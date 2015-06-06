package cn.futuremove.adminportal.controller.joymove;


import cn.futuremove.adminportal.util.jdbc.SmartRowMapper;
import com.joymove.entity.JOYOrder;
import com.joymove.entity.JOYUser;
import com.joymove.service.JOYOrderService;
import org.apache.commons.lang.StringUtils;
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


    @RequestMapping(value = "/order/ext/store", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody JSONObject modify(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
}
