package cn.futuremove.adminportal.controller.user;

import cn.futuremove.adminportal.controller.BaseController;
import cn.futuremove.adminportal.model.joyMove.JoyOrder;
import cn.futuremove.adminportal.util.jdbc.SmartRowMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by figoxu on 15/4/24.
 */
@Controller
public class OrderController  extends BaseController {
    @Autowired
    JdbcTemplate jdbcTemplate_joyMove;

    @RequestMapping(value = "/order/ext/store", method = { RequestMethod.POST, RequestMethod.GET })
    public void modify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String query = request.getParameter("query") ;

        System.out.println("@query:"+query);
        Integer start =Integer.valueOf( request.getParameter("start") );
        Integer limit =Integer.valueOf(request.getParameter("limit"));

        StringBuffer sqlBuffer = new StringBuffer("select * from JOY_Order ");
        StringBuffer countSqlBuffer = new StringBuffer("select count(*) from JOY_Order");

        if(!StringUtils.isBlank(query)){
            sqlBuffer.append(" where  mobileno like '%"+query+"%' ");
            countSqlBuffer.append(" where  mobileno like '%"+query+"%' ");
        }
        sqlBuffer.append(" limit ").append(start).append(",").append(limit);
        int total = jdbcTemplate_joyMove.queryForInt(countSqlBuffer.toString());
        List<JoyOrder> joyOrderList = jdbcTemplate_joyMove.query(sqlBuffer.toString(), new SmartRowMapper<JoyOrder>(JoyOrder.class));

        Map jsonMap = new HashMap();
        jsonMap.put("root", joyOrderList);
        jsonMap.put("total", total);
        this.writeJSON(response,jsonMap);
        return;
    }
}
