package cn.futuremove.adminportal.service;

import java.util.List;

import cn.futuremove.adminportal.model.SysUser;

import cn.futuremove.adminportal.core.service.Service;

/**
 *
 *
 */
public interface SysUserService extends Service<SysUser> {

	List<SysUser> querySysUserCnList(List<SysUser> resultList);

	SysUser getSysUserWithAvatar(SysUser sysuser);

}
