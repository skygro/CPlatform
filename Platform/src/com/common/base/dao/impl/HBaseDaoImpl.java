package com.common.base.dao.impl;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.common.base.dao.HBaseDao;
import com.common.util.exception.DaoException;
import com.common.util.page.Pager;
import com.sun.org.apache.commons.beanutils.PropertyUtils;

public class HBaseDaoImpl<T> extends HibernateDaoSupport implements HBaseDao{
	  private static Logger log = LoggerFactory.getLogger("info");
	  private static final boolean ISCACHE = true;

	  private int getStart(Map p, int pageSize)
	  {
	    int start = 0;
	    if ((p.get("pageNo") != null) && (!"".equals(p.get("pageNo"))) && 
	      (!"0".equals(p.get("pageNo"))))
	      start = (Integer.parseInt(String.valueOf(p.get("pageNo"))) - 1) * 
	        pageSize;
	    else if ((p.get("start") != null) && (!"".equals(p.get("start"))) && 
	      (!"0".equals(p.get("start"))))
	      start = Integer.parseInt(String.valueOf(p.get("start")));
	    else if ((p.get("page") != null) && (!"".equals(p.get("page"))) && 
	      (!"0".equals(p.get("page")))) {
	      start = (Integer.parseInt(String.valueOf(p.get("page"))) - 1) * 
	        pageSize;
	    }
	    return start;
	  }

	  private int getPageSize(Map p) {
	    int pageSize = 0;
	    if ((p.get("pageSize") != null) && (!"".equals(p.get("pageSize"))) && 
	      (!"0".equals(p.get("pageSize"))))
	      pageSize = Integer.parseInt(String.valueOf(p.get("pageSize")));
	    else if ((p.get("limit") != null) && (!"".equals(p.get("limit"))) && 
	      (!"0".equals(p.get("limit"))))
	      pageSize = Integer.parseInt(String.valueOf(p.get("limit")));
	    else if ((p.get("pagesize") != null) && (!"".equals(p.get("pagesize"))) && 
	      (!"0".equals(p.get("pagesize"))))
	      pageSize = Integer.parseInt(String.valueOf(p.get("pagesize")));
	    else {
	      pageSize = Pager.limit;
	    }
	    return pageSize;
	  }

	  public Session getCurrentSession() {
	    return super.getSession();
	  }

	  public Connection getConnection()
	    throws DaoException
	  {
	    return getSession().connection();
	  }

	  public Serializable getId(Object entity, Class entityClass)
	    throws DaoException
	  {
	    try
	    {
	      return (Serializable)PropertyUtils.getProperty(entity, 
	        getIdName(entityClass));
	    } catch (Exception e) {
	      throw new DaoException(e);
	    }
	  }

	  public String getIdName(Class entityClass)
	    throws DaoException
	  {
	    ClassMetadata meta = getSession().getSessionFactory().getClassMetadata(
	      entityClass);
	    return meta.getIdentifierPropertyName();
	  }

	  public Object findUnique(Class entityClass, String name, Object value)
	    throws DaoException
	  {
	    Criteria criteria = createCriteria(entityClass, new Criterion[] { Restrictions.eq(name, 
	      value) });
	    return criteria.uniqueResult();
	  }

	  public List findAll(Class clazz)
	    throws DaoException
	  {
	    return getHibernateTemplate().find("from " + clazz.getName());
	  }

	  public Object load(Class clazz, Serializable id)
	    throws DaoException
	  {
	    return getHibernateTemplate().load(clazz, id);
	  }

	  public Object get(Class clazz, Serializable id)
	    throws DaoException
	  {
	    return getHibernateTemplate().get(clazz, id);
	  }

	  public List find(String hsql)
	    throws DaoException
	  {
	    return getHibernateTemplate().find(hsql);
	  }

	  public List find(String hsql, Object[] parameters)
	    throws DaoException
	  {
	    return getHibernateTemplate().find(hsql, parameters);
	  }

	  public List find(String hsql, Object parameter)
	    throws DaoException
	  {
	    return getHibernateTemplate().find(hsql, parameter);
	  }

	  public List findAddEntityBySql(Class clazz, String sql, Object[] parameters)
	    throws DaoException
	  {
	    SQLQuery query = getSession().createSQLQuery(sql);
	    if (parameters != null) {
	      for (int i = 0; i < parameters.length; i++) {
	        query.setParameter(i, parameters[i]);
	      }
	    }
	    if (clazz != null) {
	      query.addEntity("ss", clazz);
	    }
	    return query.list();
	  }

