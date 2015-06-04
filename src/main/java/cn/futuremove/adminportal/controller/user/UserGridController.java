package cn.futuremove.adminportal.controller.user;

import cn.futuremove.adminportal.controller.BaseController;
import cn.futuremove.adminportal.model.joyMove.param.UserGridParameter;
import cn.futuremove.adminportal.util.ApplicationContextUtil;
import cn.futuremove.adminportal.util.jdbc.SmartRowMapper;
import cn.futuremove.adminportal.core.support.JqGridPageView;
import com.futuremove.cacheServer.service.CarService;
import com.joymove.entity.JOYUser;
import com.joymove.service.JOYUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class UserGridController  extends BaseController {
    @Autowired
    JdbcTemplate jdbcTemplate_joyMove;

    @Resource(name = "carService")
    private CarService cacheCarService;

    @Resource(name = "JOYUserService")
    private JOYUserService joyUserService;


    @RequestMapping(value = "/user/modify", method = { RequestMethod.POST, RequestMethod.GET })
    public void modify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String id = request.getParameter("id");
        String sql = "update Joy_Users set username='"+username+"' where id="+id;
        int updateCount = jdbcTemplate_joyMove.update(sql);
        if(updateCount>0){
            return;
        }
        Map<String, Object> result = new HashMap<String, Object>();
        response.setStatus(HttpServletResponse.SC_LENGTH_REQUIRED);
        result.put("message", "this is test");
        writeJSON(response, result);
        return;
    }


    @RequestMapping("/user/query")
    public void userQuery(UserGridParameter userGridParameter,HttpServletRequest request, HttpServletResponse response) throws IOException {

        JOYUser user = new JOYUser();
        List<JOYUser> joyUsersList = joyUserService.getNeededUser(user);


        int totalPage = (totalResult+pageSize-1  )/pageSize;

        JqGridPageView<JOYUser> departmentListView = new JqGridPageView<JOYUser>();
        departmentListView.setMaxResults(pageSize);
        departmentListView.setRows(joyUsersList);
        departmentListView.setRecords(totalResult);
        departmentListView.setTotal(totalPage);
        writeJSON(response, departmentListView);
    }

    @RequestMapping("/user/ext/store")
    public void extStore(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String query = request.getParameter("query") ;

        String state = request.getParameter("state");
        String state2 = request.getParameter("state2");

        System.out.println("@query:"+query);

        Integer start =Integer.valueOf(request.getParameter("start"));
        Integer limit =Integer.valueOf(request.getParameter("limit"));
        StringBuffer sqlQuery = new StringBuffer("select * from JOY_Users");
        StringBuffer countSqlQuery = new StringBuffer("select count(*) from JOY_Users");
        boolean whereFlag = false;
        if(!StringUtils.isBlank(query)){
            sqlQuery.append(" where  mobileno like '%"+query+"%' ");
            sqlQuery.append(" or  username like '%"+query+"%' ");

            countSqlQuery.append(" where  mobileno like '%"+query+"%' ");
            countSqlQuery.append(" or  username like '%"+query+"%' ");
            whereFlag = true;
        }

        if(!StringUtils.isBlank(state)){
            if(!whereFlag){
                sqlQuery.append(" where  ");
                countSqlQuery.append(" where  ");
            }else{
                sqlQuery.append(" and  ");
                countSqlQuery.append(" and  ");
            }
            sqlQuery.append(" authenticateid="+state);
            countSqlQuery.append(" authenticateid="+state);
            whereFlag = true;
        }
        if(!StringUtils.isBlank(state2)){
            if(!whereFlag){
                sqlQuery.append(" where  ");
                countSqlQuery.append(" where  ");
            }else{
                sqlQuery.append(" and  ");
                countSqlQuery.append(" and  ");
            }
            sqlQuery.append(" authenticatedriver="+state2);
            countSqlQuery.append(" authenticatedriver="+state2);
            whereFlag = true;
        }



        sqlQuery.append(" limit ").append(start).append(",").append(limit);
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ApplicationContextUtil.getBean("jdbcTemplate_joyMove");
        int totalResult = jdbcTemplate.queryForInt(countSqlQuery.toString());

        List<JOYUser> joyUsersList = jdbcTemplate.query(sqlQuery.toString(), new SmartRowMapper<JOYUser>(JOYUser.class));


        Map jsonMap = new HashMap();
        jsonMap.put("root", joyUsersList);
        jsonMap.put("total", totalResult);
        writeJSON(response, jsonMap);
        return;
    }

    @RequestMapping("/userAdmin/order/ext/store")
    public void orderStore(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer start =Integer.valueOf(request.getParameter("start"));
        Integer limit =Integer.valueOf(request.getParameter("limit"));
        String mobileno = request.getParameter("mobileno");

        StringBuffer sqlQuery = new StringBuffer("select * from JOY_Order");
        StringBuffer countSqlQuery = new StringBuffer("select count(*) from JOY_Order");
        if(!StringUtils.isBlank(mobileno)){
            sqlQuery.append(" where  mobileno = '"+mobileno+"' ");

            countSqlQuery.append(" where  mobileno = '"+mobileno+"' ");
        }



        sqlQuery.append(" limit ").append(start).append(",").append(limit);

        JdbcTemplate jdbcTemplate = (JdbcTemplate) ApplicationContextUtil.getBean("jdbcTemplate_joyMove");
        int totalResult = jdbcTemplate.queryForInt(countSqlQuery.toString());

        List<JOYUser> joyOrderList = jdbcTemplate.query(sqlQuery.toString(), new SmartRowMapper<JOYUser>(JOYUser.class));


        Map jsonMap = new HashMap();
        jsonMap.put("root", joyOrderList);
        jsonMap.put("total", totalResult);
        writeJSON(response, jsonMap);

    }

    @RequestMapping("/userAdmin/approve/id")
    public void updateIdStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ApplicationContextUtil.getBean("jdbcTemplate_joyMove");
        String id = request.getParameter("id");
        String sql = "update JOY_Users set authenticateid=1 where id="+id;
        jdbcTemplate.execute(sql);
        JOYUser joyUsers = jdbcTemplate.queryForObject("select * from JOY_Users where id=" + id, new SmartRowMapper<JOYUser>(JOYUser.class));
     }

    @RequestMapping("/userAdmin/approve/drive")
    public void updateDriveStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ApplicationContextUtil.getBean("jdbcTemplate_joyMove");
        String id = request.getParameter("id");
        String sql = "update JOY_Users set authenticatedriver=1 where id="+id;
        jdbcTemplate.execute(sql);
        JOYUser joyUsers = jdbcTemplate.queryForObject("select * from JOY_Users where id=" + id, new SmartRowMapper<JOYUser>(JOYUser.class));
    }

}
