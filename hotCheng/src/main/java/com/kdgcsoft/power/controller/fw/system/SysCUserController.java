package com.kdgcsoft.power.controller.fw.system;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kdgcsoft.power.common.bean.ConflictException;
import com.kdgcsoft.power.common.bean.DicConstants;
import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.common.bean.PageModel;
import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.common.bean.Principal;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.common.support.Monitor;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.entity.business.interact.MsgLog;
import com.kdgcsoft.power.entity.fw.system.SysCUser;
import com.kdgcsoft.power.entity.fw.system.SysCUserDept;
import com.kdgcsoft.power.entity.fw.system.SysCUserRole;
import com.kdgcsoft.power.service.business.interact.MsgLogService;
import com.kdgcsoft.power.service.fw.system.SysCUserRoleService;
import com.kdgcsoft.power.service.fw.system.SysCUserService;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**   
 * @Title: Controller
 * @Description: 代码生成工具自动生成
 * @date 2017-06-27
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/SysCUser")
public class SysCUserController extends BaseController {
	@Autowired
	private SysCUserService sysCUserService;
	@Autowired
	private SysCUserRoleService sysCUserRoleService;
	@Autowired
	private MsgLogService msgLogService;
	
	/**
	 * 分页查询数据,带分页和模糊搜索
	 */
	@RequestMapping(value="/searchSysCUser",method = RequestMethod.POST)
	@ResponseBody
	public PageModel searchSysCUser(HttpServletRequest request, Integer page, Integer rows){
		PageRequest pageRequest = new PageRequest(page , rows);
		String deptCode = request.getParameter("deptCode");
		String userName = request.getParameter("userName");
		PageObject<Map<String,Object>> result=new PageObject<Map<String,Object>> ();
		
		List<Map<String,Object>> ls = sysCUserService.searchSysCUser2(deptCode,userName,pageRequest);
		
		List<Map<String,Object>>rs= CollectionUtil.sub(ls, (page-1)*rows, page*rows);
		result.setTotalCount((long)ls.size());	
		result.setList(rs);
		return getPageModel(result);
//		PageObject<SysCUser> result = sysCUserService.searchSysCUser2(deptName,userName, pageRequest);
	}
	

	/**
	 * 查找单条数据
	 */
	@RequestMapping(value = "/findSysCUser")
	@ResponseBody
	public Object findSysCUser( Long id) {
		return sysCUserService.findSysCUser(id);
	}