	  public List findAddMapBySql(String sql, Object[] parameters) throws DaoException{
	    List result = new ArrayList();
	    try {
		      SQLQuery query = getSession().createSQLQuery(sql);
		      if (parameters != null) {
			    	  for (int i = 0; i < parameters.length; i++) {
			          query.setParameter(i, parameters[i]);
		        }
	      }
	      query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	      List list = query.list();
	      if (list != null)
	        for (int i = 0; i < list.size(); i++) {
	          Map info = (Map)list.get(i);
	          Map linfo = new HashMap();
	          Set set = info.entrySet();
	          Iterator ita = set.iterator();
	          while (ita.hasNext()) {
	            Map.Entry entry = (Map.Entry)ita.next();
	            String key = entry.getKey().toString();
	            String value = "";
	            if (entry.getValue() != null) {
	              if ((entry.getValue() instanceof Clob)) {
	                Clob c = (Clob)entry.getValue();
	                Reader reader = c.getCharacterStream();
	                BufferedReader br = new BufferedReader(reader);
	                StringBuilder sb = new StringBuilder();
	                String str;
	                while ((str = br.readLine()) != null)
	                {
	                  sb.append(str);
	                }
	                value = sb.toString();
	              } else {
	                value = entry.getValue().toString();
	              }
	            }
	            linfo.put(key.toLowerCase(), value);
	          }
	          result.add(linfo);
	        }
	    }
	    catch (Exception e) {
	      throw new DaoException(e);
	    }
	    return result;
	  }

	  public List findByNamedQuery(String hsql)
	    throws DaoException
	  {
	    return getHibernateTemplate().findByNamedQuery(hsql);
	  }

	  public List findByNamedQuery(String hsql, Object parameter)
	    throws DaoException
	  {
	    return getHibernateTemplate().findByNamedQuery(hsql, parameter);
	  }

	  public List findByNamedQuery(String hsql, Object[] parameters)
	    throws DaoException
	  {
	    return getHibernateTemplate().findByNamedQuery(hsql, parameters);
	  }

	  public List findBy(Class entityClass, String name, Object value)
	    throws DaoException
	  {
	    Criteria criteria = createCriteria(entityClass, new Criterion[0]);
	    if (value == null)
	      criteria.add(Restrictions.isNull(name));
	    else {
	      criteria.add(Restrictions.eq(name, value));
	    }
	    criteria.setCacheable(true);
	    return criteria.list();
	  }

	  public List findBy(Class entityClass, String name, Object value, String orderBy, boolean isAsc)
	    throws DaoException
	  {
	    Criteria criteria = createCriteria(entityClass, orderBy, Boolean.valueOf(isAsc), new Criterion[0]);
	    if (value == null)
	      criteria.add(Restrictions.isNull(name));
	    else {
	      criteria.add(Restrictions.eq(name, value));
	    }
	    criteria.setCacheable(true);
	    return criteria.list();
	  }

	  public List getAll(Class entityClass, String orderBy, Boolean isAsc)
	    throws DaoException
	  {
	    Criteria criteria = createCriteria(entityClass, new Criterion[0]);
	    criteria.setCacheable(true);
	    return criteria.list();
	  }

	  public long getCountFindPageByQuery(final String hsql, final Object[] parameters)
	    throws DaoException
	  {
	    Integer returnvalue = (Integer)getHibernateTemplate().execute(
	      new HibernateCallback()
	    {
	      public Object doInHibernate(Session session) throws HibernateException, SQLException
	      {
	        Query query = session.createQuery(hsql);
	        if (parameters != null) {
	          for (int i = 0; i < parameters.length; i++) {
	            query.setParameter(i, parameters[i]);
	          }
	        }
	        query.setMaxResults(1);
	        Integer i = new Integer((Integer) query.list().get(0));
	        return i;
	      }
	    });
	    return returnvalue.longValue();
	  }

	  public Integer getQueryPageListCount(String hql, Object[] values)
	    throws DaoException
	  {
	    int totalCount = 0;
	    List countlist = createQuery(hql, values).setCacheable(true).list();
	    if (countlist != null) {
	      totalCount = countlist.size();
	    }
	    return Integer.valueOf(totalCount);
	  }

	  public Integer getTotalCount(String hql, Object[] values)
	    throws DaoException
	  {
	    String countQueryString = " select count (*) " + 
	      removeSelect(removeOrders(hql));
	    List countlist = createQuery(countQueryString, values).setCacheable(
	      true).list();
	    int totalCount = Integer.parseInt(countlist.get(0).toString());

	    return Integer.valueOf(totalCount);
	  }

