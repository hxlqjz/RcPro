package com.kdgcsoft.power.service.fw.base;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.jsonplugin.JSONException;
import com.kdgcsoft.power.common.bean.ConflictException;
import com.kdgcsoft.power.common.bean.FlowBean;
import com.kdgcsoft.power.common.bean.Principal;
import com.kdgcsoft.power.common.bean.SystemConfig;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.common.util.ConvertUtil;
import com.kdgcsoft.power.common.util.DBPropertyUtil;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.common.workflow.WebServiceUtil;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.entity.fw.annotation.Code;
import com.kdgcsoft.power.entity.fw.base.BaseDelEntity;
import com.kdgcsoft.power.entity.fw.base.BaseEntity;
import com.ustcinfo.bwp.BwpEngineException;
import com.ustcinfo.bwp.client.support.ServiceFactory;
import com.ustcinfo.bwp.data.service.FlowDataService;
import com.ustcinfo.bwp.modle.ActivityInst;
import com.ustcinfo.bwp.modle.Participant;
import com.ustcinfo.bwp.modle.ProcessInstance;
import com.ustcinfo.bwp.modle.WorkItem;
import com.ustcinfo.bwp.modle.elements.ActivityElement;
import com.ustcinfo.bwp.modle.elements.TransitionElement;
import com.ustcinfo.bwp.service.ActivityService;
import com.ustcinfo.bwp.service.FlowService;
import com.ustcinfo.bwp.service.GlobalTransactionService;
import com.ustcinfo.bwp.service.RollBackService;
import com.ustcinfo.bwp.service.WorkItemService;
import com.ustcinfo.bwp.service.startflow.util.SerializeUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class BaseService {
	
	@Autowired
	private BeetlSQLHelper bsh;
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);
	
	private static Map<String, Method> cachedMethod = new ConcurrentHashMap<String, Method>();
	
	public static String getTableName(Class<?> classtype) {
		Annotation[] anno = classtype.getAnnotations();
		String tableName = "";
		for (int i = 0; i < anno.length; i++) {
			if (anno[i] instanceof Table) {
				Table table = (Table) anno[i];
				tableName = table.name();
			}
		}
		return tableName;
	}
	
	/**
	 * 根据注解获取指定的字段对应表中的字段
	 * @param classtype 
	 * @param type
	 * @return
	 */
	public static String getKeyName(Class<?> classtype, String type) {
		Method[] methods = classtype.getMethods();
		String keyName = "";
		for (Method method : methods) {
			Annotation[] annos = method.getAnnotations();
			boolean flag = false;
			for (Annotation  annotation : annos) {
				if("Id".equals(type)){
					if (!flag && annotation instanceof Id) {
						flag = true;
					}
					
				}else if("Code".equals(type)){
					if (!flag && annotation instanceof Code) {
						flag = true;
					}
				}
				
				if(flag && annotation instanceof Column){
					Column column = (Column)annotation;
					keyName = column.name();
					break;
				}
			}
		}
		return keyName;
	}
	
	/**
	 * 根据主键及编码判断当前编码是否重复
	 * @param code 编码值
	 * @param id 主键id值（若新增时，传null）
	 * @param classtype 要判断的class
	 * @return 重复返回true,不重复返回false
	 */
	/*private Boolean judgeCodeIsRepeat(String code, Long id, Class<?> classtype){
		String tableName = getTableName(classtype);
		String primeryKeyName = getKeyName(classtype, "Id");
		String codeName = getKeyName(classtype, "Code");

		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("tableName", tableName);
		paras.put("codeName", codeName);
		paras.put("primeryKeyName", primeryKeyName);
		paras.put("code", code);
		paras.put("id", id);
		
		Long count = bsh.getLongValue("fw.system.BaseSql.judgeCodeIsRepeat", paras);
		if(count > 0){
			return true;
		}else{
			return false;
		}
		
	}*/
	/**
	 * 新增一个实体之前给创建人,创建时间赋值
	 * @param entity
	 */
	public static void prepareInsert(BaseEntity entity){
		Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
		if(principal!=null){
			entity.setCreateBy(principal.getUserCode());
		}else{
			//暂时默认为来自job
			entity.setCreateBy("powerjob");
		}
		entity.setCreateTime(new Date());
	}
	
	/**
	 * 保存实体之前根据新增还是修改给实体赋值
	 * @param <T>
	 */
	public static <T extends BaseEntity> void prepareSave(T entity) {
		@SuppressWarnings("unchecked")
		Class<T> cls = ((Class<T>) entity.getClass());
		
		Method method = findGetIdMethod(cls);
		if (method == null) {
			LOGGER.error("在Class {} 中未找到id字段的get方法，无法进行预处理！请检查Entity设计是否正确！", cls);
			return;
		}
		
		try {
			Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
			Object id = method.invoke(entity);
			if (!StringUtil.isEmpty(id)) {
				entity.setModifyBy(principal.getUserCode());
			} else {
				prepareInsert(entity);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LOGGER.error("执行反射方法失败！",e);
			throw new RuntimeException("保存处理时发生异常：" +  e.getMessage());
		}
	}
	
	/**
	 * 在Class中查找 @Id 字段的get方法，并缓存。
	 * @param cls 查找目标Class
	 * @return Method 找到的getXXXId()方法
	 */
	private static Method findGetIdMethod(Class<?> cls) {
		String className = cls.getName();		
		Method method = cachedMethod.get(className);
		if (method != null) {
			LOGGER.debug("找到方法缓存：{}", method.getName() );
			return method; 
		}
		
		String primeryKeyName = DBPropertyUtil.columnToProperty2(getKeyName(cls, "Id")).toLowerCase();
		Method[] methods = cls.getMethods();
		for (int i = 0; i < methods.length; i++) {
			method = methods[i];
			if (("get" + primeryKeyName).equals(method.getName().toLowerCase())) {
				cachedMethod.put(className, method);
				return method;
			}
		}
		
		return null;	
	}

	
	/**
	 * 修改一条数据时判断这条数据是否被别人修改
	 * @param oldTime
	 * @param newTime
	 * @return 修改返回true,未修改返回false
	 */
	public static boolean isUpdateByOther(Long oldTime, Long newTime){
		if(StringUtil.isEmpty(oldTime)&& StringUtil.isEmpty(newTime)){
			return false;
		} else if(StringUtil.isEmpty(newTime)) {
    		return false;
    	} else if (StringUtil.isEmpty(oldTime)|| StringUtil.isEmpty(newTime)) {
    		return true;
    	} 
        Long diff = newTime - oldTime;
        return diff!=0;
    }
	/**
	 * 将request 里获取到的map 的值 放到entity里面
	 * @param map
	 * @param entity
	 * @param cls
	 * @return
	 */
	protected static <T extends BaseEntity> T  reflectMapToEntity(Map<String,String[]> map,T entity ,Class<T> cls){
		Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
		entity= ConvertUtil.mapArrayToBean(map, entity);
		entity.setModifyBy(principal.getUserCode());
		return entity;
	}
	/**
	 * 公用保存方法
	 * @param map request获取的map数组
	 * @param entity 前台传递的entity
	 * @param dao 保存实体对应的dao
	 * @return
	 */
	public static <T extends BaseEntity> T saveEntity(JpaRepository<T, Long> dao, Map<String,String[]> map,T entity)  {
		@SuppressWarnings("unchecked") 
		Class<T> cls = ((Class<T>)entity.getClass());
		if(entity instanceof BaseDelEntity){
			((BaseDelEntity) entity).setIsUse(SystemConstants.IS_USE_Y);
		}
		String primeryKeyName =DBPropertyUtil.columnToProperty2(getKeyName(cls, "Id").toLowerCase());
		if(!StringUtil.isEmpty(map)){
		if(StringUtil.isEmpty(map.get(primeryKeyName)[0])){
			prepareInsert(entity);
			return dao.save(entity);
			} else {
				Long id = Long.parseLong(map.get(primeryKeyName)[0]);
				T oldEntity = dao.findOne(id);
				if (StringUtil.isEmpty(oldEntity.getModifyTime())
						|| StringUtil.isEmpty(entity.getModifyTime())) {
					reflectMapToEntity(map, oldEntity, cls);
					return dao.save(oldEntity);
				} else {
					if (!isUpdateByOther(oldEntity.getModifyTime().getTime(),
							entity.getModifyTime().getTime())) {
						reflectMapToEntity(map, oldEntity, cls);
						return dao.save(oldEntity);
					} else {
						throw new ConflictException(
								SystemConstants.MSG_IS_UPDATE);
					}
				}
			}
		} else {
			prepareInsert(entity);
			return dao.save(entity);
		}
	}
	/**
	 * 公用删除方法
	 * @param id 主键id
	 * @param dao 实体对应的dao
	 * @return
	 */
	public static <T extends BaseEntity> T delEntity(Long id,JpaRepository<T, Long> dao,Long modifyTime) {
		Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
		T entity = dao.findOne(id);
		Date Time = entity.getModifyTime();
		if(!StringUtil.isEmpty(entity.getModifyTime())&&!isUpdateByOther(entity.getModifyTime().getTime(), modifyTime)){
		if(entity instanceof BaseDelEntity) {
			BaseDelEntity tmpEntity =  (BaseDelEntity)entity;
			tmpEntity.setIsUse(SystemConstants.IS_USE_N);
			tmpEntity.setModifyBy(principal.getUserCode());
			return dao.save(entity);
		} else {
			dao.delete(id);
			return null;
		}
		} else {
			throw new ConflictException(SystemConstants.MSG_IS_UPDATE);
		}
	}
	/**
	 * 上报
	 * 
	 * @param id
	 *          业务主键id
	  * @param typeRediao
	 *          审批radio
	 * @param processDefName
	 *          流程定义名称编号
	 * @return FlowBean
	 *          流程实例id和环节定义id
	 */
	public FlowBean report(Long id, String typeRediao, String processDefName)
			throws IOException, JSONException, BwpEngineException {
		Principal principal = (Principal) SecurityUtils.getSubject()
				.getPrincipal();
		// 初始化txnService、flowService
		FlowService flowService = ServiceFactory.getFlowService();
		GlobalTransactionService txnService = ServiceFactory
				.getGlobalTxnService();
		ActivityService activityService = ServiceFactory.getActivityService();
		FlowBean flowbean = new FlowBean();
		// 当前登录人code
		String userId = principal.getUserCode();
		LOGGER.info("创建流程->工单入库->启动流程");
		Long processInstId = 0l;
		try {
			// 开启事务
			txnService.begin();
			// 1.调用接口创建并启动流程			
			processInstId = flowService.startProcess(processDefName, userId);
			
			// 结束当前环节
			// activityService.finishCurrentActivity(processInstId, userId);
			// 提交事务
			txnService.commit();
			LOGGER.info("事务提交成功！");
			// 获取流程当前所有运行的环节
			List<ActivityInst> nowNode = activityService
					.getAllCurrentActivitys(processInstId);
			// 环节定义id
			String activityDefId = nowNode.get(0).getActivityDefId();
			flowbean.setProcessinstid(processInstId);
			flowbean.setAuditStatus(activityDefId);
			return flowbean;
		} catch (BwpEngineException e) {
			LOGGER.error("上报失败", e);
			flowbean.setProcessinstid(null);
			flowbean.setAuditStatus(null);
			// 4.业务补偿
			// orderCreateService.orderCreateCompensate(processInstId);
			try {
				// 事务回滚
				txnService.rollback();
			} catch (BwpEngineException e1) {
				LOGGER.error("事务回滚失败", e1);
				throw e1;
			}
			return flowbean;
		}
	}
	
	// 待办
		public String BwpWorkItemListQueryWSServer() {
			Principal principal = (Principal) SecurityUtils.getSubject()
					.getPrincipal();
			String str = "" ;
			// queryParticipantWorkItemListWithPending 调用方法
			String nameSpaceUriBwpWorkItemListQueryWSServer = SystemConfig.FLOW_URL;
			try {
				String nameSpaceUri = nameSpaceUriBwpWorkItemListQueryWSServer; // url地址
				Map<String, String> codeTypeAndPart = new HashMap<String, String>();
				
				Map<String,Object> busiInfo = new HashMap<String,Object>();
				
				String[] taskSource = new String[] {};
				taskSource = new String[] { "SELF" };
				codeTypeAndPart.put("person", principal.getUserCode());
				Object[] parameter = new Object[] { taskSource, codeTypeAndPart,
						busiInfo, 1, 100 };

				str = (String) WebServiceUtil.execute(nameSpaceUri,
						"queryParticipantWorkItemListWithPending", parameter);
				
				LOGGER.info(str);
				
			} catch (Exception e) {
				LOGGER.error("获取待办失败！", e);
				throw new RuntimeException("获取代办失败！");
			}
			return str ;
		}
		
         //获得所有代办的实例id（用于审核页面数据展示）
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Object[] getProcessInstIds(String pendings){
			Object[] processInstIds = new Object[]{};
			
			if (StringUtil.isEmpty(pendings)) {
				return processInstIds;
			}
			
			JSONObject jsonObject = JSONObject.fromObject(pendings);  
	        Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);  	        
	        JSONArray jsonArray = JSONArray.fromObject(mapJson.get("workItemList"));  
			List<Map<String,Object>> mapListJson = (List)jsonArray; 
			processInstIds = new Object[mapListJson.size()];
			int i = 0 ;
	        for(Map<String,Object> map : mapListJson){
	        	processInstIds[i++] =  map.get("processInstId");
	        }
	        return processInstIds ;
		}
		
		/**
		 * 审批
		 * 
		 * @param id
		 *          业务主键id
		  * @param typeRediao
		 *          审批radio
		 * @param processInstId
		 *            流程实例id
		 * @param userCode
		 *            下一步审批人
		 * @param roleOrPerson
		 *            下一步审批人
		 *            
		 * @param orgCode 流程图上扩展属性的角色需要根据单位过滤的        
		 * @param deptCode 流程图上扩展属性的角色需要根据部门过滤的 
		 *   
		 * @return Map<String,Object>
		 *       流程实例id和环节定义id
		 */
		@RequestMapping(value = "approve")
		@ResponseBody
		public FlowBean approve(Long id, String typeRediao,Long processInstId,
				String userCode,String roleOrPerson,String orgCode,String deptCode) throws IOException,JSONException, BwpEngineException {
			// 初始化txnService、flowService
			GlobalTransactionService txnService = ServiceFactory.getGlobalTxnService();
			ActivityService activityService = ServiceFactory.getActivityService();
			WorkItemService workItemService = ServiceFactory.getWorkItemService();
			FlowDataService flowDataService = ServiceFactory.getFlowDataService();
			RollBackService rollBackService = ServiceFactory.getRollBackService();
			Principal principal = (Principal) SecurityUtils.getSubject()
					.getPrincipal();
			FlowBean flowbean = new FlowBean();
			String radioArray[] = null;
			String conditions[] = null;
			String conditionns = "";
			//当前登录人
			String userId =principal.getUserCode();
			LOGGER.info("审批流程");
			
			// FIXME 下面应该删掉
			// 待办测试
			this.BwpWorkItemListQueryWSServer();
			
			if (!StringUtil.isEmpty(typeRediao)) {
				if (typeRediao.indexOf(",") != -1) {
					radioArray = typeRediao.split(",");
					if (typeRediao.indexOf("#") != -1) {
						conditionns = radioArray[1];
						String condition = radioArray[1].replace("#", "");
						if (condition.indexOf(">=") != -1 || condition.indexOf("<=") != -1) {
							condition = condition.replace(">=", ",").replace("<=",",");
						} else {
							condition = condition.replace(">", ",").replace("<", ",").replace("==", ",");
						}
						conditions = condition.split(",");
					}
				}
			}

			try {
				// 开启事务
				txnService.begin();
				// 获取流程当前所运行的环节
				List<ActivityInst> activityInst = activityService.getAllCurrentActivitys(processInstId);
				// 环节定义id
				String activityDefId = activityInst.get(0).getActivityDefId();
				// 环节实例id
				Long activityInstId = activityInst.get(0).getActivityInstId();
				if (!StringUtil.isEmpty(conditionns) && "TUH".equals(conditions[0].trim())) {
					// 根据流程实例ID查询在途库中的环节实例
					List<ActivityInst> activityInsts = flowDataService.getActInstsByProcessInstId(processInstId);
					// 回退目标环节实例id
					Long destActivityInstId = activityInsts.get(1).getActivityInstId();
					if (!StringUtil.isEmpty(conditions)) {
						// 获取连线条件相关数据区
						Map<String, Object> hashMap = new HashMap<String, Object>();
						// hashMap.put("TUH",3);
						hashMap.put(conditions[0].trim(),Integer.parseInt(conditions[1].trim()));
						// 设置连线规则信息
						activityService.setExpressConditions(processInstId,activityDefId, hashMap);
					}
					// 回退到任意目标环节
					rollBackService.rollbackToAnyActivity(processInstId,activityInstId, destActivityInstId, true);
					activityDefId = activityInsts.get(0).getActivityDefId();
				} else {
					// 设置连线条数相关数据
					List<Participant> nextActParticipants = new ArrayList<Participant>();
					Participant nextperson = new Participant();
					if(!StringUtil.isEmpty(userCode)){
						//指定参与者
						// particType放参与者类型，role或organization或person
						nextperson.setParticType("person");
						// participant字段放参与者userCode
						nextperson.setParticipant(userCode);
						// participant2字段放参与者名称
						nextperson.setParticipant2(roleOrPerson);
						
						//无参与者时设置当前登录人为参与者
						if(StringUtil.isEmpty(nextperson.getParticipant())){
							// particType放参与者类型，role或organization或person
							nextperson.setParticType("person");
							// participant字段放参与者userCode
							nextperson.setParticipant(principal.getUserCode());
							// participant2字段放参与者名称
							nextperson.setParticipant2(principal.getUserName());
						}
						
						nextActParticipants.add(nextperson);
					}else{
						//无指定参与者，有环节扩展属性配置的角色设定
						if(!StringUtil.isEmpty(conditionns)){
							String roleIds = this.findRoleIds(processInstId, conditionns);
							if(!StringUtil.isEmpty(roleIds)){
							   //根据角色查找人(根据orgCode 和 deptCode 过滤)
								Map<String,Object> para = new HashMap<String,Object>();
								if(!StringUtil.isEmpty(orgCode)){
								para.put("orgCode", orgCode);
								}
								if(!StringUtil.isEmpty(deptCode)){
									para.put("deptCode", deptCode);
								}
								para.put("roleIds", roleIds.split(","));
								List<Map<String,Object>> list = bsh.getMapList("fw.system.SysCRole.getUserByRole", para);
								for(Map<String,Object> map :list){
									Participant par = new Participant();
									//指定参与者
									// particType放参与者类型，role或organization或person
									par.setParticType("person");
									// participant字段放参与者userCode
									par.setParticipant(map.get("userCode").toString());
									// participant2字段放参与者名称
									par.setParticipant2(map.get("userName").toString());
									
									nextActParticipants.add(par);
								}
							}
						}
					}
					
					if (!StringUtil.isEmpty(conditions)) {
						// 获取连线条件相关数据区
						Map<String, Object> hashMap = new HashMap<String, Object>();
						// hashMap.put("DAYS",3);
						hashMap.put(conditions[0].trim(),Integer.parseInt(conditions[1].trim()));
						// 设置连线规则信息
						activityService.setExpressConditions(processInstId,activityDefId, hashMap);
					}
					// 获取流程当前所有运行的环节
					List<WorkItem> workItem = flowDataService.getActiveWorkItem(processInstId, activityInstId);
					if(!StringUtil.isEmpty(workItem)){
							Map<String,Object> workItemsMap = new HashMap<>();
							for(int i=0;i<workItem.size();i++){
								// 工作项Id
								Long workItemsIdnew = workItem.get(i).getWorkItemId();
								String userIdnew = workItem.get(i).getParticipant(); 
								int bizState = workItem.get(i).getBizState();
								if(userIdnew.equals(userId) && bizState !=12){
									workItemsMap.put("workItemsId", workItemsIdnew);
									workItemsMap.put("userId", userIdnew);
								}
							}
							if(workItem.size()==1){
								// 设置下一个环节参与者相关数据区
								activityService.setNextActParticipants(processInstId,activityDefId, 
												nextActParticipants);// 设置下一步审批人
							}
							  Long workItemsId = Long.parseLong(workItemsMap.get("workItemsId").toString());
								// 结束当前工作项和环节
								workItemService.finishWorkItem(processInstId, workItemsId,userId, "", "");
							
					}else{
						// 设置下一个环节参与者相关数据区
						activityService.setNextActParticipants(processInstId,activityDefId, 
										nextActParticipants);// 设置下一步审批人
						// 结束当前环节
						 activityService.finishCurrentActivity(processInstId, userId);
					}
				}

				// 提交事务
				txnService.commit();
				LOGGER.info("事务提交成功！");
				flowbean.setProcessinstid(processInstId);
				flowbean.setAuditStatus(activityDefId);
				return flowbean;
			} catch (BwpEngineException e) {
				LOGGER.error("审批失败",e);
				flowbean.setProcessinstid(null);
				flowbean.setAuditStatus(null);
				// 4.业务补偿
				// orderCreateService.orderCreateCompensate(processInstId);
				try {
					// 事务回滚
					txnService.rollback();
				} catch (BwpEngineException e1) {
					LOGGER.error("事务回滚失败",e);
					throw e1;
				}
				return flowbean;
			}
			
		}
		
		/**
		 * 获取下一步参与角色（环节扩展属性）
		 * 
		 * @param processInstId
		 *            流程实例id
		 * @param conditions
		 *            连线条件键值对conditions
		 * @return String
		 */
		public String findRoleIds(Long processInstId,String conditions){
			String roleId = "";
			String activityDefIdnew = null;
			ActivityElement act = this.getActElementsByProcessDefId(processInstId,null);
			 // 当前环节后数据
			List<TransitionElement> afteStept = act.getAfterTrans();
			 Iterator<TransitionElement> afteMap = afteStept.iterator();    
			    while (afteMap.hasNext()) {    
			    	TransitionElement element = afteMap.next();    
			        if (element.getComplexExpressionValue().equals(conditions)){
			        	activityDefIdnew = element.getTo();
			        }    
			     } 
			 if(!StringUtil.isEmpty(activityDefIdnew)){
				 ActivityElement actnew = this.getActElementsByProcessDefId(processInstId,activityDefIdnew);
				 if(!StringUtil.isEmpty(actnew.getProperties())){
					 // 环节扩展属性（参与角色）
					 Map<String, String> properties = actnew.getProperties();
					 for (String key : properties.keySet()) {
						 roleId = roleId + "," + properties.get(key);
					 }  
				 }
			 }   
		    if(!StringUtil.isEmpty(roleId)){
		    	roleId = roleId.substring(1, roleId.length());
		    }
			return roleId;
		}
		
		/**
		 * 获取当前环节相关信息
		 * 
		 * @param processInstId
		 *            流程实例id
		 * @param activityDefId
		 *            获取环节定义id
		 * @return ActivityElement
		 */
		public ActivityElement getActElementsByProcessDefId(Long processInstId,String activityDefId){
			// 初始化txnService、flowService
			FlowService flowService = ServiceFactory.getFlowService();
			ActivityService activityService = ServiceFactory.getActivityService();
			FlowDataService flowDataService = ServiceFactory.getFlowDataService();
			// 根据流程实例id查询流程实例
			ProcessInstance wfentity = flowDataService.getProcInstanceByProcInstId(processInstId);
			// 获取流程当前所有运行的环节
			List<ActivityInst> nowNode = activityService.getAllCurrentActivitys(processInstId);
			if(StringUtil.isEmpty(activityDefId)){
				activityDefId = nowNode.get(0).getActivityDefId(); // 获取环节定义id（例如 Act_1,Act_3）
			}
			// 3. 根据流程定义id获取所有环节定义的信息
			Map<String, ActivityElement> map = flowService.getActElementsByProcessDefId(wfentity.getProcessDefId());
			// 获取当前环节后面的信息
			String json = SerializeUtils.serialize(map.get(activityDefId));
			ActivityElement act = SerializeUtils.unserialize(json,ActivityElement.class);
			return act;
		}
		
		
		/**
		 * 根据流程实例id终止流程
		 * @param processInstId 流程实例ID
		 * @return boolean
		 * @throws BwpEngineException
		 */
		public boolean terminateProcess(Long processInstId) throws BwpEngineException{
			// flowService
			FlowService flowService = ServiceFactory.getFlowService();
			return flowService.terminateProcess(processInstId);
		}
}
