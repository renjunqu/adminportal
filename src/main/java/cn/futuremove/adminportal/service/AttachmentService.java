package cn.futuremove.adminportal.service;

import java.util.List;

import cn.futuremove.adminportal.model.Attachment;

import cn.futuremove.adminportal.core.service.Service;

/**
 *
 *
 */
public interface AttachmentService extends Service<Attachment> {

	List<Object[]> queryFlowerList(String epcId);

	void deleteAttachmentByForestryTypeId(Long umTypeId);

	List<Object[]> querySensorList();

}