	  public List executeSQL(final String sql, final Object[] values)
	    throws DaoException
	  {
	    return (List)getHibernateTemplate().execute(new HibernateCallback() {
	      public Object doInHibernate(Session session) {
	        Query query = session.createSQLQuery(sql);
	        for (int i = 0; i < values.length; i++) {
	          query.setParameter(i, values[i]);
	        }
	        return query.list();
	      }
	    });
	  }

	  public List executeSQL(final String sql, final List values)
	    throws DaoException
	  {
	    return (List)getHibernateTemplate().execute(new HibernateCallback() {
	      public Object doInHibernate(Session session) {
	        Query query = session.createSQLQuery(sql);
	        for (int i = 0; i < values.size(); i++) {
	          query.setParameter(i, values.get(i));
	        }
	        return query.list();
	      }
	    });
	  }

	  public Object executeSQLInsertOrUpdate(final String sql, final Object[] values)
	    throws DaoException
	  {
	    return getHibernateTemplate().execute(new HibernateCallback() {
	      public Object doInHibernate(Session session) {
	        Query query = session.createSQLQuery(sql);
	        for (int i = 0; i < values.length; i++) {
	          query.setParameter(i, values[i]);
	        }
	        return Integer.valueOf(query.executeUpdate());
	      }
	    });
	  }

	  public Object executeSQLInsertOrUpdate(final String sql, final List values)
	    throws DaoException
	  {
	    return getHibernateTemplate().execute(new HibernateCallback() {
	      public Object doInHibernate(Session session) {
	        Query query = session.createSQLQuery(sql);
	        for (int i = 0; i < values.size(); i++) {
	          query.setParameter(i, values.get(i));
	        }
	        return Integer.valueOf(query.executeUpdate());
	      }
	    });
	  }

	  public Pager findPage(String hql, Integer pageNo, Integer pageSize, Object[] values)
	    throws DaoException
	  {
	    String countQueryString = " select count (*) " + 
	      removeSelect(removeOrders(hql));
	    List countlist = createQuery(countQueryString, values).setCacheable(
	      true).list();
	    int totalCount = Integer.parseInt(countlist.get(0).toString());

	    if (totalCount < 1) {
	      return new Pager();
	    }
	    Integer pageCount = Integer.valueOf(0);
	    if (totalCount % pageSize.intValue() > 0)
	      pageCount = Integer.valueOf(totalCount / pageSize.intValue() + 1);
	    else {
	      pageCount = Integer.valueOf(totalCount / pageSize.intValue());
	    }
	    if (pageNo.intValue() > pageCount.intValue()) {
	      pageNo = pageCount;
	    }
	    Query query = createQuery(hql, values);
	    query.setCacheable(true);
	    List list = query.setFirstResult((pageNo.intValue() - 1) * pageSize.intValue())
	      .setMaxResults(pageSize.intValue()).list();

	    Pager page = new Pager(totalCount);
	    page.refresh(pageNo.intValue());
	    page.setList(list);
	    return page;
	  }

	  public Pager findPageByHsql(String hsql, Object[] parameters, Map p)
	    throws DaoException
	  {
	    int pageSize = getPageSize(p);
	    int start = getStart(p, pageSize);
	    return findPageByHsql(hsql, parameters, start, pageSize);
	  }

	  public Pager findPageByHsql(String hsql, int start, int pageSize)
	    throws DaoException
	  {
	    return findPageByHsql(hsql, null, start, pageSize);
	  }

	  public Pager findPageByHsql(String hsql, Object[] parameters, int start, int pageSize)
	    throws DaoException
	  {
	    int totalRows = 0;
	    Query query = createQuery(hsql, new Object[0]);
	    if (parameters != null) {
	      for (int i = 0; i < parameters.length; i++) {
	        query.setParameter(i, parameters[i]);
	      }
	    }
	    totalRows = query.list().size();
	    Pager pager = new Pager();
	    pager.setPageSize(pageSize);
	    pager.setTotalRows(totalRows);
	    if (start > 0)
	      pager.refresh(start / pageSize + 1);
	    else {
	      pager.refresh(1);
	    }
	    query.setFirstResult(pager.getStartRow());
	    query.setMaxResults(pageSize);
	    pager.setList(query.list());
	    return pager;
	  }

