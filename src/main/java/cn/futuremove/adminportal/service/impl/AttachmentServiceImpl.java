package cn.futuremove.adminportal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.futuremove.adminportal.dao.AttachmentDao;
import cn.futuremove.adminportal.service.AttachmentService;
import org.springframework.stereotype.Service;

import cn.futuremove.adminportal.model.Attachment;

import cn.futuremove.adminportal.core.service.BaseService;

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
