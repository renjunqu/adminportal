package cn.futuremove.adminportal.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by figoxu on 15/4/29.
 */
public class IdImageServlet  extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * @should test
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mobileNo= request.getParameter("MobileNo");
        String type = request.getParameter("type");
        response.setContentType("image/gif");

        String field = null;
        if("1".equals(type)){
            field = "IdAuthInfo";
        }else{
            field = "IdAuthInfo_back";
        }
        Blob blob = ImageHelper.searchBlob("select "+field+" from JOY_IdAuthInfo where MobileNo="+mobileNo,field);
        byte[] bytes = null;
        try {
            if(blob!=null) {
                bytes = IOUtils.toByteArray(blob.getBinaryStream());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(bytes==null){
            processDefaultImage(request,response,type);
        }else{
            ImageHelper.processBytesImage(request,response,bytes);
        }
    }

    private void processDefaultImage(HttpServletRequest request, HttpServletResponse response, String type) throws IOException {
        String realPath = null;
        if("1".equals(type)){
            realPath = request.getRealPath("/static/assets/images/commons/default/idCard_A.png");
        }else{
            realPath = request.getRealPath("/static/assets/images/commons/default/idCard_B.png");
        }
        ServletOutputStream outputStream = response.getOutputStream();
        FileInputStream fis = new FileInputStream(realPath);
        byte[] bytes = new byte[1024];
        while (fis.read(bytes)!=-1){
            outputStream.write(bytes);
        }
        outputStream.close();
    }


}
