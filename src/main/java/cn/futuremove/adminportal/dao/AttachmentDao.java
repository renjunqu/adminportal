package cn.futuremove.adminportal.dao;

import java.util.List;

import cn.futuremove.adminportal.model.Attachment;

import cn.futuremove.adminportal.core.dao.Dao;

/**
 *
 *
 */
public interface AttachmentDao extends Dao<Attachment> {

	List<Object[]> queryFlowerList(String epcId);

	void deleteAttachmentByForestryTypeId(Long umTypeId);

	List<Object[]> querySensorList();

}
