package cn.futuremove.adminportal.dao.impl;

import org.springframework.stereotype.Repository;

import cn.futuremove.adminportal.dao.MailDao;
import cn.futuremove.adminportal.model.Mail;

import cn.futuremove.adminportal.core.dao.BaseDao;

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
