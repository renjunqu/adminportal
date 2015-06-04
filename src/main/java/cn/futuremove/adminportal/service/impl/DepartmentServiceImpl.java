package cn.futuremove.adminportal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.futuremove.adminportal.dao.DepartmentDao;
import cn.futuremove.adminportal.model.sys.Department;
import cn.futuremove.adminportal.service.DepartmentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.futuremove.adminportal.core.service.BaseService;

/**
 *
 *
 */
@Service
public class DepartmentServiceImpl extends BaseService<Department> implements DepartmentService {

	private DepartmentDao departmentDao;

	@Resource
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
		this.dao = departmentDao;
	}

//	//@Override
	public List<Department> queryDepartmentCnList(List<Department> resultList) {
		List<Department> departmentList = new ArrayList<Department>();
		for (Department entity : resultList) {
			Department department = new Department();
			department.setId(entity.getId());
			department.setDepartmentKey(entity.getDepartmentKey());
			department.setDepartmentValue(entity.getDepartmentValue());
			department.setParentDepartmentkey(entity.getParentDepartmentkey());
			if (StringUtils.isNotBlank(department.getParentDepartmentkey())) {
				Department d = departmentDao.getByProerties("departmentKey", department.getParentDepartmentkey());
				department.setParentDepartmentValue(d.getDepartmentValue());
			}
			department.setDescription(entity.getDescription());
			departmentList.add(department);
		}
		return departmentList;
	}

}
