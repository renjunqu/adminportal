package cn.futuremove.adminportal.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * User: figo.xu
 * Date: 12-9-26
 * Time: 上午9:52
 */
public class ApplicationContextUtil implements ApplicationContextAware {
    private static Logger logger = Logger.getLogger(ApplicationContextUtil.class);

    private static ApplicationContext applicationContext = null;

    public ApplicationContextUtil() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

//    private
    public
    static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * @param clazz
     * @return
     * @should test
     */
    public static <T extends Object> T getBean(Class<T> clazz) {
        String defaultBeanName = clazz.getSimpleName();
        defaultBeanName = defaultBeanName.substring(0, 1).toLowerCase() + defaultBeanName.substring(1);

        Controller controller = (Controller) clazz.getAnnotation(Controller.class);
        if (controller != null) {
            String value = controller.value();
            if (!StringUtils.isBlank(value)) {
                return (T) getBean(value);
            }
        }
        Component component = (Component) clazz.getAnnotation(Component.class);
        if (component != null) {
            String value = component.value();
            if (StringUtils.isBlank(value)) {
                value = defaultBeanName;
            }
            return (T) getBean(value);
        }
        return (T) getBean(defaultBeanName);
    }

    public static Object getBean(String name, Object[] parameters) {
        return applicationContext.getBean(name, parameters);
    }
}
