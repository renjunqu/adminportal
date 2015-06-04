package cn.futuremove.adminportal.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.futuremove.adminportal.dao.sys.DictDao;
import cn.futuremove.adminportal.model.sys.Dict;
import cn.futuremove.adminportal.service.sys.DictService;
import org.springframework.stereotype.Service;

import cn.futuremove.adminportal.core.service.BaseService;

/**
 *
 *
 */
@Service
public class DictServiceImpl extends BaseService<Dict> implements DictService {

	private DictDao dictDao;

	@Resource
	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
		this.dao = dictDao;
	}

	//@Override
	public List<Dict> queryDictWithSubList(List<Dict> resultList) {
		List<Dict> dictList = new ArrayList<Dict>();
		for (Dict entity : resultList) {
			Dict dict = new Dict();
			dict.setId(entity.getId());
			dict.setDictKey(entity.getDictKey());
			dict.setDictValue(entity.getDictValue());
			dict.setSequence(entity.getSequence());
			dict.setParentDictkey(entity.getParentDictkey());
			dictList.add(dict);
		}
		return dictList;
	}

}
