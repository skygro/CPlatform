package com.common.base.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import com.common.util.exception.DaoException;
import com.common.util.page.Pager;

public abstract interface HBaseDao {
	
	  public abstract Connection getConnection()
	    throws DaoException;

	  public abstract Serializable getId(Object paramObject, Class paramClass)
	    throws DaoException;

	  public abstract String getIdName(Class paramClass)
	    throws DaoException;

	  public abstract Object findUnique(Class paramClass, String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List findAll(Class paramClass)
	    throws DaoException;

	  public abstract Object load(Class paramClass, Serializable paramSerializable)
	    throws DaoException;

	  public abstract Object get(Class paramClass, Serializable paramSerializable)
	    throws DaoException;

	  public abstract List find(String paramString)
	    throws DaoException;

	  public abstract List find(String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract List find(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List findAddEntityBySql(Class paramClass, String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract List findAddMapBySql(String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract List findByNamedQuery(String paramString)
	    throws DaoException;

	  public abstract List findByNamedQuery(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List findByNamedQuery(String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract List findBy(Class paramClass, String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List findBy(Class paramClass, String paramString1, Object paramObject, String paramString2, boolean paramBoolean)
	    throws DaoException;

	  public abstract List getAll(Class paramClass, String paramString, Boolean paramBoolean)
	    throws DaoException;

	  public abstract long getCountFindPageByQuery(String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract Integer getQueryPageListCount(String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract Integer getTotalCount(String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract List executeSQL(String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract List executeSQL(String paramString, List paramList)
	    throws DaoException;

	  public abstract Object executeSQLInsertOrUpdate(String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract Object executeSQLInsertOrUpdate(String paramString, List paramList)
	    throws DaoException;

	  public abstract Pager findPage(String paramString, Integer paramInteger1, Integer paramInteger2, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract Pager findPageByHsql(String paramString, Object[] paramArrayOfObject, Map paramMap)
	    throws DaoException;

	  public abstract Pager findPageByHsql(String paramString, int paramInt1, int paramInt2)
	    throws DaoException;

	  public abstract Pager findPageByHsql(String paramString, Object[] paramArrayOfObject, int paramInt1, int paramInt2)
	    throws DaoException;

	  public abstract Pager findPagerBySqlForMap(String paramString, Object[] paramArrayOfObject, Map paramMap)
	    throws DaoException;

	  public abstract Pager findPagerBySqlForMap(String paramString, Object[] paramArrayOfObject, int paramInt1, int paramInt2)
	    throws DaoException;

	  public abstract List findPageList(String paramString, Integer paramInteger1, Integer paramInteger2, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract void save(Object paramObject)
	    throws DaoException;

	  public abstract void saveOrUpdate(Object paramObject)
	    throws DaoException;

	  public abstract void update(Object paramObject)
	    throws DaoException;

	  public abstract Object update(String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract void updateByQuery(String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract Integer deleteByQueryForIds(String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract Integer deleteByQuery(String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract Session getCurrentSession();

	  public abstract Integer deleteBySQL(String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract void delete(Object paramObject)
	    throws DaoException;

	  public abstract void delete(Class paramClass, Serializable paramSerializable)
	    throws DaoException;

	  public abstract Integer deleteAll(Class paramClass)
	    throws DaoException;

	  public abstract void remove(List paramList);

	  public abstract void removeBy(Class paramClass, String paramString, Object paramObject)
	    throws DaoException;

	  public abstract int remove(String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract int removeByHql(String paramString)
	    throws DaoException;

	  public abstract String removeSelect(String paramString)
	    throws DaoException;

	  public abstract String removeOrders(String paramString)
	    throws DaoException;

	  public abstract Criteria createCriteria(Class paramClass, Criterion[] paramArrayOfCriterion)
	    throws DaoException;

	  public abstract Query createQuery(String paramString, Object[] paramArrayOfObject)
	    throws DaoException;

	  public abstract Criteria createCriteria(Class paramClass, String paramString, Boolean paramBoolean, Criterion[] paramArrayOfCriterion)
	    throws DaoException;

	  public abstract void clear() throws DaoException;
}
