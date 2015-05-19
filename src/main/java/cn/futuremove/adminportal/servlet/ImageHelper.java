package cn.futuremove.adminportal.servlet;

import cn.futuremove.adminportal.util.ApplicationContextUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by figoxu on 15/4/29.
 */
public class ImageHelper {

    public static Blob searchBlob(final String sql,final String field) {
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ApplicationContextUtil.getBean("jdbcTemplate_joyMove");
        Blob blob = null;
        try {
            blob = jdbcTemplate.queryForObject(sql, new RowMapper<Blob>() {
                public Blob mapRow(ResultSet resultSet, int i) throws SQLException {
                    Blob blobValue = resultSet.getBlob(field);
                    return blobValue;
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return blob;
    }


    public static void processBytesImage(HttpServletRequest request, HttpServletResponse response, byte[] bytes) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.close();
    }
}
