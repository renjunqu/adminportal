package cn.futuremove.adminportal.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import cn.futuremove.adminportal.model.Authority;

import cn.futuremove.adminportal.core.service.Service;

/**
 *
 *
 */
public interface AuthorityService extends Service<Authority> {

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_RESTRICTED_ADMIN') or hasRole('ROLE_USER')")
	List<Authority> queryAllMenuList(String globalRoleKey, List<Authority> mainMenuList);

}
