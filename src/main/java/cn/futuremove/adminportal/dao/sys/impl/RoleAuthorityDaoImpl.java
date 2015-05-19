package cn.futuremove.adminportal.dao.sys.impl;

import cn.futuremove.adminportal.dao.sys.RoleAuthorityDao;
import cn.futuremove.adminportal.model.sys.RoleAuthority;
import org.springframework.stereotype.Repository;

import core.dao.BaseDao;

/**
 *
 *
 */
@Repository
public class RoleAuthorityDaoImpl extends BaseDao<RoleAuthority> implements RoleAuthorityDao {

	public RoleAuthorityDaoImpl() {
		super(RoleAuthority.class);
	}

}
