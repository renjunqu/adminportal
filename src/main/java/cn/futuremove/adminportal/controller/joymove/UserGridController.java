package cn.futuremove.adminportal.controller.joymove;


import cn.futuremove.adminportal.util.ApplicationContextUtil;
import cn.futuremove.adminportal.util.jdbc.SmartRowMapper;
import cn.futuremove.adminportal.core.support.JqGridPageView;
import com.futuremove.cacheServer.service.CarService;
import com.joymove.entity.JOYDriverLicense;
import com.joymove.entity.JOYIdAuthInfo;
import com.joymove.entity.JOYOrder;
import com.joymove.entity.JOYUser;
import com.joymove.service.JOYDriverLicenseService;
import com.joymove.service.JOYIdAuthInfoService;
import com.joymove.service.JOYOrderService;
import com.joymove.service.JOYUserService;
import com.joymove.util.StringUtil;
import org.apache.taglibs.standard.tag.common.xml.IfTag;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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

        String query = request.getParameter("query");
        String state = request.getParameter("state");
        String state2 = request.getParameter("state2");

        System.out.println("@query:" + query);
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer limit = Integer.valueOf(request.getParameter("limit"));

        if (!StringUtils.isBlank(String.valueOf(start))) {
            likeCondition.put("pageStart", start);
        }
        if (!StringUtils.isBlank(String.valueOf(limit))) {
            likeCondition.put("pageSize", limit);
        }

        if (!StringUtils.isBlank(query)) {
            likeCondition.put("username", query);
            likeCondition.put("mobileNo",query);
        }

        if (!StringUtils.isBlank(state)) {
            likeCondition.put("authenticateid", state);

        }
        if (!StringUtils.isBlank(state2)) {
            likeCondition.put("authenticatedriver", state2);
        }

        List<JOYUser> joyUsersList = joyUserService.getPagedUserList(likeCondition);
        JSONObject Reobj = new JSONObject();
        Reobj.put("root",joyUsersList);
        likeCondition.remove("pageStart");
        List<JOYUser> joyUsersAllList = joyUserService.getPagedUserList(likeCondition);
        Reobj.put("total",joyUsersAllList.size());
        return Reobj;
    }

    @RequestMapping("/userAdmin/order/ext/store")
    public @ResponseBody JSONObject orderStore(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        String mobileno = request.getParameter("mobileno");
        Map<String,Object> likeCondition = new HashMap<String, Object>();
        if (!StringUtils.isBlank(String.valueOf(start))) {
            likeCondition.put("pageStart", start);
        }
        if (!StringUtils.isBlank(String.valueOf(limit))) {
            likeCondition.put("pageSize", limit);
        }
        if (!StringUtils.isBlank(mobileno)) {
            likeCondition.put("mobileNo", mobileno);
        }
        List<JOYOrder> joyOrderList = joyOrderService.getPagedOrderList(likeCondition);
        JSONObject Reobj = new JSONObject();
        Reobj.put("root",joyOrderList);
        likeCondition.remove("pageStart");
        List<JOYOrder> joyOrderAllList = joyOrderService.getPagedOrderList(likeCondition);
        Reobj.put("total",joyOrderAllList.size());
        return Reobj;
    }

    @RequestMapping("/userAdmin/image/store")
    public void  userImageGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String mobileNo = String.valueOf(request.getParameter("mobileNo"));
            String imageType = String.valueOf(request.getParameter("type"));
            String imageDirection = String.valueOf(request.getParameter("direction"));
            Map<String, Object> likeConditon = new HashMap<String, Object>();
            byte[] imageData = null;
            if (!StringUtils.isBlank(mobileNo)) {
                likeConditon.put("mobileNo", mobileNo);
                if (imageType.equals("driver")) {
                    List<JOYDriverLicense> driverLicenses = joyDriverLicenseService.getDriverAuthInfo(likeConditon);
                    JOYDriverLicense joyDriverLic = driverLicenses.get(0);
                    if (imageDirection.equals("back")) {
                        imageData = joyDriverLic.driverAuthInfo_back;
                    } else {
                        imageData = joyDriverLic.driverAuthInfo;
                    }
                } else if (imageType.equals("id")) {
                    List<JOYIdAuthInfo> authInfos = joyIdAuthInfoService.getNeededIdAuthInfo(likeConditon);
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
        user.id = id;
        user.authenticateId = result;
        joyUserService.updateJOYUser(user);
    }

    @RequestMapping("/userAdmin/approve/drive")
    public void updateDriveStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer result = Integer.parseInt(request.getParameter("result"));
        JOYUser user = new JOYUser();
        user.id = id;
        user.authenticateDriver = result;
        joyUserService.updateJOYUser(user);
    }
}
