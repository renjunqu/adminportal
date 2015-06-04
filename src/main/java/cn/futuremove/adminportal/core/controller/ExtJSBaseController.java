package cn.futuremove.adminportal.core.controller;

import cn.futuremove.adminportal.core.service.Service;
import cn.futuremove.adminportal.core.support.ExtJSBaseParameter;
import cn.futuremove.adminportal.core.support.ListView;
import cn.futuremove.adminportal.core.support.QueryResult;
import cn.futuremove.adminportal.core.web.CustomDateEditor;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class ExtJSBaseController<E extends ExtJSBaseParameter> {
    public static final String CMD_EDIT = "edit";
    public static final String CMD_NEW = "new";
    public static final String MODEL = "model";
    protected String idField;
    protected String statusField;
    protected static final String separator = "/";
    protected Service<E> service;
    protected static ObjectMapper mapper = new ObjectMapper();
    protected static JsonFactory factory;

    static {
        factory = mapper.getJsonFactory();
    }

    public ExtJSBaseController() {
    }

    @RequestMapping(
            value = {"/list"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void doList(@ModelAttribute E entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.beforeList(entity);
        QueryResult qr = this.service.doPaginationQuery(entity);
        ListView clv = new ListView();
        clv.setData(qr.getResultList());
        this.writeJSON(response, (Object)clv);
    }

    @RequestMapping({"/save"})
    public void doSave(E entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Date now = new Date();
        if("edit".equals(entity.getCmd())) {
            this.beforeSaveUpdate(entity);

            try {
                BeanUtils.setProperty(entity, "updateTime", now);
            } catch (Exception var8) {
                var8.printStackTrace();
            }

            this.service.update(entity);
        } else if("new".equals(entity.getCmd())) {
            this.beforeSaveNew(entity);

            try {
                BeanUtils.setProperty(entity, "createTime", now);
                BeanUtils.setProperty(entity, "updateTime", now);
            } catch (Exception var7) {
                var7.printStackTrace();
            }

            this.service.persist(entity);
        }

        entity.setCmd("edit");
        entity.setSuccess(Boolean.valueOf(true));
        this.writeJSON(response, (Object)entity);
    }

    @RequestMapping({"/content/{id}"})
    public void doContent(@PathVariable("id") Serializable id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Class c = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Class returnClass = null;

        try {
            returnClass = c.getMethod("get" + this.idField.substring(0, 1).toUpperCase() + this.idField.substring(1), new Class[0]).getReturnType();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        ExtJSBaseParameter entity = (ExtJSBaseParameter)this.service.get((Serializable)ConvertUtils.convert(id, returnClass));
        entity.setSuccess(Boolean.valueOf(true));
        entity.setCmd("edit");
        this.writeJSON(response, (Object)entity);
    }

    @RequestMapping({"/delete"})
    public void doDelete(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Serializable[] ids) throws IOException {
        this.service.updateByProperties(this.idField, ids, new String[]{this.statusField}, new Object[]{Integer.valueOf(-1)});
        this.writeJSON(response, "{success:true}");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor());
    }

    protected void writeJSON(HttpServletResponse response, String json) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void writeJSON(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        JsonGenerator responseJsonGenerator = factory.createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
        responseJsonGenerator.writeObject(obj);
    }

    protected void beforeSaveNew(E example) {
    }

    protected void beforeSaveUpdate(E example) {
    }

    protected void beforeList(E example) {
    }
}
