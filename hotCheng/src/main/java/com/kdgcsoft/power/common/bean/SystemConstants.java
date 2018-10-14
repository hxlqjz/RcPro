package com.kdgcsoft.power.common.bean;

/**
 * 业务系统常量定义
 */
public class SystemConstants {
	
	//返回消息
	public static final String MSG_SUCCESS = "操作成功";
	public static final String MSG_FAILURE = "操作失败";
	public static final String MSG_SAVE_SUCCESS = "保存成功";
	public static final String MSG_SAVE_FAILURE = "保存失败";
	public static final String MSG_ADD_SUCCESS = "添加成功";
	public static final String MSG_ADD_FAILURE = "添加失败";
	public static final String MSG_DELETE_SUCCESS = "删除成功";
	public static final String MSG_DELETE_FAILURE = "删除失败";
	public static final String MSG_REPORT_SUCCESS = "上报成功";
	public static final String MSG_REPORT_FAILURE = "上报失败";
	public static final String MSG_APPROVE_SUCCESS = "审批成功";
	public static final String MSG_APPROVE_FAILURE = "审批失败";
	public static final String MSG_CODE_REPEAT = "编码重复";
	public static final String MSG_IS_UPDATE = "数据已更新，请刷新数据后再修改保存！";
	
	//初始密码
	public final static String INIT_PASS = "123";
	//密码盐值
	public final static String PASS_SALT = "gcadmin";
	//头像上传文件夹
	public final static String FILE_PATH_AVATAR = "touxiang";
	//案例上传文件夹
	public final static String FILE_PATH_EXAMPLE = "anli";
	//学习资料上传文件夹
	public final static String FILE_PATH_MATERIAL = "xuexiziliao";
	
	
	//java代码路径
	public final static String JAVA_DIRECTORY = "/src/main/java/com/kdgcsoft/power";
	//jsp代码路径
	public final static String JSP_DIRECTORY = "/src/main/webapp/WEB-INF/views/";
	//js 代码路径
	public final static String JS_DIRECTORY = "/src/main/webapp/static/js/";
	//sql 代码路径
	public final static String SQL_DIRECTORY = "/src/main/resources/beetlsql/";
	
	//超级管理员
	public final static String SUPER_ADMIN = "gcadmin";
	//是否使用（是否删除标志）
	public final static String IS_USE_Y = "Y";
	public final static String IS_USE_N = "N";
	//是否启用
	public final static String FLAG_Y = "Y";
	public final static String FLAG_N = "N";
	/**
	 * 课题立项---过程评价活动记录参与者状态（参加）
	 */
	public final static String PARTICIPATE_TYPE_ATTEND = "0";
	/**
	 * 课题立项---过程评价活动记录参与者状态（缺席）
	 */
	public final static String PARTICIPATE_TYPE_ABSENT = "1";
	/**
	 * 课题立项---过程评价活动记录状态（保存）
	 */
	public final static String ACTIVITY_STATE_SAVE = "S";
	/**
	 * 课题立项---过程评价活动记录状态（提交）
	 */
	public final static String ACTIVITY_STATE_COMMIT = "C";
    //课题立项表
    public final static String PRO_TABLE = "PRO_J_PROJECT_INFO";
    //课题立项审核页面
    public final static String PRO_APPROVE_VIEW = "views/business/ktproject/ProJProjectInfoApprove.action";
    //专家信息表
    public final static String EXPERT_TABLE = "TAL_C_EXPERT_INFO";
    //课题验收表
    public final static String CHECK_TABLE = "PRO_J_PROJECT_CHECK";


}