	/**
	 * 保存数据
	 * @throws ConflictException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveSysCUser")
	@ResponseBody
	public JsonMsg saveSysCUser(HttpServletRequest request,SysCUser entity) throws ConflictException{
		Map<String,String[]> map = request.getParameterMap();
		Map<String,Object> res= sysCUserService.saveSysCUser(map,entity,SysCUser.class);
		return new JsonMsg(true,SystemConstants.MSG_SAVE_SUCCESS,res);
	}
	
	/**
	 * (注册)保存数据
	 * @throws ConflictException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/registerSaveSysCUser")
	@ResponseBody
	public JsonMsg registerSaveSysCUser(HttpServletRequest request,SysCUser entity) throws ConflictException{		
		String userCode=entity.getUserCode();
		SysCUser user=sysCUserService.findByUserCode(userCode);		
		JsonMsg msg=new JsonMsg();		
		if(user.getUserId()!=null){
			msg.setSuccess(false);
			msg.setMsg("该用户已存在！");
		}else{
			Map<String,String[]> map = request.getParameterMap();
			entity.setDeptCode("100000");
			entity.setIsLongterm("Y");
			entity.setFlag("Y");
			entity.setUserName(userCode);
			entity.setTel(userCode);
			Map<String,Object> res= sysCUserService.saveSysCUser(map,entity,SysCUser.class);
			saveMsgLogInfo(userCode);//向日志表插入注册信息
			msg.setMsg(SystemConstants.MSG_SAVE_SUCCESS);
			msg.setSuccess(true);
			msg.setData(res);
		}	
		return msg;
	}
	
	private void saveMsgLogInfo(String userCode){
		 String msg=userCode+"用户您好！您的手机账户注册成功，欢迎使用！";
		 String type="1";//注册类型
		 MsgLog en=new MsgLog();
		 en.setCreateTime(new Date());
		 en.setIsUse("Y");
		 en.setUserCode(userCode);
		 en.setType(type);
		 en.setMsg(msg);
		 msgLogService.saveMsgLog(en);
		 
	};
	
	@RequestMapping(value="resetSysCUserPswd")
	@ResponseBody
	public JsonMsg resetSysCUserPswd(HttpServletRequest request, SysCUser entity){
		String ps = entity.getPswd();
		sysCUserService.updateUserPswd(entity,entity.getPswd());
		return new JsonMsg("密码重置成功！");
	}
	
	@RequestMapping(value="getResetUserPwdPage")
	@ResponseBody
	public ModelAndView getResetUserPwdPage(Long userId){
		ModelAndView model = new ModelAndView();
		SysCUser entity = sysCUserService.findSysCUser(userId);
		
		model.addObject("entity", entity);
		model.setViewName("system/ResetUserPwd");
		return model;
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions(value = { "user:delete"})
	@RequestMapping(value = "/delSysCUser")
	@ResponseBody
	public Object delSysCUser(@RequestParam(required=true) Long id) {
		sysCUserService.delSysCUser(id);//直接删除
		return new JsonMsg();
		
	}
	// 新增，修改弹出框
	@RequestMapping(value = "/loadSysCUser")
	public ModelAndView loadSysCUser(String id, HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		SysCUser entity = new SysCUser();
		String orgCode = null;
		String deptCode = null;
		if(!StringUtil.isEmpty(id)){//修改
			entity = sysCUserService.findSysCUser(Long.parseLong(id));
			SysCUserDept sysCUserDept = sysCUserService.findSysCUserDeptByUserCode(entity.getUserCode());
			if (sysCUserDept.getOrgCode()!=null){
				orgCode = sysCUserDept.getOrgCode();
				if(!sysCUserDept.getOrgCode().equals(sysCUserDept.getDeptCode())){
					deptCode = sysCUserDept.getDeptCode();
				}
			}
			
		}else{
			Principal principal = getEmpSession();
			orgCode = principal.getOrgCode();
			entity.setFlag(SystemConstants.FLAG_Y);
		}
		model.addObject("orgCode", orgCode);
		model.addObject("deptCode", deptCode);
		model.addObject("entity", entity);
		model.setViewName("system/SysCUserform");
		return model;
	}
	
	
	/**
	 * 新增修改弹窗
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value = "/loadSysCUser2")
	public ModelAndView loadSysCUser2(String id){
	ModelAndView model = new ModelAndView();
		if(!StringUtil.isEmpty(id)){//修改
			SysCUser entity = sysCUserService.findSysCUser(Long.parseLong(id));
			model.addObject("entity", entity);
		}
		model.setViewName("system/SysCUserform");
		return model;
	}

	
	@RequestMapping(value="getUserSelectRolePage")
	@ResponseBody
	public ModelAndView getUserSelectRolePage(Long userId){
		ModelAndView model = new ModelAndView();
		
		String userCode = sysCUserService.findSysCUser(userId).getUserCode();
		
		model.addObject("userCode", userCode);
		model.setViewName("system/UserSelectRole");
		return model;
	}
	
	@RequestMapping(value="querySelectedRole")
	@ResponseBody
	public PageModel querySelectedRole(String userCode, String roleName, Integer page, Integer rows){
		PageRequest pageRequest = new PageRequest(page, rows);
		PageObject<?> pageObject = sysCUserRoleService.querySelectedRole(userCode, roleName, pageRequest);
		return getPageModel(pageObject);
	}
	
	@RequestMapping(value="queryUnselectedRole")
	@ResponseBody
	public PageModel queryUnselectedRole(String userCode, String roleName, Integer page, Integer rows){
		PageRequest pageRequest = new PageRequest(page, rows);
		PageObject<?> pageObject = sysCUserRoleService.queryUnselectedRole(userCode, roleName, pageRequest);
		return getPageModel(pageObject);
	}
	
	@RequestMapping(value="moveSelectedRole")
	@ResponseBody
	public JsonMsg moveSelectedRole(String urIds){
			
		String[] urId_array = urIds.split(",");
		for(String urId : urId_array){
			sysCUserRoleService.delete(Long.parseLong(urId));
		}
		
		return new JsonMsg("成功移除用户！");
	}
	
	@RequestMapping(value="moveUnselectedRole")
	@ResponseBody
	public JsonMsg moveUnselectedRole(String userCode, String roleCodes){
		
		SysCUser model = sysCUserService.findByUserCodeAndIsUse(userCode);
		
		String[] roleCode_array = roleCodes.split(",");
		for(String roleCode : roleCode_array){
			SysCUserRole entity = new SysCUserRole();
			
			entity.setUserCode(userCode);
			entity.setRoleCode(roleCode);
			if(model != null){
				entity.setOrdBy(model.getOrdBy());
			}
			
			sysCUserRoleService.save(entity);
		}
		
		return new JsonMsg("成功添加到角色！");
	}
	
	/**
	 * 通过部门条件查询人员,flag表示查询的人员用于专家信息注册
	 * @param deptCode
	 * @param userName
	 * @param flag
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="getUserListByDeptCode")
	@ResponseBody
	public PageModel getUserListByDeptCode(String deptCode, String userName, String flag, Integer page, Integer rows){
		PageRequest pageRequest = new PageRequest(page, rows);
		PageObject<Map<String, Object>> result = sysCUserService.getUserListByDeptCode(deptCode, userName, flag, pageRequest);
		dictService.updateListWithDict(result.getList(), DicConstants.EXPERTGRADE, "expertGrade");
		return getPageModel(result);
	}
	
	/**
	 * 查询当前登录人是否是项目公司的人
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="queryIsItemCompany")
	@ResponseBody
	public Object queryIsItemCompany(){
		Map<String, Object> result = sysCUserService.queryIsItemCompany();
		return result;
	}
	
}
