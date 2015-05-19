package cn.futuremove.adminportal.service.sys.impl;

import javax.annotation.Resource;

import cn.futuremove.adminportal.model.sys.Role;
import org.springframework.stereotype.Service;

import cn.futuremove.adminportal.dao.sys.RoleDao;
import cn.futuremove.adminportal.service.sys.RoleService;

import core.service.BaseService;

/**
 *
 *
 */
@Service
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

	private RoleDao roleDao;

	@Resource
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
		this.dao = roleDao;
	}

}
