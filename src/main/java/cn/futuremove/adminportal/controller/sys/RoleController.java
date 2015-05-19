package cn.futuremove.adminportal.controller.sys;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.futuremove.adminportal.model.sys.Role;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.futuremove.adminportal.core.Constant;
import cn.futuremove.adminportal.core.JavaEEFrameworkBaseController;
import cn.futuremove.adminportal.service.sys.RoleService;

import core.support.ExtJSBaseParameter;
import core.support.JqGridPageView;
import core.support.QueryResult;

/**
 *
 *
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController extends JavaEEFrameworkBaseController<Role> implements Constant {

	@Resource
	private RoleService roleService;

	@RequestMapping(value = "/getRole", method = { RequestMethod.POST, RequestMethod.GET })
	public void getRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer firstResult = Integer.valueOf(request.getParameter("page"));
		Integer maxResults = Integer.valueOf(request.getParameter("rows"));
		String sortedObject = request.getParameter("sidx");
		String sortedValue = request.getParameter("sord");
		String filters = request.getParameter("filters");
		Role role = new Role();
		if (StringUtils.isNotBlank(filters)) {
			JSONObject jsonObject = JSONObject.fromObject(filters);
			JSONArray jsonArray = (JSONArray) jsonObject.get("rules");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject result = (JSONObject) jsonArray.get(i);
				if (result.getString("field").equals("roleKey") && result.getString("op").equals("eq")) {
					role.set$eq_roleKey(result.getString("data"));
				}
				if (result.getString("field").equals("roleValue") && result.getString("op").equals("cn")) {
					role.set$like_roleValue(result.getString("data"));
				}
			}
			if (((String) jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
				role.setFlag("OR");
			} else {
				role.setFlag("AND");
			}
		}
		role.setFirstResult((firstResult - 1) * maxResults);
		role.setMaxResults(maxResults);
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put(sortedObject, sortedValue);
		role.setSortedConditions(sortedCondition);
		QueryResult<Role> queryResult = roleService.doPaginationQuery(role);
		JqGridPageView<Role> roleListView = new JqGridPageView<Role>();
		roleListView.setMaxResults(maxResults);
		roleListView.setRows(queryResult.getResultList());
		roleListView.setRecords(queryResult.getTotalCount());
		writeJSON(response, roleListView);
	}

	//@Override
	@RequestMapping(value = "/saveRole", method = { RequestMethod.POST, RequestMethod.GET })
	public void doSave(Role entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
		if (CMD_EDIT.equals(parameter.getCmd())) {
			roleService.update(entity);
		} else if (CMD_NEW.equals(parameter.getCmd())) {
			roleService.persist(entity);
		}
		parameter.setSuccess(true);
		writeJSON(response, parameter);
	}

	@RequestMapping(value = "/operateRole", method = { RequestMethod.POST, RequestMethod.GET })
	public void operateRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oper = request.getParameter("oper");
		String id = request.getParameter("id");
		if (oper.equals("del")) {
			String[] ids = id.split(",");
			deleteRole(request, response, (Long[]) ConvertUtils.convert(ids, Long.class));
		} else if (oper.equals("excel")) {
			response.setContentType("application/msexcel;charset=UTF-8");
			try {
				response.addHeader("Content-Disposition", "attachment;filename=file.xls");
				OutputStream out = response.getOutputStream();
				out.write(request.getParameter("csvBuffer").getBytes());
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Map<String, Object> result = new HashMap<String, Object>();
			String roleKey = request.getParameter("roleKey");
			String roleValue = request.getParameter("roleValue");
			String description = request.getParameter("description");
			Role role = null;
			if (oper.equals("edit")) {
				role = roleService.get(Long.valueOf(id));
			}
			Role roleKeyRole = roleService.getByProerties("roleKey", roleKey);
			if (StringUtils.isBlank(roleKey) || StringUtils.isBlank(roleValue)) {
				response.setStatus(HttpServletResponse.SC_LENGTH_REQUIRED);
				result.put("message", "请填写角色编码和角色名称");
				writeJSON(response, result);
			} else if (null != roleKeyRole && oper.equals("add")) {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				result.put("message", "此角色编码已存在，请重新输入");
				writeJSON(response, result);
			} else if (null != roleKeyRole && !role.getRoleKey().equalsIgnoreCase(roleKey) && oper.equals("edit")) {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				result.put("message", "此角色编码已存在，请重新输入");
				writeJSON(response, result);
			} else {
				Role entity = new Role();
				entity.setRoleKey(roleKey);
				entity.setRoleValue(roleValue);
				entity.setDescription(description);
				if (oper.equals("edit")) {
					entity.setId(Long.valueOf(id));
					entity.setCmd("edit");
					doSave(entity, request, response);
				} else if (oper.equals("add")) {
					entity.setCmd("new");
					doSave(entity, request, response);
				}
			}
		}
	}

	@RequestMapping("/deleteRole")
	public void deleteRole(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
		boolean flag = roleService.deleteByPK(ids);
		if (flag) {
			writeJSON(response, "{success:true}");
		} else {
			writeJSON(response, "{success:false}");
		}
	}

}
