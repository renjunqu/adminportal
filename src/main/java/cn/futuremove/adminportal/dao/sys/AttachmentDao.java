package cn.futuremove.adminportal.dao.sys;

import java.util.List;

import cn.futuremove.adminportal.model.sys.Attachment;

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
