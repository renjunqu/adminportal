package cn.futuremove.adminportal.service.sys.impl;

import javax.annotation.Resource;

import cn.futuremove.adminportal.model.sys.Mail;
import cn.futuremove.adminportal.service.sys.MailService;
import org.springframework.stereotype.Service;

import cn.futuremove.adminportal.dao.sys.MailDao;

import core.service.BaseService;

/**
 *
 *
 */
@Service
public class MailServiceImpl extends BaseService<Mail> implements MailService {

	private MailDao mailDao;

	@Resource
	public void setMailDao(MailDao mailDao) {
		this.mailDao = mailDao;
		this.dao = mailDao;
	}

}
