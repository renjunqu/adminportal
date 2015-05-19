package cn.futuremove.adminportal.dao.sys.impl;

import cn.futuremove.adminportal.dao.sys.SysUserDao;
import org.springframework.stereotype.Repository;

import cn.futuremove.adminportal.model.sys.SysUser;

import core.dao.BaseDao;

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
