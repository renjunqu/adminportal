package cn.futuremove.adminportal.controller.user;

import cn.futuremove.adminportal.controller.BaseController;
import com.futuremove.cacheServer.entity.Car;
import cn.futuremove.adminportal.util.ApplicationContextUtil;
import cn.futuremove.adminportal.util.GsonInstance;
import cn.futuremove.adminportal.util.lbs.LbsNearbyVo;
import cn.futuremove.adminportal.util.lbs.LbsService;
import cn.futuremove.adminportal.util.lbs.LbsVo;
import com.futuremove.cacheServer.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class CarLocationController extends BaseController {
    @Autowired
    JdbcTemplate jdbcTemplate_joyMove;
    @Resource(name = "carService")
    private CarService carService;



    @RequestMapping(value = "/car/loadByCenter/withFilter", method = { RequestMethod.POST, RequestMethod.GET })
    public void loadByCenterWithSessionFilter(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
        carList = filterSameId(carList);

        writeJSON(response, carList);

    }

    private List<Car> filterSameId(List<Car> carList) {
        List<Car> cars = new ArrayList<Car>();
        for(Car car:carList){
            boolean existFlag = false;
            for(Car addedCar:cars){
                if(addedCar.getVinNum().equals(car.getVinNum())){
                    existFlag = true;
                }
            }
            if(existFlag){
                continue;
            }
            cars.add(car);
        }
        return cars;
    }

    private double parseLocation(String strVal, double defaultVal) {
        try {
            defaultVal = Double.valueOf(strVal);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return defaultVal;
    }

//    @RequestMapping(value = "/car/loadByCenter/withFilter", method = { RequestMethod.POST, RequestMethod.GET })
//    public void loadByCenterWithSessionFilter(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        String powerAlarmFlag = request.getParameter("powerAlarmFlag");
//        String outOfRangeFlag = request.getParameter("outOfRangeFlag");
//        String state = request.getParameter("state");
//        String filterText = request.getParameter("filterText");
//        //todo 帮忙写一下过滤条件
//        System.out.println("@powerAlarmFlag:"+powerAlarmFlag
//                        +"\t@outOfRangeFlag:"+outOfRangeFlag
//                        +"\t@state:"+state
//                        +"\t@filterText:"+filterText
//        );
//
//
//
//        Double lat = Double.valueOf(request.getParameter("lat"));
//        Double lng = Double.valueOf(request.getParameter("lng"));
//
//        LbsVo lbsVo = new LbsVo();
//        lbsVo.setLat(lat);
//        lbsVo.setLng(lng);
//
//        LbsNearbyVo lbsNearbyVo = LbsService.nearby(lbsVo, 5000);
//
//        StringBuffer sqlBuffer = new StringBuffer("select * from JOY_Car  ");
//        sqlBuffer.append(" where positionx > ").append(lbsNearbyVo.getLngMin()).append(" and positionx<").append(lbsNearbyVo.getLngMax())
//                .append(" and positiony >").append(lbsNearbyVo.getLatMin()).append(" and positiony<").append(lbsNearbyVo.getLatMax());
//
////        sqlBuffer = new StringBuffer("select * from JOY_Car where id > 10532");
//        List<JoyCar> joyCarList = jdbcTemplate_joyMove.query(sqlBuffer.toString(), new SmartRowMapper<JoyCar>(JoyCar.class));
//        System.out.println("##############");
//        System.out.println(sqlBuffer.toString());
//        System.out.println("### @found:"+joyCarList.size());
//        writeJSON(response, joyCarList);
//    }


    @RequestMapping(value = "/car/ext/store", method = { RequestMethod.POST, RequestMethod.GET })
    public void carExtStore(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        carList = filterSameId(carList);

        Map jsonMap = new HashMap();
        jsonMap.put("root", carList);
        jsonMap.put("total", carList.size());
        writeJSON(response, jsonMap);
    }

//    @RequestMapping(value = "/car/ext/store", method = { RequestMethod.POST, RequestMethod.GET })
//    public void carExtStore(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String powerAlarmFlag = request.getParameter("powerAlarmFlag");
//        String outOfRangeFlag = request.getParameter("outOfRangeFlag");
//        String state = request.getParameter("state");
//        String filterText = request.getParameter("filterText");
//        //todo 帮忙写一下过滤条件
//        System.out.println("@powerAlarmFlag:"+powerAlarmFlag
//                        +"\t@outOfRangeFlag:"+outOfRangeFlag
//                        +"\t@state:"+state
//                        +"\t@filterText:"+filterText
//        );
//
//
//
//
//        Integer start =Integer.valueOf( request.getParameter("start") );
//        Integer limit =Integer.valueOf(request.getParameter("limit"));
//
//        StringBuffer sqlBuffer = new StringBuffer("select * from JOY_Car  ");
//        sqlBuffer.append("limit ").append(start).append(",").append(limit);
//        List<JoyCar> joyCarList = jdbcTemplate_joyMove.query(sqlBuffer.toString(), new SmartRowMapper<JoyCar>(JoyCar.class));
//
//        StringBuffer countBuffer = new StringBuffer("select count(*) from JOY_Car");
//        int total = jdbcTemplate_joyMove.queryForInt(countBuffer.toString());
//
//
//        Map jsonMap = new HashMap();
//        jsonMap.put("root", joyCarList);
//        jsonMap.put("total", total);
//        writeJSON(response, jsonMap);
//    }





    public static void main(String[] args) {
        LbsVo lbsVo = new LbsVo();
        lbsVo.setLat(39.91667);
        lbsVo.setLng(116.41667);
        LbsNearbyVo lbsNearbyVo = LbsService.nearby(lbsVo, 1000);
        System.out.println(GsonInstance.instance().toJson(lbsNearbyVo));

//        {"latMin":39.90767,"latMax":39.925670000000004,"lngMin":116.40615,"lngMax":116.42719}

    }


}
