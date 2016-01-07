package com.common.base.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.common.util.data.map.Dmp;
import com.common.util.exception.DaoException;
import com.common.util.page.Pager;

public interface IBaseDao {
	  public abstract Object queryForObject(String paramString);

	  public abstract Object queryForObject(String paramString, Object paramObject);

	  public abstract List queryForList(String paramString);

	  public abstract List queryForList(String paramString, Object paramObject);

	  public abstract List queryForList(String paramString, Object paramObject, int paramInt1, int paramInt2);

	  public abstract Pager queryForPage(String paramString, Map paramMap);

	  public abstract Object insert(String paramString);

	  public abstract Object insert(String paramString, Object paramObject);

	  public abstract int update(String paramString);

	  public abstract int update(String paramString, Object paramObject);

	  public abstract int delete(String paramString);

	  public abstract int delete(String paramString, Object paramObject);

	  public abstract Connection getConnection() throws DaoException;

	  public abstract DataSource getDataSourceFromSqlMap();

	  public abstract SqlMapClientTemplate getSqlMapClientTpl();
}
