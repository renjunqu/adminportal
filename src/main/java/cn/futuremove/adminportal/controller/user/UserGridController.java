package cn.futuremove.adminportal.controller.user;

import cn.futuremove.adminportal.controller.BaseController;
import cn.futuremove.adminportal.model.joyMove.param.UserGridParameter;
import cn.futuremove.adminportal.util.ApplicationContextUtil;
import cn.futuremove.adminportal.util.jdbc.SmartRowMapper;
import cn.futuremove.adminportal.core.support.JqGridPageView;
import com.futuremove.cacheServer.service.CarService;
import com.joymove.entity.JOYOrder;
import com.joymove.entity.JOYUser;
import com.joymove.service.JOYOrderService;
import com.joymove.service.JOYUserService;
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
    @Autowired
    JdbcTemplate jdbcTemplate_joyMove;

    @Resource(name = "carService")
    private CarService cacheCarService;

    @Resource(name = "JOYUserService")
    private JOYUserService joyUserService;

    @Resource(name = "JOYOrderService")
    private JOYOrderService joyOrderService;



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

    @RequestMapping("/userAdmin/approve/id")
    public void updateIdStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ApplicationContextUtil.getBean("jdbcTemplate_joyMove");
        String id = request.getParameter("id");
        String sql = "update JOY_Users set authenticateid=1 where id=" + id;
        jdbcTemplate.execute(sql);
        JOYUser joyUsers = jdbcTemplate.queryForObject("select * from JOY_Users where id=" + id, new SmartRowMapper<JOYUser>(JOYUser.class));
    }

    @RequestMapping("/userAdmin/approve/drive")
    public void updateDriveStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ApplicationContextUtil.getBean("jdbcTemplate_joyMove");
        String id = request.getParameter("id");
        String sql = "update JOY_Users set authenticatedriver=1 where id=" + id;
        jdbcTemplate.execute(sql);
        JOYUser joyUsers = jdbcTemplate.queryForObject("select * from JOY_Users where id=" + id, new SmartRowMapper<JOYUser>(JOYUser.class));
    }

}
