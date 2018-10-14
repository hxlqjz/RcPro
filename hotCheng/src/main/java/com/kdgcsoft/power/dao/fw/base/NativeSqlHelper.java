package com.kdgcsoft.power.dao.fw.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.beanutils.ConvertUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kdgcsoft.power.common.bean.NativeSQLException;
import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.common.util.DBPropertyUtil;
import com.kdgcsoft.power.common.util.SqlUtil;



/***
 * 封装的操作数据库的工具类
 */
/**
 * @author hling
 *
 */
@Repository("NativeSqlHelper")
public class NativeSqlHelper {
	
	@PersistenceContext
	@Resource
	private  EntityManager entityManager;
	
	private static Logger logger = LoggerFactory.getLogger(NativeSqlHelper.class);
	
	

	/**
	 * 执行一次SQL从而得到结果集的数量。SQL会自动优化，去掉最后一个order by后再执行。
	 * @param sql 要执行的SQL
	 * @param params 参数。只能为Object[]或Map<String, Object>。可以为null，表示没有参数。
	 * @return 记录数
	 */
	public Long getTotalCount(final String sql, final Object params) {
		String sqlCount =SqlUtil.getCountSql(sql);
		Long count = this.getLongValueCommon(sqlCount, params);
		return count;
	}
	
	/*********** 获取单个字段值   ********/
	
	/**
	 * 获取单个字符串字段值
	 * @param sql SQL
	 * @return 字符串
	 */
	public String getStringValue(String sql) {
		return getValue(sql,new Object[]{}, String.class);
	}
	
