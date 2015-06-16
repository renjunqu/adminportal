package cn.futuremove.adminportal.core.main;

import cn.futuremove.adminportal.model.RoleAuthority;
import cn.futuremove.adminportal.service.RoleAuthorityService;
import cn.futuremove.adminportal.service.impl.RoleAuthorityServiceImpl;
import cn.futuremove.adminportal.util.ApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by figoxu on 15/4/20.
 */
public class PermissionInitMain extends BaseMain{

    final static Logger logger = LoggerFactory.getLogger(PermissionInitMain.class);


    public static void main(String[] args) {
        initSpring();
        JdbcTemplate jdbcTemplate = ApplicationContextUtil.getBean(JdbcTemplate.class);

        List<String> menuCodeList = jdbcTemplate.queryForList("select DISTINCT menu_code from authority", String.class);
        for(String menuCode:menuCodeList){
            logger.trace(menuCode);
        }

        List<String> roleList = jdbcTemplate.queryForList("select role_key from role", String.class);
        for(String role:roleList){
            logger.trace(role);
        }
        String sql = "truncate table role_authority";
        jdbcTemplate.execute(sql);

        RoleAuthorityService roleAuthorityService = ApplicationContextUtil.getBean(RoleAuthorityServiceImpl.class);

        for(String roleKey :roleList){
            for(String menuCode:menuCodeList){
                RoleAuthority roleAuthority = new RoleAuthority();
                roleAuthority.setMenuCode(menuCode);
                roleAuthority.setRoleKey(roleKey);
                roleAuthorityService.persist(roleAuthority);
            }
        }

        System.exit(-1);
    }

}
