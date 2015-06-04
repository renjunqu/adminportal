package cn.futuremove.adminportal.core;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.futuremove.adminportal.model.sys.SysUser;

import cn.futuremove.adminportal.core.controller.ExtJSBaseController;
import cn.futuremove.adminportal.core.support.ExtJSBaseParameter;

/**
 *
 *
 */
public abstract class JavaEEFrameworkBaseController<E extends ExtJSBaseParameter> extends ExtJSBaseController<E> implements Constant {

	public SysUser getCurrentSysUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return (SysUser) request.getSession().getAttribute(SESSION_SYS_USER);
	}

}