	@SuppressWarnings("unchecked")
	public List queryByNativeSQL(String sql,Object[] params,final int... rowStartIdxAndCount){
		Query query = entityManager.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if(params != null && params.length>0){
			for(int i=0;i<params.length;i++)
			{
				query.setParameter(i+1, params[i]);
			}
		}
		if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
			int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
			if (rowStartIdx > 0) {
				query.setFirstResult(rowStartIdx);
			}

			if (rowStartIdxAndCount.length > 1) {
				int rowCount = Math.max(0, rowStartIdxAndCount[1]);
				if (rowCount > 0) {
					query.setMaxResults(rowCount);
				}
			}
		}
		return query.getResultList();
	}

	/**
	 *  获取单个字符串字段值
	 * @param sql SQL
	 * @param params 数组形式的参数
	 * @return 字符串
	 */
	public String getStringValue(String sql, Object[] params) {
		return getValue(sql, params, String.class);
	}

	/**
	 *  获取单个字符串字段值
	 * @param sql SQL
	 * @param params Map形式的参数
	 * @return 字符串
	 */
	public String getStringValue(String sql, Map<?, ?> params) {
		return getValue(sql, params, String.class);
	}
	
	/**
	 * 获取单个整型字段值
	 * @param sql SQL
	 * @return 整型
	 */
	public Long getLongValue(String sql) {
		return getValue(sql,new Object[]{}, Long.class);
	}
	
	/**
	 * 获取单个整型字段值
	 * @param sql SQL
	 * @param params 参数。只能为Object[]或Map<String, Object>。可以为null。
	 * @return
	 */
	private Long getLongValueCommon(String sql, Object params) {
		return getValueCommon(sql, params, Long.class);
	}

	/**
	 *  获取单个整型字段值
	 * @param sql SQL
	 * @param params 数组形式的参数
	 * @return 整型
	 */
	public Long getLongValue(String sql, Object[] params) {
		return getValue(sql, params, Long.class);
	}

	/**
	 *  获取单个整型字段值
	 * @param sql SQL
	 * @param params Map形式的参数
	 * @return 整型
	 */
	public Long getLongValue(String sql, Map<?, ?> params) {
		return getValue(sql, params, Long.class);
	}

	/**
	 *  按照需要的类型获取单个字段值
	 * @param sql SQL
	 * @param params 数组形式的参数
	 * @param type 返回的对象类型
	 * @return 单个对象
	 */
	public <T> T getValue(String sql, Object[] params, Class<T> type) {
		Query query = prepareQuery(sql, params);
		return getValueAndCast(query, type);
	}
	
	/**
	 *  按照需要的类型获取单个字段值
	 * @param sql SQL
	 * @param params Map形式的参数
	 * @param type 返回的对象类型
	 * @return 单个对象
	 */
	public <T> T getValue(String sql, Map<?, ?> params, Class<T> type) {
		Query query = prepareQuery(sql, params, type);
		return getValueAndCast(query, type);
	}
	

	private <T> T getValueCommon(String sql, Object params, Class<T> type) {
		Query query = prepareQuery(sql, params);
		return getValueAndCast(query, type);
	}
	
	/**
	 * 获取实体Bean
	 * @param sql SQL
	 * @param type 返回的对象类型
	 * @return 对应于一条记录的单个对象
	 */
	public <T> T getEntity(String sql, Class<T> type) {
		return getEntity(sql,new Object[]{},type);
	}

	/**
	 * 获取实体Bean
	 * @param sql SQL
	 * @param params Object[]形式的参数
	 * @param type 返回的对象类型
	 * @return 对应于一条记录的单个对象
	 */
	public <T> T getEntity(String sql, Object[] params, Class<T> type) {
		return getEntityCommon(sql, params, type);
	}
	
	/**
	 * 获取实体Bean
	 * @param sql SQL
	 * @param params Map形式的参数
	 * @param type 返回的对象类型
	 * @return 对应于一条记录的单个对象
	 */
	public <T> T getEntity(String sql, Map<String, Object> params, Class<T> type) {
		return getEntityCommon(sql, params, type);
	}
	
	/**
	 * 获取实体Bean
	 * @param sql SQL
	 * @param params 参数
	 * @param type 返回的对象类型
	 * @return 对应于一条记录的单个对象
	 */
	private <T> T getEntityCommon(String sql, Object params, Class<T> type) {
		List<T> list = getListCommon(sql,params, type);
		if(list != null && !list.isEmpty()){
			if (list.size() > 1) {
				logger.error("只需要一条数据，但实际返回记录数为多条！可能存在数据混乱或应该优化SQL减少返回条数！", new Exception() );
			}
			return list.get(0);
		}
		return null;
	}
	
	/*************** 获取Map   ****************/
	/**
	 * 获取对应于一条记录的单个Map对象
	 * @param sql SQL
	 * @return Map对象
	 */
	public Map<String,Object> getMap(String sql){
		return getMapCommon(sql, null);
	}
	
	/**
	 * 获取对应于一条记录的单个Map对象
	 * @param sql SQL
	 * @param params 参数
	 * @return 单条记录对应的Map对象
	 */
	public Map<String,Object> getMap(String sql, Object[] params){
		return getMapCommon(sql, params);
	}
	
	/**
	 * 获取对应于一条记录的单个Map对象
	 * @param sql SQL
	 * @param params 参数
	 * @return 单条记录对应的Map对象
	 */
	public Map<String,Object> getMap(String sql, Map<String, Object> params){
		return getMapCommon(sql, params);
	}
	
	/**
	 * 获取对应于一条记录的单个Map对象
	 * @param sql SQL
	 * @param params 参数
	 * @return 单条记录对应的Map对象
	 */
	private Map<String,Object> getMapCommon(String sql, Object params){
		Query query = prepareQueryForMap(sql, params, null);
		List<Map<String,Object>> list = getResultListAndCast(query);
		if(list.isEmpty() || list.size() == 0){
			if (list.size() > 1) {
				logger.error("只需要一条数据，但实际返回记录数为多条！可能存在数据混乱或应该优化SQL减少返回条数！", new Exception() );
			}
			return new HashMap<String,Object>();
		}
		return convertMapKey(list.get(0));
	}

	/**
	 * 获取以Map形式存储数据的List
	 * @param sql SQL
	 * @param rowStartIdxAndCount 返回数据的起始位置和条数
	 * @return 以Map形式存储数据的List
	 */
	public List<Map<String,Object>> getMapList(String sql, int... rowStartIdxAndCount) {
		return getMapListCommon(sql, null, rowStartIdxAndCount);
	}

	/**
	 * 获取以Map形式存储数据的List
	 * @param sql SQL
	 * 	@param params 数组类型的参数
	 * @param rowStartIdxAndCount 返回数据的起始位置和条数
	 * @return 以Map形式存储数据的List
	 */
	public List<Map<String,Object>> getMapList(String sql, Object[] params, int... rowStartIdxAndCount) {
		return getMapListCommon(sql, params, rowStartIdxAndCount);
	}
	
	/**
	 * 获取以Map形式存储数据的List
	 * @param sql SQL
	 * 	@param params Map类型的参数
	 * @param rowStartIdxAndCount 返回数据的起始位置和条数
	 * @return 以Map形式存储数据的List
	 */
	public List<Map<String,Object>> getMapList(String sql, Map<String, Object> params, int... rowStartIdxAndCount) {
		return getMapListCommon(sql, params, rowStartIdxAndCount);
	}
	
	/**
	 * 获取以Map形式存储数据的List
	 * @param sql SQL
	 * 	@param params 数组类型的参数
	 * @param rowStartIdxAndCount 返回数据的起始位置和条数
	 * @return 以Map形式存储数据的List
	 */
	private List<Map<String,Object>> getMapListCommon(String sql, Object params, int... rowStartIdxAndCount) {
		Query query = prepareQueryForMap(sql, params, rowStartIdxAndCount);
		List<Map<String,Object>> list = getResultListAndCast(query);
		List<Map<String,Object>> newList = convertMapKey(list);
		entityManager.clear();
		return newList;
	}

	/**
	 * 获取以数组形式存储数据的List。数组的每个元素对应一个字段。
	 * @param sql SQL
	 * @param rowStartIdxAndCount 返回数据的起始位置和条数
	 * @return 以数组形式存储数据的List
	 */
	public List<Object[]> getList(String sql, final int... rowStartIdxAndCount) {
		return getListCommon(sql, null, rowStartIdxAndCount);
	}

	/**
	 * 获取以数组形式存储数据的List。数组的每个元素对应一个字段。
	 * @param sql SQL
	 * @param params 参数
	 * @param rowStartIdxAndCount 返回数据的起始位置和条数
	 * @return 以数组形式存储数据的List
	 */
	public List<Object[]> getList(String sql, Object[] params,
			final int... rowStartIdxAndCount) {
		return getListCommon(sql, params, rowStartIdxAndCount);
	}

	/**
	 * 获取以数组形式存储数据的List。数组的每个元素对应一个字段。
	 * @param sql SQL
	 * @param params 参数
	 * @param rowStartIdxAndCount 返回数据的起始位置和条数
	 * @return 以数组形式存储数据的List
	 */
	public List<Object[]> getList(String sql, Map<?, ?> params,
			final int... rowStartIdxAndCount) {
		return getListCommon(sql, params, rowStartIdxAndCount);
	}
	
	/**
	 * 获取以数组形式存储数据的List。数组的每个元素对应一个字段。
	 * @param sql SQL
	 * @param params 参数
	 * @param rowStartIdxAndCount 返回数据的起始位置和条数
	 * @return 以数组形式存储数据的List
	 */
	private List<Object[]> getListCommon(String sql, Object params, final int... rowStartIdxAndCount) {
		Query query = prepareQuery(sql, params, rowStartIdxAndCount);
		List<Object[]> list = getResultListAndCast(query);
		entityManager.clear();
		return list;
	}

	/**
	 * 获取以指定对象类型存储数据的List。数组的每个元素对应一个字段。
	 * @param sql SQL
	 * @param type 指定的对象类型
	 * @param rowStartIdxAndCount 返回数据的起始位置和条数
	 * @return 以指定对象类型存储数据的List
	 */
	public  <T> List<T> getList(String sql, Class<?> type,
			final int... rowStartIdxAndCount) {
		return getList(sql, new Object[]{}, type, rowStartIdxAndCount);
	}

	/**
	 * 获取以指定对象类型存储数据的List。数组的每个元素对应一个字段。
	 * @param sql SQL
	 * @param params 参数
	 * @param type 指定的对象类型
	 * @param rowStartIdxAndCount 返回数据的起始位置和条数
	 * @return 以指定对象类型存储数据的List
	 */
	public <T> List<T> getList(String sql, Object[] params, Class<?> type,
			final int... rowStartIdxAndCount) {
		return getListCommon(sql, params, type, rowStartIdxAndCount);
	}

	public <T> List<T> getList(String sql, Map<?, ?> params, Class<?> type,
			final int... rowStartIdxAndCount) {
		return getListCommon(sql, params, type, rowStartIdxAndCount);
	}
	
	private <T> List<T> getListCommon(String sql, Object params, Class<?> type,
			final int... rowStartIdxAndCount) {
		Query query = prepareQuery(sql, params, type, rowStartIdxAndCount);
		List<T> list = getResultListAndCast(query);
		entityManager.clear();
		return list;
	}
	
	/********************** 执行sql  ************************/
	

	public int exeNativeSQL(String sql) {
		return exeNativeSQL(sql,null);
	}

	public int exeNativeSQL(String sql, Object[] params) {
		Query query = entityManager.createNativeQuery(sql);
		setQueryParameters(query, params);
		return query.executeUpdate();
	}

	/**
	 * 取得表的最大值加1
	 */
	@Deprecated
	public Long getMaxId(String tabelName, String idColumnName) {
		logger.warn("获取表中最大ID的方式存在隐患，在高并发时可能产生错误数据！");
		String sql = "select case when  max(" + idColumnName
				+ ") is  null then 1 else max(" + idColumnName
				+ ")+1 end from " + tabelName;
		return Long.parseLong(this.getStringValue(sql).toString());
	}
	
	public boolean exeNativeSQL(List<String> sqls) {
		boolean b = false;
		int i = 0;
		for (Object sql : sqls) {
			i++;
			if (sql != null) {
				Query query = entityManager.createNativeQuery(sql.toString());
				query.executeUpdate();
			}
			if (i % 20 == 0) {
				entityManager.flush();
			}
		}

		b = true;
		return b;
	}
	
	/**
	 * sql语句查询分页
	 * @param sql SQL
	 * @param params 参数
	 * @param rowStartIdxAndCount 起始位置和最大返回记录数
	 * @return
	 */
	public PageObject<Map<String, Object>> getPageObject(String sql, Object[] params, int... rowStartIdxAndCount) {
		logger.debug(Arrays.toString(params));
		return getPageObjectCommon(sql, params, null, rowStartIdxAndCount);
	}
	
	/**
	 * sql语句查询分页
	 * @param sql SQL
	 * @param params 参数
	 * @param rowStartIdxAndCount 起始位置和最大返回记录数
	 * @return
	 */
	public PageObject<Map<String, Object>> getPageObject(String sql, Map<String, Object> params, int... rowStartIdxAndCount) {
		logger.info(params.toString());
		return getPageObjectCommon(sql, params, null, rowStartIdxAndCount);
	}
	
	/**
	 * sql语句查询分页
	 * @param sql SQL
	 * @param params 数据形式的参数
	 * @param type 返回List中的数据类型
	 * @param rowStartIdxAndCount 起始位置和最大返回记录数
	 * @return
	 */
	public <T> PageObject<T> getPageObject(String sql, Object[] params, Class<T> type, int... rowStartIdxAndCount) {
		logger.info(Arrays.toString(params));
		return getPageObjectCommon(sql, params, type, rowStartIdxAndCount);
	}
	
	/**
	 * sql语句查询分页
	 * @param sql SQL
	 * @param params Map形式的参数
	 * @param type 返回List中的数据类型
	 * @param rowStartIdxAndCount 起始位置和最大返回记录数
	 * @return
	 */
	public <T> PageObject<T> getPageObject(String sql, Map<String, Object> params, Class<T> type, int... rowStartIdxAndCount) {
		logger.info(params.toString());
		return getPageObjectCommon(sql, params, type, rowStartIdxAndCount);
	}
	
	/**
	 * sql语句查询分页
	 * @param sql SQL
	 * @param params 参数。只能为Object[]或Map<String, Object>。可以为null。
	 * @param type 返回List中的数据类型
	 * @param rowStartIdxAndCount 起始位置和最大返回记录数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> PageObject<T> getPageObjectCommon(String sql, Object params, Class<T> type, int... rowStartIdxAndCount) {
		Long count = getTotalCount(sql, params);
		
		PageObject<T> pageobject = new PageObject<T>();
		pageobject.setTotalCount(count);
		List<T> list = null;
		if (count > 0) {
			if (type == null) {
				list = (List<T>)this.getMapListCommon(sql, params, rowStartIdxAndCount);
			} else {
				list = this.getListCommon(sql, params, type, rowStartIdxAndCount);
			}
			
		} else {
			// 没必要再查询，直接返回一个空List
			list = new ArrayList<T>();
		}
		
		pageobject.setList(list);		
		return pageobject;
	}
	
	/**
	 * sql语句查询分页
	 * @param sql
	 * @param rowStartIdxAndCount
	 * @return
	 */
	public PageObject<Map<String, Object>> getPageObject(String sql,  int... rowStartIdxAndCount) {
		return getPageObject(sql, new String[]{}, rowStartIdxAndCount);
	}
	
	/**
	 * 把List<Map>中所有Map的Key转换为驼峰格式。
	 * 注意：只按照第一个Map中的名称进行转换，即所有Map的Key应该相同。
	 * @param list  原数组
	 * @return
	 */
	private List<Map<String,Object>> convertMapKey(List<Map<String,Object>> list) {

		List<Map<String,Object>> newList = new ArrayList<Map<String,Object>>();
		if(list==null || list.isEmpty()){
			return newList;
		}
		
		Object[] keys = list.get(0).keySet().toArray();
		String[] newKeys = new String[keys.length];
		for(int i = 0; i < keys.length; i++){
			newKeys[i] = DBPropertyUtil.columnToProperty2(keys[i].toString());
		}
		
		Map<String,Object> newMap = null;
		for(Map<String,Object> tmp : list){
			newMap = new HashMap<String, Object>();
			for(int i = 0; i < keys.length; i++){
				newMap.put(newKeys[i], tmp.get(keys[i]));
			}
			newList.add(newMap);
		}
		
		return newList;
	}
	
	/**
	 * 把Map中的Key转换为驼峰格式
	 * @param originMap  原Map
	 * @return 转换后的Map
	 */
	private Map<String,Object> convertMapKey(Map<String,Object> originMap) {
		if (originMap == null) {
			return null;
		}
		
		Object[] keys = originMap.keySet().toArray();
		Map<String,Object> newMap = new HashMap<String, Object>();
		for(int i = 0; i < keys.length; i++){
			String newKey = DBPropertyUtil.columnToProperty2(keys[i].toString());
			newMap.put(newKey, originMap.get(keys[i]));
		}
		
		return newMap;
	}
	
	/**
	 * 把数组型参数添加到Query对象
	 * @param query Query对象
	 * @param params 数组型参数
	 */
	private void setQueryParameters(Query query, Object[] params) {
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
	}
	
	/**
	 * 把Map型参数添加到Query对象
	 * @param query Query对象
	 * @param params Map型参数
	 */
	private void setQueryParameters(Query query, Map<?, ?> params) {
		Iterator<?> it = params.keySet().iterator();
		while (it.hasNext()) {
			String str = it.next().toString();
			query.setParameter(str, params.get(str));
		}
	}

	/**
	 * 对Query对象设置查询结果起始位置和最大返回条数
	 * @param query Query对象
	 * @param rowStartIdxAndCount 起始位置和最大返回条数
	 */
	private void setQueryStartAndCount(Query query, int... rowStartIdxAndCount) {
		if(rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0){
			if( rowStartIdxAndCount[0] > 0){
				query.setFirstResult(rowStartIdxAndCount[0]);
			}
			if(rowStartIdxAndCount.length > 1 && rowStartIdxAndCount[1] > 0){
				query.setMaxResults(rowStartIdxAndCount[1]);
			}
		}
	}
	
	/**
	 * 获得查询结果并按照预定数据类型返回
	 * @param q 查询对象
	 * @return 特定数据类型的List
	 */
	private static <T> List<T> getResultListAndCast(Query q) {
	    @SuppressWarnings("unchecked")
		List<T> list = (List<T>)q.getResultList();
	    return list;
	}
	
	/**
	 * 获得单个字段的查询结果，并按照指定数据类型返回
	 * @param q Query对象
	 * @param type 数据类型，可以为String，Long等
	 * @return 单个数据对象
	 */
	@SuppressWarnings("unchecked")
	private static <T> T getValueAndCast(Query q, Class<T> type) {
		
		assert(type != null);
		List<?> list = q.getResultList();
	    if (list != null && list.size() > 0) {
	    	if (list.size() > 1) {
	    		logger.error("应该返回一条记录，但返回了多条！");
	    		throw new NativeSQLException("应该返回一条记录，但返回了多条！");
	    	}

	    	Object value = list.get(0);
	    	if (value == null) {
	    		return null;
	    	}
	    	
	    	if (!value.getClass().equals(type.getClass())) {
	    		// 如果类不相同，则使用Apache BeanUtils尝试转换
	    		Object result = ConvertUtils.convert(value, type);
	    		return (T)result;
	    	} else {
	    		return (T)value;
	    	}
	    } else {
	    	logger.error("没有返回数据！");
	    	 return null;
	    }
	}
	
	/**
	 * 按照给定参数创建查询对象
	 * @param sql SQL
	 * @param params 参数
	 *  @param rowStartIdxAndCount 起始位置和最大返回条数
	 * @return
	 */
	private Query prepareQuery(String sql, Object params, int ... rowStartIdxAndCount ) {
		Query query = entityManager.createNativeQuery(sql);
		setQueryParameters(query, params, rowStartIdxAndCount);
		return query;
	}

	/**
	 * 按照给定参数创建查询对象
	 * @param sql SQL
	 * @param params 参数
	 * @param type 每条记录所对应的对象类型
	 *  @param rowStartIdxAndCount 起始位置和最大返回条数
	 * @return
	 */
	private Query prepareQuery(String sql, Object params, Class<?> type, int ... rowStartIdxAndCount ) {
		if (type == null) {
			return prepareQuery(sql, params, rowStartIdxAndCount);
		} else {
			Query query = entityManager.createNativeQuery(sql, type);
			setQueryParameters(query, params, rowStartIdxAndCount);
			return query;
		}
	}
	
	/**
	 * 按照给定参数创建查询对象，该查询对象返回的结果集每条记录使用Map存储数据。
	 * @param sql SQL
	 * @param params 参数
	 *  @param rowStartIdxAndCount 起始位置和最大返回条数
	 * @return
	 */
	private Query prepareQueryForMap(String sql, Object params, int ... rowStartIdxAndCount ) {
		Query query = entityManager.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		setQueryParameters(query, params, rowStartIdxAndCount);
		return query;
	}
	
	/**
	 * 把参数设定到Query对象中。参数可以是Object[]或Map<?,?>两种形式。
	 * @param sql SQL
	 * @param params 参数。可以是Object[]或Map<?,?>两种形式
	 *  @param rowStartIdxAndCount 起始位置和最大返回条数
	 * @return
	 */
	private void setQueryParameters(Query query, Object params, int ... rowStartIdxAndCount ) {
		if (params != null) {
			if (params instanceof Object[]) {
				setQueryParameters(query, (Object[])params);
			} else if (params instanceof Map<?,?>) {
				setQueryParameters(query, (Map<?,?>)params);
			} else {
				logger.error("只支持Object[]或Map<String, Object>作为参数。当前参数为：{}", params.getClass().getName());
				throw new NativeSQLException("不支持的SQL参数类型：" + params.getClass().getName());
			}
		}
		
		setQueryStartAndCount(query, rowStartIdxAndCount);
	}
	
}
