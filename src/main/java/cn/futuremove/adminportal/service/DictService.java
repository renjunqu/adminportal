package cn.futuremove.adminportal.service;

import java.util.List;

import cn.futuremove.adminportal.model.Dict;

import cn.futuremove.adminportal.core.service.Service;

/**
 *
 *
 */
public interface DictService extends Service<Dict> {

	List<Dict> queryDictWithSubList(List<Dict> resultList);

}
