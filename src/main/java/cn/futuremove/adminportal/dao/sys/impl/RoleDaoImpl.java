package cn.futuremove.adminportal.dao.sys.impl;

import cn.futuremove.adminportal.model.sys.Role;
import org.springframework.stereotype.Repository;

import cn.futuremove.adminportal.dao.sys.RoleDao;

import core.dao.BaseDao;

/**
 *
 *
 */
@Repository
public class RoleDaoImpl extends BaseDao<Role> implements RoleDao {

	public RoleDaoImpl() {
		super(Role.class);
	}

}
