package cn.futuremove.adminportal.dao.impl;

import cn.futuremove.adminportal.dao.RoleAuthorityDao;
import cn.futuremove.adminportal.model.RoleAuthority;
import org.springframework.stereotype.Repository;

import cn.futuremove.adminportal.core.dao.BaseDao;

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
