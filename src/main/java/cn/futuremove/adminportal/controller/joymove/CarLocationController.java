package cn.futuremove.adminportal.controller.joymove;


import com.futuremove.cacheServer.entity.Car;
import cn.futuremove.adminportal.util.ApplicationContextUtil;
import cn.futuremove.adminportal.util.GsonInstance;
import cn.futuremove.adminportal.util.lbs.LbsNearbyVo;
import cn.futuremove.adminportal.util.lbs.LbsService;
import cn.futuremove.adminportal.util.lbs.LbsVo;
import com.futuremove.cacheServer.service.CarService;
import com.joymove.entity.JOYNCar;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by figoxu on 15/4/23.
 */
@Controller
public class CarLocationController {
   @Resource(name = "carService")
    private CarService carService;



    @RequestMapping(value = "/car/loadByCenter/withFilter", method = { RequestMethod.POST, RequestMethod.GET })
    public  @ResponseBody JSONObject loadByCenterWithSessionFilter(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String powerAlarmFlag = request.getParameter("powerAlarmFlag");
        String outOfRangeFlag = request.getParameter("outOfRangeFlag");
        String state = request.getParameter("state");
        String filterText = request.getParameter("filterText");
        //todo 帮忙写一下过滤条件
        System.out.println("@powerAlarmFlag:" + powerAlarmFlag
                        + "\t@outOfRangeFlag:" + outOfRangeFlag
                        + "\t@state:" + state
                        + "\t@filterText:" + filterText
        );

        double defaultX = 116.413879;
        double defaultY = 39.919216;

        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
        defaultX = parseLocation(lat,defaultX);
        defaultY = parseLocation(lng,defaultY);


        HashMap<String, Object> likeCondition = new HashMap<String, Object>();
        likeCondition.put("userPositionX", defaultX);
        likeCondition.put("userPositionY", defaultY);
        likeCondition.put("scope", 500000000);
        List<Car> carAList = carService.getFreeCarByScope(likeCondition);
        List<Car> carBList  = carService.getBusyCarByScope(likeCondition);

        List<Car> carList = new ArrayList<Car>();
        carList.addAll(carAList);
        carList.addAll(carBList);

        JSONObject Reobj  = JSONObject.fromObject(carAList);
        return Reobj;

    }



    private double parseLocation(String strVal, double defaultVal) {
        try {
            defaultVal = Double.valueOf(strVal);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return defaultVal;
    }
    @RequestMapping(value = "/car/ext/store", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody JSONObject carExtStore(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String powerAlarmFlag = request.getParameter("powerAlarmFlag");
        String outOfRangeFlag = request.getParameter("outOfRangeFlag");
        String state = request.getParameter("state");
        String filterText = request.getParameter("filterText");
        //todo 帮忙写一下过滤条件
        System.out.println("@powerAlarmFlag:" + powerAlarmFlag
                        + "\t@outOfRangeFlag:" + outOfRangeFlag
                        + "\t@state:" + state
                        + "\t@filterText:" + filterText
        );

        double defaultX = 116.413879;
        double defaultY = 39.919216;

        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
        defaultX = parseLocation(lat,defaultX);
        defaultY = parseLocation(lng,defaultY);




        HashMap<String, Object> likeCondition = new HashMap<String, Object>();
        likeCondition.put("userPositionX", defaultX);
        likeCondition.put("userPositionY", defaultY);
        likeCondition.put("scope", 500000000);
        List<Car> carAList = carService.getFreeCarByScope(likeCondition);
        List<Car> carBList  = carService.getBusyCarByScope(likeCondition);

        List<Car> carList = new ArrayList<Car>();
        carList.addAll(carAList);
        carList.addAll(carBList);

        JSONObject Reobj  = new JSONObject();
        Reobj.put("root", carList);
        Reobj.put("total", carList.size());
        return Reobj;
    }



    public static void main(String[] args) {
         List<JOYNCar> nCarList = new ArrayList<JOYNCar>();
        JOYNCar car = new JOYNCar();
        car.vinNum = "111222";
        nCarList.add(car);
        car = new JOYNCar();
        car.vinNum = "222333";
        nCarList.add(car);
        JSONObject test = JSONObject.fromObject(car);
        //test.put("list",JSONArray.fromObject(nCarList));
        System.out.println(test);
    }


}
