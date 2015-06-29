package cn.futuremove.adminportal.core.main;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by figoxu on 15/4/13.
 */
public class BaseMain {
    private static Logger logger = Logger.getLogger(BaseMain.class);
    protected static ApplicationContext factory;

    private static boolean initFlag = false;

    public static void initSpring() {
        if(initFlag){
            return;
        }
        initFlag = true;
        factory = new ClassPathXmlApplicationContext(
                new String[]{
                        "spring_config/applicationContext-mvc.xml"
                   //     , "spring_config/applicationContext-security.xml"
                   //     , "spring_config/applicationContext-mongo.xml"
                }
        );

    }


}
