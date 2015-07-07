package cn.futuremove.adminportal.controller.joymove;


import com.futuremove.cacheServer.entity.CarDynProps;
import com.futuremove.cacheServer.service.CarDynPropsService;
import com.futuremove.cacheServer.utils.CoordinatesUtil;
import com.futuremove.cacheServer.utils.Gps;
import com.mongodb.client.FindIterable;
import org.json.simple.JSONArray;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    /*
   @Resource(name = "carService")
    private CarService carService;
*/
    @Resource(name = "CarDynPropsService")
    private CarDynPropsService carPropsService;

    final static Logger logger = LoggerFactory.getLogger(CarLocationController.class);





    @RequestMapping(value = "/car/loadByCenter/withFilter", method = { RequestMethod.POST, RequestMethod.GET })
    public  @ResponseBody JSONObject loadByCenterWithSessionFilter(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONArray carArray  = new JSONArray();
        CarDynProps carDynPropsFilter = new CarDynProps();
        CarDynProps carProps = new CarDynProps();
        Long count = 0L;
        //logger.trace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        try {
            Long startV = Long.parseLong(request.getParameter("start"));
            Long limitV = Long.parseLong(request.getParameter("limit"));
            Map<String,Object> likeCondition = new HashMap<String, Object>();

            FindIterable<Document> carPropsDocs =  carPropsService.find(carDynPropsFilter).skip(startV.intValue()).limit(limitV.intValue());
            for(Document doc:carPropsDocs) {
                JSONObject carJson = new JSONObject();
                carProps.fromDocument(doc);
                carJson.putAll(doc);
                Gps carGcj02 = CoordinatesUtil.gps84_To_Gcj02(carProps.location.coordinates.get(1),
                        carProps.location.coordinates.get(0));
                carJson.put("longitude", carGcj02.getWgLon());
                carJson.put("latitude", carGcj02.getWgLat());
                carArray.add(carJson);
            }
                count  = carPropsService.count(carDynPropsFilter);


        } catch(Exception e) {
            logger.trace(e.getStackTrace().toString());
        }
        JSONObject Reobj  = new JSONObject();
        Reobj.put("root",carArray);
        Reobj.put("total", count);
        //logger.trace("heelp" + Reobj.toJSONString());
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
