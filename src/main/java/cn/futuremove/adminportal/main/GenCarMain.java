package cn.futuremove.adminportal.main;

import cn.futuremove.adminportal.util.ApplicationContextUtil;
import cn.futuremove.adminportal.util.GsonInstance;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by figoxu on 15/4/24.
 */
public class GenCarMain extends BaseMain{

    public static void main(String[] args) {
        initSpring();
        JdbcTemplate jdbcTemplate_joyMove = (JdbcTemplate) ApplicationContextUtil.getBean("jdbcTemplate_joyMove");
        Double[][] position = {
            {116.384439,39.921586}
//            ,{116.412506,39.900255}
        };
        for(Double[] positionVal:position){
            String sql = "insert into JOY_Car(positionX,positionY,desp,state) " +
                    "values( "+positionVal[0]+", "+positionVal[1]+",'京F001go',0);";
            jdbcTemplate_joyMove.execute(sql);
        }
        System.exit(-1);
    }

}
