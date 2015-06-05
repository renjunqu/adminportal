package cn.futuremove.adminportal.core.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

/**
 *
 *
 */
@Component("JdbcBaseDao")
public class JdbcBaseDao extends JdbcDaoSupport {

	@Resource(name = "dataSource_sys")
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}

}
