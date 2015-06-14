package cn.futuremove.adminportal.controller.joymove;

import com.joymove.entity.JOYOrder;
import com.joymove.entity.JOYPayHistory;
import com.joymove.service.JOYOrderService;
import com.joymove.service.JOYPayHistoryService;
import com.joymove.velocity.directives.TimeScopeFilter;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jessie on 2015/6/14.
 */

@Controller
public class PayHistoryController {
    @Resource(name = "JOYPayHistoryService")
    private JOYPayHistoryService  joyPayHistoryService;

    @RequestMapping("/userAdmin/payhistory/ext/store")
    public @ResponseBody
    JSONObject orderStore(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JOYPayHistory payHistoryFilter = new JOYPayHistory();
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        payHistoryFilter.setDataFilterFromHTTPReq(request);
        Map<String,Object> timeScope = new HashMap<String, Object>();
        if(request.getParameter("minRentTime")!=null) {
            timeScope.put("minRentTime", new Date(Long.parseLong(request.getParameter("minRentTime"))));
        }
        if(request.getParameter("maxRentTime")!=null) {
            timeScope.put("maxRentTime", new Date(Long.parseLong(request.getParameter("maxRentTime"))));
        }

        List<JOYPayHistory> payHistories = joyPayHistoryService.getListWithTimeScope(payHistoryFilter, timeScope, start, limit, "DESC");
        JSONObject Reobj = new JSONObject();
        JSONArray rootArray = new JSONArray();
        for(int i=0;i<payHistories.size();i++) {
            JOYPayHistory payHistory = payHistories.get(i);
            System.out.println(payHistory);
        }
        Reobj.put("root", payHistories);
        Long recordCount  = joyPayHistoryService.countRecordWithTimeScope(payHistoryFilter,timeScope);
        Reobj.put("total",recordCount);
        return Reobj;
    }


}
