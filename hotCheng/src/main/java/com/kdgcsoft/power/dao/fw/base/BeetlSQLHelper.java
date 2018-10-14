package com.kdgcsoft.power.dao.fw.base;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.hibernate.engine.spi.SessionImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.common.util.DBPropertyUtil;

/**
 * beetlSql工具类
 * @author jingao
 *
 */
@Repository("BeetlSQLHelper")
public class BeetlSQLHelper {
	@Autowired
    SQLManager sqlManager;
	
	@PersistenceContext
	@Resource
	private  EntityManager entityManager;
	
	private static Logger logger = LoggerFactory.getLogger(BeetlSQLHelper.class);
	
	public List<Map<String, Object>> getMetaInfoList(String sql, Object[] params){
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			//获取连接信息
			SessionImplementor session =entityManager.unwrap(SessionImplementor.class);
			java.sql.Connection con = session.connection();
			
			//获取查询信息
			pst = con.prepareStatement(sql);
			if(params!=null){
				for(int i=0; i<params.length; i++){
					pst.setObject(i+1, params[i]);
				}
			}
			rs = pst.executeQuery();
			//获取列名相关信息
			ResultSetMetaData data=rs.getMetaData();
			
			if(data!=null){
				List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
				for(int i = 1 ; i<= data.getColumnCount() ; i++){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("colNum", data.getColumnName(i));
					map.put("colType", data.getColumnTypeName(i));
					map.put("colField",DBPropertyUtil.columnToProperty2(data.getColumnName(i).toLowerCase()));
					result.add(map);
				}
				return result;
			}
		} catch (Exception e) {
			logger.error("获取数据库相应列名信息失败！", e);
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error("ResultSet failed！", e);
				}
			}
			if(pst != null){
				try {
					pst.close();
				} catch (SQLException e) {
					logger.error("PreparedStatement failed！", e);
				}
			}
		}
		return null;
	}
	
	/**
	 * 分页查询
	 * @param sqlId 对应sql模板里的sqlid
	 * @param paras 查询参数 可以为null
	 * @param pageRequest 分页信息
	 * @return
	 */
	public PageObject<Map<String, Object>> serachPage(String sqlId,Map<String,Object> paras,PageRequest pageRequest){
		PageQuery<Map<String, Object>> query = new PageQuery<Map<String, Object>>(pageRequest.getPageNumber(),paras,-1,pageRequest.getPageSize());
		@SuppressWarnings({ "unchecked"})
		Class<Map<String, Object>> clazz = (Class<Map<String, Object>>)(Class<?>)Map.class;
		sqlManager.pageQuery(sqlId, clazz, query);
		return new PageObject<Map<String, Object>>(query);
	}
	
	/**
	 * 无分页查询
	 * @param sqlId 对应sql模板里的sqlid
	 * @param paras 查询参数 可以为null
	 * @return
	 */
	public PageObject<Map<String, Object>> serachPage(String sqlId,Map<String,Object> paras){
		@SuppressWarnings({ "unchecked"})
		Class<Map<String, Object>> clazz = (Class<Map<String, Object>>)(Class<?>)Map.class;
		List<Map<String, Object>> list = this.getList(sqlId, paras, clazz);
		PageObject<Map<String, Object>> pageObject = new PageObject<Map<String, Object>>();
		pageObject.setList(list);
		pageObject.setTotalCount(Long.parseLong(String.valueOf(list.size())));
		return pageObject;
	}
	
	/**
	 * 根据实体获取返回结果List
	 * @param sqlId 对应sql模板里的sqlid
	 * @param paras 查询参数 可以为null
	 * @param cls 要返回的Class类型
	 * @return
	 */
	public <T> List<T> getList(String sqlId, Map<String,Object> paras, Class<T> cls){
		List<T> list = sqlManager.select(sqlId, cls, paras);
		return list;
	}
	/**
	 * 获取以Map形式存储数据的List
	 * @param sqlId
	 * @param paras 可以为null
	 * @param cls
	 * @return
	 */
	public  List<Map<String,Object>> getMapList(String sqlId, Map<String,Object> paras){
		@SuppressWarnings({ "unchecked"})
		Class<Map<String, Object>> clazz = (Class<Map<String, Object>>)(Class<?>)Map.class;
		List<Map<String,Object>> list = sqlManager.select(sqlId, clazz, paras);
		return list;
	}
	
	/**
	 * 获取单条返回结果(带实体的)
	 * @param sqlId 对应sql模板里的sqlid
	 * @param paras 查询参数 可以为null
	 * @param cls 要返回的Class类型
	 * @return
	 */
	public <T> T getEntity(String sqlId, Map<String, Object> paras, Class<T> cls){
		T t = sqlManager.selectSingle(sqlId, paras, cls);
		return t;
	}
	/**
	 * 获取对应于一条记录的单个Map对象
	 * @param sqlId
	 * @param paras 可以为null
	 * @return
	 */
	public Map<String,Object> getMap(String sqlId, Map<String, Object> paras){
		@SuppressWarnings({ "unchecked"})
		Class<Map<String, Object>> clazz = (Class<Map<String, Object>>)(Class<?>)Map.class;
		Map<String,Object> retrunMap = sqlManager.selectSingle(sqlId, paras, clazz);
		return retrunMap;
	}
	
	/**
	 * 获取单个Long字段数值
	 * @param sqlId 对应sql模板里的sqlid
	 * @param paras 查询参数 可以为null
	 * @return
	 */
	public Long getLongValue(String sqlId, Map<String, Object> paras){
		return sqlManager.longValue(sqlId, paras);
	}
	
	/**
	 * 获取单个Integer字段数值
	 * @param sqlId 对应sql模板里的sqlid
	 * @param paras 查询参数 可以为null
	 * @return
	 */
	public Integer getIntValue(String sqlId, Map<String, Object> paras){
		return sqlManager.intValue(sqlId, paras);
	}

	/**
	 * 获取单个BigDecimal字段数值
	 * @param sqlId 对应sql模板里的sqlid
	 * @param paras 查询参数 可以为null
	 * @return
	 */
	public BigDecimal getBigDecimalValue(String sqlId, Map<String, Object> paras){
		return sqlManager.bigDecimalValue(sqlId, paras);
	}
	
	/**
	 * 获取单个String字段数值
	 * @param sqlId 对应sql模板里的sqlid
	 * @param paras 查询参数 可以为null
	 * @return
	 */
	public String getStringlValue(String sqlId, Map<String, Object> paras){
		return sqlManager.selectSingle(sqlId, paras, String.class);
	}
	/**
	 * 执行sql语句update 或者delte操作
	 * @param sqlId
	 * @param paras 可以为null
	 * @return
	 */
	public int updateOrDelete(String sqlId,Map<String, Object> paras){
		return sqlManager.update(sqlId, paras);
	}
	
	/**
	 * 根据动态sql语句 分页查询
	 * @param sqlId 对应sql模板里的sqlid
	 * @param paras 查询参数 可以为null
	 * @param pageRequest 分页信息
	 * @return
	 */
	public PageObject<Map<String, Object>> serachPageBySql(String sql,Map<String,Object> paras,PageRequest pageRequest){
		@SuppressWarnings({ "unchecked"})
		Class<Map<String, Object>> clazz = (Class<Map<String, Object>>)(Class<?>)Map.class;
		List<Map<String, Object>> list = sqlManager.execute(sql, clazz, paras, pageRequest.getPageNumber(), pageRequest.getPageSize());
		PageObject<Map<String, Object>> pageObject = new PageObject<Map<String, Object>>();
		pageObject.setList(list);
		pageObject.setTotalCount(Long.parseLong(String.valueOf(list.size())));
		return pageObject;
	}

}
