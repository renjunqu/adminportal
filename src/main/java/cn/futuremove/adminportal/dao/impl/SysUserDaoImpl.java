package cn.futuremove.adminportal.dao.impl;

import cn.futuremove.adminportal.dao.SysUserDao;
import org.springframework.stereotype.Repository;

import cn.futuremove.adminportal.model.SysUser;

import cn.futuremove.adminportal.core.dao.BaseDao;

/**
 *
 *
 */
@Repository
public class SysUserDaoImpl extends BaseDao<SysUser> implements SysUserDao {

	public SysUserDaoImpl() {
		super(SysUser.class);
	}

}
