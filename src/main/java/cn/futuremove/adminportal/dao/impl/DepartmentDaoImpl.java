package cn.futuremove.adminportal.dao.impl;

import cn.futuremove.adminportal.dao.DepartmentDao;
import org.springframework.stereotype.Repository;

import cn.futuremove.adminportal.model.sys.Department;

import cn.futuremove.adminportal.core.dao.BaseDao;

/**
 *
 *
 */
@Repository
public class DepartmentDaoImpl extends BaseDao<Department> implements DepartmentDao {

	public DepartmentDaoImpl() {
		super(Department.class);
	}

}
