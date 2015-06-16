package cn.futuremove.adminportal.controller.joymove;


import com.futuremove.cacheServer.service.CarService;
import com.joymove.entity.JOYDriverLicense;
import com.joymove.entity.JOYIdAuthInfo;
import com.joymove.entity.JOYOrder;
import com.joymove.entity.JOYUser;
import com.joymove.service.JOYDriverLicenseService;
import com.joymove.service.JOYIdAuthInfoService;
import com.joymove.service.JOYOrderService;
import com.joymove.service.JOYUserService;
import com.joymove.util.SimpleJSONUtil;
import net.sf.json.JSONArray;
import org.json.simple.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by figoxu on 15/4/21.
 */
@Controller
public class UserGridController {

    @Resource(name = "carService")
    private CarService cacheCarService;

    @Resource(name = "JOYUserService")
    private JOYUserService joyUserService;

    @Resource(name = "JOYOrderService")
    private JOYOrderService joyOrderService;

    @Resource(name = "JOYIdAuthInfoService")
    private JOYIdAuthInfoService joyIdAuthInfoService;
    @Resource(name = "JOYDriverLicenseService")
    private JOYDriverLicenseService joyDriverLicenseService;



    @RequestMapping("/user/ext/store")
    public @ResponseBody JSONObject extStore(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Map<String,Object> likeCondition = new HashMap<String, Object>();
        JOYUser userFilter = new JOYUser();

        //logger.trace("@query:" + query);
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer limit = Integer.valueOf(request.getParameter("limit"));

        userFilter.setDataFilterFromHTTPReq(request);

        if(userFilter.mobileNo==null) {
             userFilter.mobileNo = "%";
        } else {
            userFilter.mobileNo+="%";
        }

        List<Map<String,Object>> mapList = joyUserService.getExtendInfoPagedList(" select u.*, m.driverLicenseNumber  from JOY_Users u left join JOY_DriverLicense m on u.mobileNo = m.mobileNo",
                userFilter,start,limit);
        JSONObject Reobj = new JSONObject();
        JSONArray joyUserJsonList = new JSONArray();
        for(int i=0;i<mapList.size();i++) {
            Map<String,Object> mapE = mapList.get(i);
          //  logger.trace(SimpleJSONUtil.fromMap(mapE).toJSONString());
            joyUserJsonList.add(SimpleJSONUtil.fromMap(mapE));
        }

        Reobj.put("root",joyUserJsonList);
        Reobj.put("total",joyUserService.countRecord(userFilter));
        return Reobj;
    }

    @RequestMapping("/userAdmin/image/store")
    public void  userImageGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            JOYIdAuthInfo idAuthInfoFilter = new JOYIdAuthInfo();
            JOYDriverLicense driverLicenseFilter = new JOYDriverLicense();
            String mobileNo = String.valueOf(request.getParameter("mobileNo"));
            String imageType = String.valueOf(request.getParameter("type"));
            String imageDirection = String.valueOf(request.getParameter("direction"));
            Map<String, Object> likeConditon = new HashMap<String, Object>();
            byte[] imageData = null;
            if (!StringUtils.isBlank(mobileNo)) {
                idAuthInfoFilter.mobileNo = mobileNo;
                driverLicenseFilter.mobileNo = mobileNo;
                if (imageType.equals("driver")) {
                    List<JOYDriverLicense> driverLicenses = joyDriverLicenseService.getNeededList(driverLicenseFilter);
                    JOYDriverLicense joyDriverLic = driverLicenses.get(0);
                    if (imageDirection.equals("back")) {
                        imageData = joyDriverLic.driverAuthInfo_back;
                    } else {
                        imageData = joyDriverLic.driverAuthInfo;
                    }
                } else if (imageType.equals("id")) {
                    List<JOYIdAuthInfo> authInfos = joyIdAuthInfoService.getNeededList(idAuthInfoFilter);
                    JOYIdAuthInfo authInfo = authInfos.get(0);
                    if (imageDirection.equals("back")) {
                        imageData = authInfo.idAuthInfo_back;
                    } else {
                        imageData = authInfo.idAuthInfo;
                    }
                }
            }
            if (imageData != null) {
                response.setContentType("image/jpg");
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(imageData);
                outputStream.close();
            }
        } catch(Exception e){
            System.out.print(e);
            e.printStackTrace();
        }
    }


    @RequestMapping("/userAdmin/approve/id")
    public void updateIdStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer result = Integer.parseInt(request.getParameter("result"));
        JOYUser user = new JOYUser();
        JOYUser userFilter = new JOYUser();
        userFilter.id = id;
        user.authenticateId = result;
        joyUserService.updateRecord(user, userFilter);
    }

    @RequestMapping("/userAdmin/approve/drive")
    public void updateDriveStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer result = Integer.parseInt(request.getParameter("result"));
        JOYUser user = new JOYUser();
        JOYUser userFilter = new JOYUser();
        userFilter.id = id;
        user.authenticateDriver = result;
        joyUserService.updateRecord(user, userFilter);
    }
}
