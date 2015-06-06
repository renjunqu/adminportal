package cn.futuremove.adminportal.dao;

import java.util.List;

import cn.futuremove.adminportal.model.Information;

import cn.futuremove.adminportal.core.dao.Dao;

/**
 *
 *
 */
public interface InformationDao extends Dao<Information> {

	void indexingInformation();

	List<Information> queryByInformationName(String name);

}
