package cn.futuremove.adminportal.core.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Component("JdbcBaseDao")
public class JdbcBaseDao extends JdbcDaoSupport {

	@Resource(name = "dataSource")
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}

}
