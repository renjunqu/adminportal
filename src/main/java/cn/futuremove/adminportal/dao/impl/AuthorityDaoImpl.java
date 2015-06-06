package cn.futuremove.adminportal.dao.impl;

import cn.futuremove.adminportal.dao.AuthorityDao;
import org.springframework.stereotype.Repository;

import cn.futuremove.adminportal.model.Authority;

import cn.futuremove.adminportal.core.dao.BaseDao;

/**
 *
 *
 */
@Repository
public class AuthorityDaoImpl extends BaseDao<Authority> implements AuthorityDao {

	public AuthorityDaoImpl() {
		super(Authority.class);
	}

}
