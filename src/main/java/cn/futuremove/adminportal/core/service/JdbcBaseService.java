package cn.futuremove.adminportal.core.service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import cn.futuremove.adminportal.core.dao.JdbcBaseDao;

/**
 *
 *
 */
@Transactional
public class JdbcBaseService {

	@Resource
	protected JdbcBaseDao jdbcBaseDao;

}
