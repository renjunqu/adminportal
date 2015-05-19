package cn.futuremove.adminportal.dao.sys;

import java.util.List;

import cn.futuremove.adminportal.model.sys.Information;

import core.dao.Dao;

/**
 *
 *
 */
public interface InformationDao extends Dao<Information> {

	void indexingInformation();

	List<Information> queryByInformationName(String name);

}