	  public Pager findPagerBySqlForMap(String sql, Object[] parameters, Map p)
	    throws DaoException
	  {
	    int pageSize = getPageSize(p);
	    int start = getStart(p, pageSize);
	    return findPagerBySqlForMap(sql, parameters, start, pageSize);
	  }

	  public Pager findPagerBySqlForMap(String sql, Object[] parameters, int start, int pageSize)
	    throws DaoException
	  {
	    Pager pager = new Pager();
	    try {
	      int totalRows = 0;
	      SQLQuery query = getSession().createSQLQuery(sql);
	      if (parameters != null) {
	        for (int i = 0; i < parameters.length; i++) {
	          query.setParameter(i, parameters[i]);
	        }
	      }
	      query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	      totalRows = query.list().size();
	      pager.setPageSize(pageSize);
	      pager.setTotalRows(totalRows);
	      if (start > 0)
	        pager.refresh(start / pageSize + 1);
	      else {
	        pager.refresh(1);
	      }
	      query.setFirstResult(pager.getStartRow());
	      query.setMaxResults(pageSize);
	      List list = query.list();
	      List result = new ArrayList();
	      if (list != null) {
	        for (int i = 0; i < list.size(); i++) {
	          Map info = (Map)list.get(i);
	          Map linfo = new HashMap();
	          Set set = info.entrySet();
	          Iterator ita = set.iterator();
	          while (ita.hasNext()) {
	            Map.Entry entry = (Map.Entry)ita.next();
	            String key = entry.getKey().toString();
	            String value = "";
	            if (entry.getValue() != null) {
	              if ((entry.getValue() instanceof Clob)) {
	                Clob c = (Clob)entry.getValue();
	                Reader reader = c.getCharacterStream();
	                BufferedReader br = new BufferedReader(reader);
	                StringBuilder sb = new StringBuilder();
	                String str;
	                while ((str = br.readLine()) != null)
	                {
	                  sb.append(str);
	                }
	                value = sb.toString();
	              } else {
	                value = entry.getValue().toString();
	              }
	            }
	            linfo.put(key.toLowerCase(), value);
	          }
	          result.add(linfo);
	        }
	      }
	      pager.setList(result);
	    } catch (Exception e) {
	      throw new DaoException(e);
	    }
	    return pager;
	  }

	  public List findPageList(String hql, Integer pageNo, Integer pageSize, Object[] values)
	    throws DaoException
	  {
	    Query query = createQuery(hql, values);
	    query.setCacheable(true);
	    List list = query.setFirstResult((pageNo.intValue() - 1) * pageSize.intValue())
	      .setMaxResults(pageSize.intValue()).list();
	    return list;
	  }

	  public void save(Object object)
	    throws DaoException
	  {
	    getHibernateTemplate().save(object);
	  }

	  public void saveOrUpdate(Object object)
	    throws DaoException
	  {
	    getHibernateTemplate().saveOrUpdate(object);
	  }

	  public void update(Object object)
	    throws DaoException
	  {
	    getHibernateTemplate().saveOrUpdate(object);
	  }

	  public Object update(final String hql, final Object[] values)
	    throws DaoException
	  {
	    return getHibernateTemplate().execute(new HibernateCallback() {
	      public Object doInHibernate(Session session) {
	        Query query = session.createQuery(hql);
	        for (int i = 0; i < values.length; i++) {
	          query.setParameter(i, values[i]);
	        }
	        return Integer.valueOf(query.executeUpdate());
	      }
	    });
	  }

	  public void updateByQuery(final String hsql, final Object[] parameters)
	    throws DaoException
	  {
	    getHibernateTemplate().execute(new HibernateCallback() {
	      public Object doInHibernate(Session session) {
	        Query query = session.createQuery(hsql);
	        if (parameters != null) {
	          for (int i = 0; i < parameters.length; i++) {
	            query.setParameter(i, parameters[i]);
	          }
	        }
	        query.executeUpdate();
	        return null;
	      }
	    });
	  }

	  public Integer deleteByQueryForIds(final String hsql, final Object[] ids)
	    throws DaoException
	  {
	    return (Integer)getHibernateTemplate().execute(
	      new HibernateCallback() {
	      public Object doInHibernate(Session session) {
	        Query query = session.createQuery(hsql);
	        if (ids != null) {
	          query.setParameterList("ids", ids);
	        }
	        return new Integer(query.executeUpdate());
	      }
	    });
	  }

