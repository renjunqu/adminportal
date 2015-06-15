//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.futuremove.adminportal.core.dao;

import cn.futuremove.adminportal.core.dao.Dao;
import cn.futuremove.adminportal.core.support.BaseParameter;
import cn.futuremove.adminportal.core.support.QueryResult;
import cn.futuremove.adminportal.core.util.BeanUtils;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class BaseDao<E> implements Dao<E> {
    protected final Logger log = Logger.getLogger(BaseDao.class);
    private static Map<String, Method> MAP_METHOD = new HashMap();
    private SessionFactory sessionFactory;
    protected Class<E> entityClass;

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Resource(
            name = "sessionFactory"
    )
    public void setSF(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    public BaseDao(Class<E> entityClass) {
        this.entityClass = entityClass;
        
    }

    public void persist(E entity) {
        this.getSession().save(entity);
    }

    public boolean deleteByPK(Serializable... id) {
        boolean result = false;
        if(id != null && id.length > 0) {
            for(int i = 0; i < id.length; ++i) {
                Object entity = this.get(id[i]);
                if(entity != null) {
                    this.getSession().delete(entity);
                    result = true;
                }
            }
        }

        return result;
    }

    public void deleteByProperties(String[] propName, Object[] propValue) {
        if(propName != null && propName.length > 0 && propValue != null && propValue.length > 0 && propValue.length == propName.length) {
            StringBuffer sb = new StringBuffer("delete from " + this.entityClass.getName() + " o where 1=1 ");
            this.appendQL(sb, propName, propValue);
            Query query = this.getSession().createQuery(sb.toString());
            this.setParameter(query, propName, propValue);
            query.executeUpdate();
        }

    }

    public void delete(E entity) {
        this.getSession().delete(entity);
    }

    public void deleteByProperties(String propName, Object propValue) {
        this.deleteByProperties(new String[]{propName}, new Object[]{propValue});
    }

    public void updateByProperties(String[] conditionName, Object[] conditionValue, String[] propertyName, Object[] propertyValue) {
        if(propertyName != null && propertyName.length > 0 && propertyValue != null && propertyValue.length > 0 && propertyName.length == propertyValue.length && conditionValue != null && conditionValue.length > 0) {
            StringBuffer sb = new StringBuffer();
            sb.append("update " + this.entityClass.getName() + " o set ");

            for(int query = 0; query < propertyName.length; ++query) {
                sb.append(propertyName[query] + " = :p_" + propertyName[query] + ",");
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append(" where 1=1 ");
            this.appendQL(sb, conditionName, conditionValue);
            Query var8 = this.getSession().createQuery(sb.toString());

            for(int i = 0; i < propertyName.length; ++i) {
                var8.setParameter("p_" + propertyName[i], propertyValue[i]);
            }

            this.setParameter(var8, conditionName, conditionValue);
            var8.executeUpdate();
        } else {
            throw new IllegalArgumentException("Method updateByProperties in BaseDao argument is illegal!");
        }
    }

    public void updateByProperties(String[] conditionName, Object[] conditionValue, String propertyName, Object propertyValue) {
        this.updateByProperties(conditionName, conditionValue, new String[]{propertyName}, new Object[]{propertyValue});
    }

    public void updateByProperties(String conditionName, Object conditionValue, String[] propertyName, Object[] propertyValue) {
        this.updateByProperties(new String[]{conditionName}, new Object[]{conditionValue}, propertyName, propertyValue);
    }

    public void updateByProperties(String conditionName, Object conditionValue, String propertyName, Object propertyValue) {
        this.updateByProperties(new String[]{conditionName}, new Object[]{conditionValue}, new String[]{propertyName}, new Object[]{propertyValue});
    }

    public void update(E entity) {
        this.getSession().update(entity);
    }

    public void update(E entity, Serializable oldId) {
        this.deleteByPK(new Serializable[]{oldId});
        this.persist(entity);
    }

    public E merge(E entity) {
        return (E)this.getSession().merge(entity);
    }

    public E getByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition) {
        if(propName != null && propName.length > 0 && propValue != null && propValue.length > 0 && propValue.length == propName.length) {
            StringBuffer sb = new StringBuffer("select o from " + this.entityClass.getName() + " o where 1=1 ");
            this.appendQL(sb, propName, propValue);
            if(sortedCondition != null && sortedCondition.size() > 0) {
                sb.append(" order by ");
                Iterator list = sortedCondition.entrySet().iterator();

                while(list.hasNext()) {
                    Entry query = (Entry)list.next();
                    sb.append((String)query.getKey() + " " + (String)query.getValue() + ",");
                }

                sb.deleteCharAt(sb.length() - 1);
            }

            Query query1 = this.getSession().createQuery(sb.toString());
            this.setParameter(query1, propName, propValue);
            List list1 = query1.list();
            if(list1 != null && list1.size() > 0) {
                return (E)list1.get(0);
            }
        }

        return null;
    }

    public E get(Serializable id) {
        return (E)this.getSession().get(this.entityClass, id);
    }

    public E load(Serializable id) {
        return (E)this.getSession().load(this.entityClass, id);
    }

    public E getByProerties(String[] propName, Object[] propValue) {
        return (E)this.getByProerties((String[])propName, (Object[])propValue, (Map)null);
    }

    public E getByProerties(String propName, Object propValue) {
        return this.getByProerties(new String[]{propName}, new Object[]{propValue});
    }

    public E getByProerties(String propName, Object propValue, Map<String, String> sortedCondition) {
        return this.getByProerties(new String[]{propName}, new Object[]{propValue}, sortedCondition);
    }

    public List<E> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition, Integer top) {
        if(propName != null && propValue != null && propValue.length == propName.length) {
            StringBuffer sb = new StringBuffer("select o from " + this.entityClass.getName() + " o where 1=1 ");
            this.appendQL(sb, propName, propValue);
            if(sortedCondition != null && sortedCondition.size() > 0) {
                sb.append(" order by ");
                Iterator var7 = sortedCondition.entrySet().iterator();

                while(var7.hasNext()) {
                    Entry query = (Entry)var7.next();
                    sb.append((String)query.getKey() + " " + (String)query.getValue() + ",");
                }

                sb.deleteCharAt(sb.length() - 1);
            }

            Query query1 = this.getSession().createQuery(sb.toString());
            this.setParameter(query1, propName, propValue);
            if(top != null) {
                query1.setFirstResult(0);
                query1.setMaxResults(top.intValue());
            }

            return query1.list();
        } else {
            return null;
        }
    }

    public List<E> queryByProerties(String[] propName, Object[] propValue, Integer top) {
        return this.queryByProerties((String[])propName, (Object[])propValue, (Map)null, top);
    }

    public List<E> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition) {
        return this.queryByProerties((String[])propName, (Object[])propValue, sortedCondition, (Integer)null);
    }

    public List<E> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition, Integer top) {
        return this.queryByProerties(new String[]{propName}, new Object[]{propValue}, sortedCondition, top);
    }

    public List<E> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition) {
        return this.queryByProerties((String[])(new String[]{propName}), (Object[])(new Object[]{propValue}), sortedCondition, (Integer)null);
    }

    public List<E> queryByProerties(String propName, Object propValue, Integer top) {
        return this.queryByProerties((String[])(new String[]{propName}), (Object[])(new Object[]{propValue}), (Map)null, top);
    }

    public List<E> queryByProerties(String[] propName, Object[] propValue) {
        return this.queryByProerties((String[])propName, (Object[])propValue, (Map)null, (Integer)null);
    }

    public List<E> queryByProerties(String propName, Object propValue) {
        return this.queryByProerties((String[])(new String[]{propName}), (Object[])(new Object[]{propValue}), (Map)null, (Integer)null);
    }

    public Long countAll() {
        return (Long)this.getSession().createQuery("select count(*) from " + this.entityClass.getName()).uniqueResult();
    }

    public void clear() {
        this.getSession().clear();
    }

    public void evict(E entity) {
        this.getSession().evict(entity);
    }

    public List<E> doQueryAll(Map<String, String> sortedCondition, Integer top) {
        Criteria criteria = this.getSession().createCriteria(this.entityClass);
        if(sortedCondition != null && sortedCondition.size() > 0) {
            Iterator it = sortedCondition.keySet().iterator();

            while(it.hasNext()) {
                String pm = (String)it.next();
                if("DESC".equals(sortedCondition.get(pm))) {
                    criteria.addOrder(Order.desc(pm));
                } else if("ASC".equals(sortedCondition.get(pm))) {
                    criteria.addOrder(Order.asc(pm));
                }
            }
        }

        if(top != null) {
            criteria.setMaxResults(top.intValue());
            criteria.setFirstResult(0);
        }

        return criteria.list();
    }

    public List<E> doQueryAll() {
        return this.doQueryAll((Map)null, (Integer)null);
    }

    public List<E> doQueryAll(Integer top) {
        return this.doQueryAll((Map)null, top);
    }

    public Long doCount(BaseParameter param) {
        if(param == null) {
            return null;
        } else {
            Criteria criteria = this.getSession().createCriteria(this.entityClass);
            this.processQuery(criteria, param);

            try {
                criteria.setProjection(Projections.rowCount());
                return Long.valueOf(((Number)criteria.uniqueResult()).longValue());
            } catch (Exception var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    public List<E> doQuery(BaseParameter param) {
        if(param == null) {
            return null;
        } else {
            Criteria criteria = this.getSession().createCriteria(this.entityClass);
            this.processQuery(criteria, param);

            try {
                if(param.getSortedConditions() != null && param.getSortedConditions().size() > 0) {
                    Map e = param.getSortedConditions();
                    Iterator it = param.getSortedConditions().keySet().iterator();

                    while(it.hasNext()) {
                        String pm = (String)it.next();
                        if("DESC".equals(e.get(pm))) {
                            criteria.addOrder(Order.desc(pm));
                        } else if("ASC".equals(e.get(pm))) {
                            criteria.addOrder(Order.asc(pm));
                        }
                    }
                }

                return criteria.list();
            } catch (Exception var6) {
                var6.printStackTrace();
                return null;
            }
        }
    }

    public QueryResult<E> doPaginationQuery(BaseParameter param) {
        return this.doPaginationQuery(param, true);
    }

    public QueryResult<E> doPaginationQuery(BaseParameter param, boolean bool) {
        if(param == null) {
            return null;
        } else {
            Criteria criteria = this.getSession().createCriteria(this.entityClass);
            if(bool) {
                this.processQuery(criteria, param);
            } else {
                this.extendprocessQuery(criteria, param);
            }

            try {
                QueryResult e = new QueryResult();
                criteria.setProjection(Projections.rowCount());
                e.setTotalCount(Long.valueOf(((Number)criteria.uniqueResult()).longValue()));
                if(e.getTotalCount().longValue() > 0L) {
                    if(param.getSortedConditions() != null && param.getSortedConditions().size() > 0) {
                        Map map = param.getSortedConditions();
                        Iterator it = param.getSortedConditions().keySet().iterator();

                        while(it.hasNext()) {
                            String pm = (String)it.next();
                            if("DESC".equalsIgnoreCase((String)map.get(pm))) {
                                criteria.addOrder(Order.desc(pm));
                            } else if("ASC".equalsIgnoreCase((String)map.get(pm))) {
                                criteria.addOrder(Order.asc(pm));
                            }
                        }
                    }

                    criteria.setProjection((Projection)null);
                    criteria.setMaxResults(param.getMaxResults().intValue());
                    criteria.setFirstResult(param.getFirstResult().intValue());
                    e.setResultList(criteria.list());
                } else {
                    e.setResultList(new ArrayList());
                }

                return e;
            } catch (Exception var8) {
                var8.printStackTrace();
                return null;
            }
        }
    }

    private void appendQL(StringBuffer sb, String[] propName, Object[] propValue) {
        for(int i = 0; i < propName.length; ++i) {
            String name = propName[i];
            Object value = propValue[i];
            if(!(value instanceof Object[]) && !(value instanceof Collection)) {
                if(value == null) {
                    sb.append(" and o." + name + " is null ");
                } else {
                    sb.append(" and o." + name + "=:" + name.replace(".", ""));
                }
            } else {
                Object[] arraySerializable = (Object[])value;
                if(arraySerializable != null && arraySerializable.length > 0) {
                    sb.append(" and o." + name + " in (:" + name.replace(".", "") + ")");
                }
            }
        }

    }

    private void setParameter(Query query, String[] propName, Object[] propValue) {
        for(int i = 0; i < propName.length; ++i) {
            String name = propName[i];
            Object value = propValue[i];
            if(value != null) {
                if(value instanceof Object[]) {
                    query.setParameterList(name.replace(".", ""), (Object[])value);
                } else if(value instanceof Collection) {
                    query.setParameterList(name.replace(".", ""), (Collection)value);
                } else {
                    query.setParameter(name.replace(".", ""), value);
                }
            }
        }

    }

    protected void buildSorted(BaseParameter param, StringBuffer hql) {
        if(param.getSortedConditions() != null && param.getSortedConditions().size() > 0) {
            hql.append(" order by ");
            Map sorted = param.getSortedConditions();
            Iterator it = sorted.keySet().iterator();

            while(it.hasNext()) {
                String col = (String)it.next();
                hql.append(col + " " + (String)sorted.get(col) + ",");
            }

            hql.deleteCharAt(hql.length() - 1);
        }

    }

    private String transferColumn(String queryCondition) {
        return queryCondition.substring(queryCondition.indexOf(95, 1) + 1);
    }

    protected void setParameter(Map<String, Object> mapParameter, Query query) {
        Iterator it = mapParameter.keySet().iterator();

        while(it.hasNext()) {
            String parameterName = (String)it.next();
            Object value = mapParameter.get(parameterName);
            query.setParameter(parameterName, value);
        }

    }

    protected Map handlerConditions(BaseParameter param) throws Exception {
        Map staticConditions = BeanUtils.describe(param);
        Map dynamicConditions = param.getQueryDynamicConditions();
        if(dynamicConditions.size() > 0) {
            Iterator it = staticConditions.keySet().iterator();

            while(it.hasNext()) {
                String key = (String)it.next();
                Object value = staticConditions.get(key);
                if(key.startsWith("$") && value != null && !"".equals(value)) {
                    dynamicConditions.put(key, value);
                }
            }

            staticConditions = dynamicConditions;
        }

        return staticConditions;
    }

    private Method getMethod(String name) {
        if(!MAP_METHOD.containsKey(name)) {
            Class clazz = Restrictions.class;
            Class[] paramType = new Class[]{String.class, Object.class};
            Class[] likeParamType = new Class[]{String.class, String.class, MatchMode.class};
            Class[] isNullType = new Class[]{String.class};

            try {
                Method e = null;
                if("like".equals(name)) {
                    e = clazz.getMethod(name, likeParamType);
                } else if("isNull".equals(name)) {
                    e = clazz.getMethod(name, isNullType);
                } else {
                    e = clazz.getMethod(name, paramType);
                }

                MAP_METHOD.put(name, e);
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        }

        return (Method)MAP_METHOD.get(name);
    }

    private Method getExtendMethod(String name) {
        if(!MAP_METHOD.containsKey(name)) {
            Class clazz = Restrictions.class;
            Class[] paramType = new Class[]{String.class, Object.class};
            Class[] likeParamType = new Class[]{String.class, String.class, MatchMode.class};
            Class[] isNullType = new Class[]{String.class};

            try {
                Method e = null;
                if("like".equals(name)) {
                    e = clazz.getMethod(name, likeParamType);
                } else if("isNull".equals(name)) {
                    e = clazz.getMethod(name, isNullType);
                } else if(!"IN".equals(name.toUpperCase())) {
                    e = clazz.getMethod(name, paramType);
                }

                MAP_METHOD.put(name, e);
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        }

        return (Method)MAP_METHOD.get(name);
    }

    private String getOpt(String value) {
        return value.substring(0, value.indexOf(95, 1)).substring(1);
    }

    private String getPropName(String value) {
        return value.substring(value.indexOf(95, 1) + 1);
    }

    private void processQuery(Criteria criteria, BaseParameter param) {
        try {
            Map e = BeanUtils.describeAvailableParameter(param);
            Map dynamicConditionMap = param.getQueryDynamicConditions();
            Disjunction disjunction = Restrictions.disjunction();
            String pn;
            if(e != null && e.size() > 0) {
                Iterator map = e.entrySet().iterator();

                label124:
                while(true) {
                    while(true) {
                        Entry bean;
                        Object e1;
                        do {
                            do {
                                if(!map.hasNext()) {
                                    break label124;
                                }

                                bean = (Entry)map.next();
                                e1 = bean.getValue();
                            } while(e1 == null);
                        } while(e1 instanceof String && "".equals((String)e1));

                        String prop = this.getPropName((String)bean.getKey());
                        pn = this.getOpt((String)bean.getKey());
                        Method prop1 = this.getMethod(pn);
                        if("like".equals(pn)) {
                            if(param.getFlag().equals("OR")) {
                                criteria.add(disjunction.add((Criterion)prop1.invoke(Restrictions.class, new Object[]{prop, e1, MatchMode.ANYWHERE})));
                            } else {
                                criteria.add((Criterion)prop1.invoke(Restrictions.class, new Object[]{prop, e1, MatchMode.ANYWHERE}));
                            }
                        } else if("isNull".equals(pn) && e1 instanceof Boolean) {
                            if(((Boolean)e1).booleanValue()) {
                                if(param.getFlag().equals("OR")) {
                                    criteria.add(disjunction.add(Restrictions.isNull(prop)));
                                } else {
                                    criteria.add(Restrictions.isNull(prop));
                                }
                            } else if(param.getFlag().equals("OR")) {
                                criteria.add(disjunction.add(Restrictions.isNotNull(prop)));
                            } else {
                                criteria.add(Restrictions.isNotNull(prop));
                            }
                        } else if(param.getFlag().equals("OR")) {
                            criteria.add(disjunction.add((Criterion)prop1.invoke(Restrictions.class, new Object[]{prop, e1})));
                        } else {
                            criteria.add((Criterion)prop1.invoke(Restrictions.class, new Object[]{prop, e1}));
                        }
                    }
                }
            }

            if(dynamicConditionMap != null && dynamicConditionMap.size() > 0) {
                Object bean1 = this.entityClass.newInstance();
                HashMap map1 = new HashMap();
                Iterator prop2 = dynamicConditionMap.entrySet().iterator();

                Entry e2;
                while(prop2.hasNext()) {
                    e2 = (Entry)prop2.next();
                    map1.put(this.getPropName((String)e2.getKey()), e2.getValue());
                }

                org.apache.commons.beanutils.BeanUtils.populate(bean1, map1);
                prop2 = dynamicConditionMap.entrySet().iterator();

                while(true) {
                    while(true) {
                        String methodName;
                        Method m;
                        Object value;
                        String prop3;
                        do {
                            do {
                                if(!prop2.hasNext()) {
                                    return;
                                }

                                e2 = (Entry)prop2.next();
                                pn = (String)e2.getKey();
                                prop3 = this.getPropName(pn);
                                methodName = this.getOpt(pn);
                                m = this.getMethod(methodName);
                                value = PropertyUtils.getNestedProperty(bean1, prop3);
                            } while(value == null);
                        } while(value instanceof String && "".equals((String)value));

                        if("like".equals(methodName)) {
                            if(param.getFlag().equals("OR")) {
                                criteria.add(disjunction.add((Criterion)m.invoke(Restrictions.class, new Object[]{prop3, value, MatchMode.ANYWHERE})));
                            } else {
                                criteria.add((Criterion)m.invoke(Restrictions.class, new Object[]{prop3, value, MatchMode.ANYWHERE}));
                            }
                        } else if("isNull".equals(methodName) && value instanceof Boolean) {
                            if(((Boolean)value).booleanValue()) {
                                if(param.getFlag().equals("OR")) {
                                    criteria.add(disjunction.add(Restrictions.isNull(prop3)));
                                } else {
                                    criteria.add(Restrictions.isNull(prop3));
                                }
                            } else if(param.getFlag().equals("OR")) {
                                criteria.add(disjunction.add(Restrictions.isNotNull(prop3)));
                            } else {
                                criteria.add(Restrictions.isNotNull(prop3));
                            }
                        } else if(param.getFlag().equals("OR")) {
                            criteria.add(disjunction.add((Criterion)m.invoke(Restrictions.class, new Object[]{prop3, value})));
                        } else {
                            criteria.add((Criterion)m.invoke(Restrictions.class, new Object[]{prop3, value}));
                        }
                    }
                }
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        }

    }

    private void extendprocessQuery(Criteria criteria, BaseParameter param) {
        try {
            Map e = BeanUtils.describeAvailableParameter(param);
            Map dynamicConditionMap = param.getQueryDynamicConditions();
            String pn;
            if(e != null && e.size() > 0) {
                Iterator map = e.entrySet().iterator();

                label108:
                while(true) {
                    while(true) {
                        Entry bean;
                        Object e1;
                        do {
                            do {
                                if(!map.hasNext()) {
                                    break label108;
                                }

                                bean = (Entry)map.next();
                                e1 = bean.getValue();
                            } while(e1 == null);
                        } while(e1 instanceof String && "".equals((String)e1));

                        String prop = this.getPropName((String)bean.getKey());
                        pn = this.getOpt((String)bean.getKey());
                        Method prop1 = this.getExtendMethod(pn);
                        if("like".equals(pn)) {
                            criteria.add((Criterion)prop1.invoke(Restrictions.class, new Object[]{prop, e1, MatchMode.ANYWHERE}));
                        } else if("isNull".equals(pn) && e1 instanceof Boolean) {
                            if(((Boolean)e1).booleanValue()) {
                                criteria.add(Restrictions.isNull(prop));
                            } else {
                                criteria.add(Restrictions.isNotNull(prop));
                            }
                        } else if(e1 != null && e1 instanceof Object[] && "IN".equals(pn.toUpperCase())) {
                            Object[] methodName = (Object[])e1;
                            criteria.add(Restrictions.in(prop, methodName));
                        } else {
                            criteria.add((Criterion)prop1.invoke(Restrictions.class, new Object[]{prop, e1}));
                        }
                    }
                }
            }

            if(dynamicConditionMap != null && dynamicConditionMap.size() > 0) {
                Object bean1 = this.entityClass.newInstance();
                HashMap map1 = new HashMap();
                Iterator prop2 = dynamicConditionMap.entrySet().iterator();

                Entry e2;
                while(prop2.hasNext()) {
                    e2 = (Entry)prop2.next();
                    map1.put(this.getPropName((String)e2.getKey()), e2.getValue());
                }

                org.apache.commons.beanutils.BeanUtils.populate(bean1, map1);
                prop2 = dynamicConditionMap.entrySet().iterator();

                while(true) {
                    while(true) {
                        Method m;
                        Object value;
                        String methodName1;
                        String prop3;
                        do {
                            do {
                                if(!prop2.hasNext()) {
                                    return;
                                }

                                e2 = (Entry)prop2.next();
                                pn = (String)e2.getKey();
                                prop3 = this.getPropName(pn);
                                methodName1 = this.getOpt(pn);
                                m = this.getMethod(methodName1);
                                value = PropertyUtils.getNestedProperty(bean1, prop3);
                            } while(value == null);
                        } while(value instanceof String && "".equals((String)value));

                        if("like".equals(methodName1)) {
                            criteria.add((Criterion)m.invoke(Restrictions.class, new Object[]{prop3, value, MatchMode.ANYWHERE}));
                        } else if("isNull".equals(methodName1) && value instanceof Boolean) {
                            if(((Boolean)value).booleanValue()) {
                                criteria.add(Restrictions.isNull(prop3));
                            } else {
                                criteria.add(Restrictions.isNotNull(prop3));
                            }
                        } else {
                            criteria.add((Criterion)m.invoke(Restrictions.class, new Object[]{prop3, value}));
                        }
                    }
                }
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

    }
}
