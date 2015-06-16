package cn.futuremove.adminportal.controller.joymove;


import com.joymove.entity.JOYOrder;
import com.joymove.entity.JOYUser;
import com.joymove.service.JOYOrderService;
import com.joymove.util.SimpleJSONUtil;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by figoxu on 15/4/24.
 */
@Controller
public class OrderController {
    @Resource(name = "JOYOrderService")
    private JOYOrderService joyOrderService;

    final static Logger logger = LoggerFactory.getLogger(OrderController.class);




    @RequestMapping(value = "/order/ext/store", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody JSONObject modify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject Reobj = new JSONObject();
        try {
            JOYOrder orderFilter = new JOYOrder();
            //logger.trace("@query:" + query);
            Integer start = Integer.valueOf(request.getParameter("start"));
            Integer limit = Integer.valueOf(request.getParameter("limit"));

            orderFilter.setDataFilterFromHTTPReq(request);
            if(orderFilter.mobileNo==null) {
                orderFilter.mobileNo = "%";
            } else {
                orderFilter.mobileNo+="%";
            }


            Map<String, Object> timeScope = new HashMap<String, Object>();

            try {
                if (request.getParameter("maxStartTime") != null) {
                    timeScope.put("maxStartTime", new Date(Long.parseLong(request.getParameter("maxStartTime"))));
                }
                if (request.getParameter("minStartTime") != null) {
                    timeScope.put("minStartTime", new Date(Long.parseLong(request.getParameter("minStartTime"))));
                }

                if (request.getParameter("minStopTime") != null) {
                    timeScope.put("minStopTime", new Date(Long.parseLong(request.getParameter("minStopTime"))));
                }

                if (request.getParameter("maxStopTime") != null) {
                    timeScope.put("maxStopTime", new Date(Long.parseLong(request.getParameter("maxStopTime"))));
                }
            } catch(Exception e) {
                timeScope.clear();
            }





       //     List<Map<String,Object>> mapList = joyUserService.getExtendInfoPagedList(" select u.*, m.driverLicenseNumber  from JOY_Users u left join JOY_DriverLicense m on u.mobileNo = m.mobileNo", userFilter);

            List<Map<String,Object>> mapList = joyOrderService.getExtendInfoPagedList(
                    " select sum(cp.couponNum) as cNum,ph.type as payType,ph.balance as payBalance,ncar.licensenum as carLicenseNum,car.desp as carDesp ,u.* from " +
                            " JOY_Order u " +
                            " left join JOY_Coupon cp on u.id = cp.orderId " +
                            " left join JOY_PayHistory ph on u.id = ph.orderId " +
                            " left join JOY_NCar ncar on u.carVinNum=ncar.vinNum " +
                            " left join JOY_Car car on u.carId = car.id ",
                    timeScope,orderFilter,start,limit,"DESC");

             JSONArray joyExtendOrderJsonList = new JSONArray();

             for(int i=0;i<mapList.size();i++) {
                 Map<String,Object> mapE = mapList.get(i);
                 joyExtendOrderJsonList.add(SimpleJSONUtil.fromMap(mapE));
             }
            Reobj.put("root",joyExtendOrderJsonList);
            Reobj.put("total", joyOrderService.countRecord(orderFilter));

        } catch(Exception e) {
             logger.trace(e.getStackTrace().toString());
        }
            return Reobj;
    }
}
