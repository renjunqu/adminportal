package cn.futuremove.adminportal.dao.sys.impl;

import cn.futuremove.adminportal.dao.sys.DepartmentDao;
import org.springframework.stereotype.Repository;

import cn.futuremove.adminportal.model.sys.Department;

import core.dao.BaseDao;

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
