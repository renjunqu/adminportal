package cn.futuremove.adminportal.service;

import java.util.List;

import cn.futuremove.adminportal.model.Information;

import cn.futuremove.adminportal.core.service.Service;

/**
 *
 *
 */
public interface InformationService extends Service<Information> {

	List<Information> queryInformationHTMLList(List<Information> resultList);

	void indexingInformation();

	List<Information> queryByInformationName(String name);

}