	  public Integer deleteByQuery(final String hsql, final Object[] parameters)
	    throws DaoException
	  {
	    return (Integer)getHibernateTemplate().execute(
	      new HibernateCallback() {
	      public Object doInHibernate(Session session) {
	        Query query = session.createQuery(hsql);
	        if (parameters != null) {
	          for (int i = 0; i < parameters.length; i++) {
	            query.setParameter(i, parameters[i]);
	          }
	        }
	        return new Integer(query.executeUpdate());
	      }
	    });
	  }

	  public Integer deleteBySQL(String sql, Object[] parameters)
	    throws DaoException
	  {
	    PreparedStatement pst = null;
	    int len = 0;
	    try {
	      getCurrentSession().beginTransaction();
	      pst = getCurrentSession().connection().prepareStatement(sql);
	      if (parameters != null) {
	        for (int i = 0; i < parameters.length; i++) {
	          pst.setObject(i + 1, parameters[i]);
	        }
	      }
	      len = pst.executeUpdate();
	      getCurrentSession().getTransaction().commit();
	    } catch (Exception e) {
	      try {
	        getCurrentSession().connection().rollback();
	      } catch (HibernateException e1) {
	        e1.printStackTrace();
	      } catch (SQLException e1) {
	        e1.printStackTrace();
	      }
	      throw new DaoException(e);
	    } finally {
	      if (pst != null) {
	        try {
	          pst.close();
	        } catch (SQLException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	    return Integer.valueOf(len);
	  }

	  public void delete(Object object)
	    throws DaoException
	  {
	    getHibernateTemplate().delete(object);
	  }

	  public void delete(Class clazz, Serializable id)
	    throws DaoException
	  {
	    getHibernateTemplate().delete(load(clazz, id));
	  }

	  public Integer deleteAll(final Class clazz)
	    throws DaoException
	  {
	    return (Integer)getHibernateTemplate().execute(
	      new HibernateCallback() {
	      public Object doInHibernate(Session session) {
	        Query query = session.createQuery("delete " + 
	          clazz.getName());
	        return new Integer(query.executeUpdate());
	      }
	    });
	  }

	  public void remove(List entitys)
	  {
	    for (Iterator localIterator = entitys.iterator(); localIterator.hasNext(); ) { Object entity = localIterator.next();
	      getHibernateTemplate().delete(entity);
	    }
	  }

	  public void removeBy(Class entityClass, String name, Object value)
	    throws DaoException
	  {
	    Query query = createQuery("delete from " + entityClass.getName() + 
	      " where " + name + "=?", new Object[] { value });
	    query.executeUpdate();
	  }

	  public int remove(String hsql, Object[] values) throws DaoException {
	    int result = 0;
	    Query query = createQuery(hsql, values);
	    result = query.executeUpdate();
	    return result;
	  }

	  public int removeByHql(String hsql) throws DaoException {
	    int result = 0;
	    Query query = createQuery(hsql, new Object[0]);
	    result = query.executeUpdate();
	    return result;
	  }

	  public String removeSelect(String hql)
	    throws DaoException
	  {
	    int beginPos = hql.toLowerCase().indexOf("from");
	    return hql.substring(beginPos);
	  }

	  public String removeOrders(String hql)
	    throws DaoException
	  {
	    Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", 
	      2);
	    Matcher m = p.matcher(hql);
	    StringBuffer sb = new StringBuffer();
	    while (m.find()) {
	      m.appendReplacement(sb, "");
	    }
	    m.appendTail(sb);
	    return sb.toString();
	  }

	  public Criteria createCriteria(Class entityClass, Criterion[] criterions)
	    throws DaoException
	  {
	    Criteria criteria = getSession().createCriteria(entityClass);
	    for (Criterion c : criterions) {
	      criteria.add(c);
	    }
	    return criteria;
	  }

	  public Query createQuery(String hql, Object[] values)
	    throws DaoException
	  {
	    Query query = getSession().createQuery(hql);
	    for (int i = 0; i < values.length; i++) {
	      query.setParameter(i, values[i]);
	    }
	    return query;
	  }

	  public Criteria createCriteria(Class entityClass, String orderBy, Boolean isAsc, Criterion[] criterions)
	    throws DaoException
	  {
	    Criteria criteria = createCriteria(entityClass, criterions);
	    if (isAsc.booleanValue())
	      criteria.addOrder(Order.asc(orderBy));
	    else {
	      criteria.addOrder(Order.desc(orderBy));
	    }
	    return criteria;
	  }

	  public void clear()
	    throws DaoException
	  {
	    getSession().clear();
	  }
}
