package cn.futuremove.adminportal.controller.joymove;


import com.futuremove.cacheServer.dao.StaticMatDao;
import com.futuremove.cacheServer.entity.Car;
import cn.futuremove.adminportal.util.ApplicationContextUtil;
import cn.futuremove.adminportal.util.GsonInstance;
import cn.futuremove.adminportal.util.lbs.LbsNearbyVo;
import cn.futuremove.adminportal.util.lbs.LbsService;
import cn.futuremove.adminportal.util.lbs.LbsVo;
import com.futuremove.cacheServer.service.CarService;
import com.joymove.entity.JOYNCar;
import com.joymove.util.SimpleJSONUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by figoxu on 15/4/23.
 */
@Controller
public class CarLocationController {
   @Resource(name = "carService")
    private CarService carService;



    @RequestMapping(value = "/car/loadByCenter/withFilter", method = { RequestMethod.POST, RequestMethod.GET })
    public  @ResponseBody JSONObject loadByCenterWithSessionFilter(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Car> carPagedList = new ArrayList<Car>();
        List<Car> carAllList = new ArrayList<Car>();
        //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        try {
            Object start = request.getParameter("start");
            Object limit = request.getParameter("limit");
            Map<String,Object> likeCondition = new HashMap<String, Object>();
            likeCondition.put("start",start==null?0:String.valueOf(start));
            likeCondition.put("limit",limit==null?5:String.valueOf(limit));
            carPagedList.addAll(carService.getPagedCarList(likeCondition));
            likeCondition.put("start", 0);
            likeCondition.put("limit", 0);
            carAllList.addAll(carService.getPagedCarList(likeCondition));

        } catch(Exception e) {
            System.out.println(e);
        }
        JSONObject Reobj  = new JSONObject();
        Reobj.put("root",SimpleJSONUtil.listToJSONArray(carPagedList));
        Reobj.put("total", carAllList.size());
        //System.out.println("heelp" + Reobj.toJSONString());
        return Reobj;
    }



    private double parseLocation(String strVal, double defaultVal) {
        try {
            defaultVal = Double.valueOf(strVal);
        }catch (Exception ex){
         //   ex.printStackTrace();
        }
        return defaultVal;
    }



    public static void main(String[] args) {
    }


}