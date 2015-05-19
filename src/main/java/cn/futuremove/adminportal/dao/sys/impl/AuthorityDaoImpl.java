package cn.futuremove.adminportal.dao.sys.impl;

import cn.futuremove.adminportal.dao.sys.AuthorityDao;
import org.springframework.stereotype.Repository;

import cn.futuremove.adminportal.model.sys.Authority;

import core.dao.BaseDao;

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
