

/** 系统常量 **/
var Constants = {};

Constants.CONFIRM_SAVE = "确认要保存吗？";
Constants.CONFIRM_DEL = "确认要删除吗？";
Constants.CONFIRM_CANCEL = "确认要取消吗？";
Constants.CONFIRM_REPORT = "确认要上报吗？";
Constants.SAVE_SUCCESS = "保存成功!";
Constants.DEL_SUCCESS="删除成功!";
Constants.REPORT_SUCCESS="上报成功!";
Constants.APPROVE_SUCCESS="审批成功!";
Constants.CONFIRM="确定";

Constants.DISPLAY_MSG="显示第 {0} 条到 {1} 条记录，一共 {2} 条";
Constants.EMPTY_MSG="没有记录";
Constants.PAGE_SIZE = 20;
Constants.AUTO_CREATE = "自动生成";
Constants.DATA_SAVING="正在保存数据...";
Constants.NOTICE="注意";
Constants.ERROR="错误";
Constants.UNKNOWN_ERR="出现未知错误.";
Constants.USER_CHECK_ERROR = "用户名或密码输入错误";
Constants.SYS_REMIND_MSG="提示信息";
Constants.SELECT_COMPLEX_MSG="请选择其中一项进行编辑！";
Constants.SELECT_NULL_UPDATE_MSG="请先选择要编辑的行！";
Constants.SELECT_NULL_DEL_MSG="请选择要删除的记录！";
Constants.OPERATE_ERROR_MSG="操作失败,请联系管理员!";
Constants.DEL_ERROR="删除时出现未知错误!";

Constants.ALL_SELECT = "全部";
Constants.rootDeptId = "4";
Constants.rootDeptName = "目录树";
Constants.rootNodeCode = "root";
Constants.zyCExpertInfo = "TAL_C_EXPERT_INFO";
Constants.trJTrainPlanDetail = "TR_J_TRAIN_PLAN_DETAIL";
Constants.flowViewUrl  = "http://192.168.80.62:9092/bwp/flowMonitor/viewAdminFlow";

/**
 * 数据--是否启用
 */
Constants.flag = [{
	text : '正常',
	value : 'Y'
}, {
	text : '禁用',
	value : 'N'
}]

/**
 * 数据--是否启用
 */
Constants.pageType = [{
	text : '目录',
	value : 'folder'
}, {
	text : '页面',
	value : 'page'
}]

/** 数据字典类型常量 **/
var DicConstants = {};
//报表时段类型
DicConstants.REPORT_TYPE = "reportType";
//控件类型
DicConstants.CTRLTYP = "ctrlTyp";
//布局方向
DicConstants.DIRECTION = "direction";
//账号类型
DicConstants.ACNTUSERTYP = "acntUserTyp";
//页面打开方式
DicConstants.PAGEOPENTYP = "pageOpenTyp";
//按钮权限
DicConstants.PERMBUTTON = "permButton";
//数据权限
DicConstants.PERMDATA = "permData";
//部门分类属性
DicConstants.DEPTCAT = "deptCat";
//省、城市、区域选择项
DicConstants.REGION = "region";
//页面类型
DicConstants.PAGETYP = "pageTyp";
//专家带级
DicConstants.EXPERTGRADE = "expertGrade";
//项目类型
DicConstants.ITEMTYPE = "itemType";
//改善项目分类
DicConstants.CHANGEITEMCLASS = "changeItemClass";
//指标单位
DicConstants.ITEMUNIT = "itemUnit";
//符号
DicConstants.ITEMSIGN = "itemSign";
//性别
DicConstants.GENDER = "gender";
