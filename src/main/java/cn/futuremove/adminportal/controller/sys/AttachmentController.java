package cn.futuremove.adminportal.controller.sys;

import javax.annotation.Resource;

import cn.futuremove.adminportal.model.Attachment;
import cn.futuremove.adminportal.service.AttachmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.futuremove.adminportal.core.JavaEEFrameworkBaseController;

/**
 *
 *
 */
@Controller
@RequestMapping("/sys/attachment")
public class AttachmentController extends JavaEEFrameworkBaseController<Attachment> {

	@Resource
	private AttachmentService attachmentService;

}
