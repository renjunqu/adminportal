package cn.futuremove.adminportal.dao.impl;

import cn.futuremove.adminportal.dao.DictDao;
import cn.futuremove.adminportal.model.Dict;
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
