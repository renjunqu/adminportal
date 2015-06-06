package cn.futuremove.adminportal.service;

import java.util.List;

import cn.futuremove.adminportal.model.Department;

import cn.futuremove.adminportal.core.service.Service;

/**
 *
 *
 */
public interface DepartmentService extends Service<Department> {

	List<Department> queryDepartmentCnList(List<Department> resultList);

}
