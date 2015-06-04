package cn.futuremove.adminportal.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.futuremove.adminportal.dao.sys.InformationDao;
import cn.futuremove.adminportal.model.sys.Information;
import cn.futuremove.adminportal.service.sys.InformationService;
import org.springframework.stereotype.Service;

import cn.futuremove.adminportal.core.service.BaseService;
import cn.futuremove.adminportal.core.util.HtmlUtils;

/**
 *
 *
 */
@Service
public class InformationServiceImpl extends BaseService<Information> implements InformationService {

	private InformationDao informationDao;

	@Resource
	public void setInformationDao(InformationDao informationDao) {
		this.informationDao = informationDao;
		this.dao = informationDao;
	}

	//@Override
	public List<Information> queryInformationHTMLList(List<Information> resultList) {
		List<Information> informationList = new ArrayList<Information>();
		for (Information entity : resultList) {
			Information information = new Information();
			information.setId(entity.getId());
			information.setTitle(entity.getTitle());
			information.setAuthor(entity.getAuthor());
			information.setRefreshTime(entity.getRefreshTime());
			information.setContent(entity.getContent());
			information.setContentNoHTML(HtmlUtils.htmltoText(entity.getContent()));
			informationList.add(information);
		}
		return informationList;
	}

	//@Override
	public void indexingInformation() {
		informationDao.indexingInformation();
	}

	//@Override
	public List<Information> queryByInformationName(String name) {
		return informationDao.queryByInformationName(name);
	}

}
