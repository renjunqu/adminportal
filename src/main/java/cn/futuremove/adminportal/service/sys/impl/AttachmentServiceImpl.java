package cn.futuremove.adminportal.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.futuremove.adminportal.dao.sys.AttachmentDao;
import cn.futuremove.adminportal.service.sys.AttachmentService;
import org.springframework.stereotype.Service;

import cn.futuremove.adminportal.model.sys.Attachment;

import core.service.BaseService;

/**
 *
 *
 */

@Service
public class AttachmentServiceImpl extends BaseService<Attachment> implements AttachmentService {
    
	private AttachmentDao attachmentDao;

	@Resource
	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
		this.dao = attachmentDao;
	}

	//@Override
	public List<Object[]> queryFlowerList(String epcId) {
		return attachmentDao.queryFlowerList(epcId);
	}

	//@Override
	public void deleteAttachmentByForestryTypeId(Long umTypeId) {
		attachmentDao.deleteAttachmentByForestryTypeId(umTypeId);
	}

	//@Override
	public List<Object[]> querySensorList() {
		return attachmentDao.querySensorList();
	}

}
