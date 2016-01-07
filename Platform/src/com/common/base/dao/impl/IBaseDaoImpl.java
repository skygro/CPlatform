package com.common.base.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.common.base.dao.IBaseDao;
import com.common.util.data.map.Dmp;
import com.common.util.exception.DaoException;
import com.common.util.page.Pager;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;

public class IBaseDaoImpl extends SqlMapClientDaoSupport implements IBaseDao{
	        
	protected static transient Logger logger = LoggerFactory.getLogger(IBaseDaoImpl.class);
			private SqlExecutor sqlExecutor;
			
			public SqlExecutor getSqlExecutor()
			{
			  return this.sqlExecutor;
			}
			
			public void setSqlExecutor(SqlExecutor sqlExecutor) {
			  this.sqlExecutor = sqlExecutor;
			}
			
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
			
			private int getPageSize(Map p) throws DaoException
			{
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
			
			public Object queryForObject(String sqlKey)
			{
			  logger.info("IBatis-sqlKey:" + sqlKey);
			  return getSqlMapClientTemplate().queryForObject(sqlKey);
			}
			
			public Object queryForObject(String sqlKey, Object param)
			{
			  logger.info("IBatis-sqlKey:" + sqlKey);
			  return getSqlMapClientTemplate().queryForObject(sqlKey, param);
			}
			
			public List queryForList(String sqlKey)
			{
			  logger.info("IBatis-sqlKey:" + sqlKey);
			  return getSqlMapClientTemplate().queryForList(sqlKey);
			}
			
			public List queryForList(String sqlKey, Object param)
			{
			  logger.info("IBatis-sqlKey:" + sqlKey);
			  return getSqlMapClientTemplate().queryForList(sqlKey, param);
			}
			
			public List queryForList(String tradeCode, Object param, int start, int end)
			{
			  return getSqlMapClientTemplate().queryForList(tradeCode, param, 
			    start, end);
			}
			
			public Object insert(String sqlKey)
			{
			  logger.info("IBatis-sqlKey:" + sqlKey);
			  return getSqlMapClientTemplate().insert(sqlKey);
			}
			
			public Object insert(String sqlKey, Object param)
			{
			  logger.info("IBatis-sqlKey:" + sqlKey);
			  return getSqlMapClientTemplate().insert(sqlKey, param);
			}
			
			public int update(String sqlKey)
			{
			  logger.info("IBatis-sqlKey:" + sqlKey);
			  return getSqlMapClientTemplate().update(sqlKey);
			}
			
			public int update(String sqlKey, Object param)
			{
			  logger.info("IBatis-sqlKey:" + sqlKey);
			  return getSqlMapClientTemplate().update(sqlKey, param);
			}
			
			public int delete(String sqlKey)
			{
			  logger.info("IBatis-sqlKey:" + sqlKey);
			  return getSqlMapClientTemplate().delete(sqlKey);
			}
			
			public int delete(String sqlKey, Object param)
			{
			  logger.info("IBatis-sqlKey:" + sqlKey);
			  return getSqlMapClientTemplate().delete(sqlKey, param);
			}
			
			public DataSource getDataSourceFromSqlMap()
			{
			  return getSqlMapClientTemplate().getDataSource();
			}
			
			public Pager queryForPage(String tradeCode, Map p)
			  {
			    logger.info("IBatis-sqlKey:" + tradeCode);
			    Pager pager = new Pager();
			    try {
			      int pageSize = getPageSize(p);
			      int start = getStart(p, pageSize);
			      pager.setPageSize(pageSize);

			      if (start > 0)
			        pager.refresh(start / pageSize + 1);
			      else {
			        pager.refresh(1);
			      }

			      List list = getSqlMapClientTemplate().queryForList(tradeCode, 
			        p, pager.getStartRow(), 
			        pager.getPageSize() * pager.getCurrentPage());

			     int count =  list.size();
			      if (pager.getStartRow() > count)
			      {
			        pager.setCurrentPage(count / pageSize + 1);
			        pager.refresh(count / pageSize + 1);
			      }
			      pager.setTotalRows(count);
			      pager.setList(list);
			    }
			    catch (Exception e)
			    {
			      e.printStackTrace();
			    }
			    return pager;
			  }

			public Connection getConnection() throws DaoException {
				  try{
				      return getSqlMapClientTemplate().getDataSource().getConnection(); 
				    } catch (SQLException e) {
				    }throw new DaoException();
			}

			public SqlMapClientTemplate getSqlMapClientTpl()
			{
			  return getSqlMapClientTemplate();
			}

}
