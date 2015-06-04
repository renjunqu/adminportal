package cn.futuremove.adminportal.service.sys.impl;

import javax.annotation.Resource;

import cn.futuremove.adminportal.dao.sys.RoleAuthorityDao;
import cn.futuremove.adminportal.model.sys.RoleAuthority;
import cn.futuremove.adminportal.service.sys.RoleAuthorityService;
import org.springframework.stereotype.Service;

import cn.futuremove.adminportal.core.service.BaseService;

/**
 *
 *
 */
@Service
public class RoleAuthorityServiceImpl extends BaseService<RoleAuthority> implements RoleAuthorityService {

	private RoleAuthorityDao roleAuthorityDao;

	@Resource
	public void setRoleAuthorityDao(RoleAuthorityDao roleAuthorityDao) {
		this.roleAuthorityDao = roleAuthorityDao;
		this.dao = roleAuthorityDao;
	}

}
