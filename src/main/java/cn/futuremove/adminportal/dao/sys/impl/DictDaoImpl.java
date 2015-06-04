package cn.futuremove.adminportal.dao.sys.impl;

import cn.futuremove.adminportal.dao.sys.DictDao;
import cn.futuremove.adminportal.model.sys.Dict;
import org.springframework.stereotype.Repository;

import cn.futuremove.adminportal.core.dao.BaseDao;

/**
 *
 *
 */
@Repository
public class DictDaoImpl extends BaseDao<Dict> implements DictDao {

	public DictDaoImpl() {
		super(Dict.class);
	}

}
