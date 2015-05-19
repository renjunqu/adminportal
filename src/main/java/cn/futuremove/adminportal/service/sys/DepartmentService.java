package cn.futuremove.adminportal.service.sys;

import java.util.List;

import cn.futuremove.adminportal.model.sys.Department;

import core.service.Service;

/**
 *
 *
 */
public interface DepartmentService extends Service<Department> {

	List<Department> queryDepartmentCnList(List<Department> resultList);

}
