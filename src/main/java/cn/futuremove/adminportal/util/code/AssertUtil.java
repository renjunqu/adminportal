package cn.futuremove.adminportal.util.code;

import java.util.Collection;
import java.util.Map;

/**
 * Created by figoxu on 15/4/13.
 */
public class AssertUtil {

    public static boolean notEmpty(Object[] array) {
        return (array != null) && (array.length > 0);
    }

    public static boolean notEmpty(Collection<?> collection) {
        return (collection != null) && (!collection.isEmpty());
    }

    public static boolean notEmpty(Map<?,?> map) {
        return (map != null) && (!map.isEmpty());
    }
}
