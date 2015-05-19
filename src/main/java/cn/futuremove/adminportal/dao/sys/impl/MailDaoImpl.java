package cn.futuremove.adminportal.dao.sys.impl;

import org.springframework.stereotype.Repository;

import cn.futuremove.adminportal.dao.sys.MailDao;
import cn.futuremove.adminportal.model.sys.Mail;

import core.dao.BaseDao;

/**
 *
 *
 */
@Repository
public class MailDaoImpl extends BaseDao<Mail> implements MailDao {

	public MailDaoImpl() {
		super(Mail.class);
	}

}
