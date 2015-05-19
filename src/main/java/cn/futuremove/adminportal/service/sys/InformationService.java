package cn.futuremove.adminportal.service.sys;

import java.util.List;

import cn.futuremove.adminportal.model.sys.Information;

import core.service.Service;

/**
 *
 *
 */
public interface InformationService extends Service<Information> {

	List<Information> queryInformationHTMLList(List<Information> resultList);

	void indexingInformation();

	List<Information> queryByInformationName(String name);

}
