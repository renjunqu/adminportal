package cn.futuremove.adminportal.service.sys;

import java.util.List;

import cn.futuremove.adminportal.model.sys.Attachment;

import core.service.Service;

/**
 *
 *
 */
public interface AttachmentService extends Service<Attachment> {

	List<Object[]> queryFlowerList(String epcId);

	void deleteAttachmentByForestryTypeId(Long umTypeId);

	List<Object[]> querySensorList();

}
