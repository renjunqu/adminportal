package cn.futuremove.adminportal.controller;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by figoxu on 15/4/23.
 */
public class BaseController {


    protected static ObjectMapper mapper = new ObjectMapper();
    protected static JsonFactory factory;

    static {
        factory = mapper.getJsonFactory();
    }


    protected void writeJSON(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        JsonGenerator responseJsonGenerator = factory.createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
        responseJsonGenerator.writeObject(obj);
    }

}
