package com.kdgcsoft.power.controller.fw.system;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kdgcsoft.power.common.ResponseData;
import com.kdgcsoft.power.common.bean.EasyUITreeNode;
import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.common.bean.Principal;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.common.util.AjaxUtil;
import com.kdgcsoft.power.common.util.MD5Util;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.entity.fw.system.SysCUser;
import com.kdgcsoft.power.service.fw.system.SysCMenuService;
import com.kdgcsoft.power.service.fw.system.SysCUserService;


@Controller
@RequestMapping(value="/system")
public class HomeLoginController extends BaseController{
	@Autowired
    private SysCUserService sysCUserService;
	
	@Autowired
	private SysCMenuService sysCMenuService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeLoginController.class);
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(String workCode,String password,Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		view.setViewName("view/homepage/index.jsp");

		Principal principal = getEmpSession(); // 当前登陆人员信息
		if(principal != null){
			setMainFrameView(view);
			return view;
		} else {
			view.setViewName("view/homepage/index.jsp");
			return view;
		}
	}
	
	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST)
	@ResponseBody
	public Object loginFail(String workCode,String password,Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String msg = "";
		ModelAndView view = new ModelAndView();

		Object principal = SecurityUtils.getSubject().getPrincipal();
		// 如果已经登录，则跳转到管理首页
		if (principal != null) {
			if(AjaxUtil.isAjaxRequest((HttpServletRequest) request)){
				JsonMsg jsonMsg = new JsonMsg();
				 jsonMsg.setSuccess(true);
				return jsonMsg;
			}else{
				setMainFrameView(view);
				return view;
			}
			
		}

		Object exception = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (exception instanceof IncorrectCredentialsException || exception instanceof UnknownAccountException) {
			 msg = "用户或密码错误, 请重试！";
		} else if (exception instanceof DisabledAccountException) {
			msg = "账号被禁用！";
		} else if (exception instanceof ExcessiveAttemptsException) {
			msg = "登录失败次数过多！";
		} else if (exception instanceof ExpiredCredentialsException) {
			msg = "凭证过期！";
		} else if (exception instanceof AuthenticationException) {
			msg = "登录密码错误！";
		} else if (exception instanceof UnauthorizedException) {
			msg = "非授权异常！";
		} else {
			msg = "用户或密码错误, 请重试！";
		}

       logger.error(msg);
        view.setViewName("view/homepage/index.jsp");
        model.addAttribute("errMsg", msg);
        return view;
	}
	
	@RequestMapping(value="/userLogin", method = RequestMethod.GET)
	public ModelAndView userLogin(String workCode,String password,Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		view.setViewName("view/homepage/mainFrame.jsp");  

		Principal principal = getEmpSession(); // 当前登陆人员信息
		if(principal != null){
			setMainFrameView(view);
			return view;
		} else {
			view.setViewName("view/homepage/index.jsp");
			return view;
		}
	}
	
	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value="/userLogin", method = RequestMethod.POST)
	@ResponseBody
	public Object userLoginFail(String workCode,String password,Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String msg = "";
		ModelAndView view = new ModelAndView();
		
		Object principal = SecurityUtils.getSubject().getPrincipal();
		// 如果已经登录，则跳转到管理首页
		if (principal != null) {
			if(AjaxUtil.isAjaxRequest((HttpServletRequest) request)){
				JsonMsg jsonMsg = new JsonMsg();
				 jsonMsg.setSuccess(true);
				return jsonMsg;
			}else{
				setMainFrameView(view);
				return view;
			}
			
		}

		Object exception = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (exception instanceof IncorrectCredentialsException || exception instanceof UnknownAccountException) {
			 msg = "用户或密码错误, 请重试！";
		} else if (exception instanceof DisabledAccountException) {
			msg = "账号被禁用！";
		} else if (exception instanceof ExcessiveAttemptsException) {
			msg = "登录失败次数过多！";
		} else if (exception instanceof ExpiredCredentialsException) {
			msg = "凭证过期！";
		} else if (exception instanceof AuthenticationException) {
			msg = "登录密码错误！";
		} else if (exception instanceof UnauthorizedException) {
			msg = "非授权异常！";
		} else {
			msg = "用户或密码错误, 请重试！";
		}

       logger.error(msg);
        view.setViewName("view/homepage/index.jsp");
        model.addAttribute("errMsg", msg);
        return view;
	}

	private List<EasyUITreeNode> getFileTreeListByRoleIds(){
		
		List<String> roleCodes = getRoleCodes();
		List<EasyUITreeNode> list0  = sysCMenuService.findChildByMenuCode("0", roleCodes);
		return list0;
	}
	
	/**
	 * 单点登录的登录方法
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/casLogin", method = RequestMethod.GET)
	public ModelAndView casLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		view.setViewName("view/homepage/index");

		Principal principal = getEmpSession(); // 当前登陆人员信息
		if(principal != null){
			setMainFrameView(view);
			return view;
		} else {
			view.setViewName("view/homepage/index");
			return view;
		}
	}
	
	/**
	 * 单点登录的登录方法
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/userSuccessLogin", method = RequestMethod.GET)
	public ModelAndView userSuccessLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		view.setViewName("view/homepage/index.jsp");

		Principal principal = getEmpSession(); // 当前登陆人员信息
		if(principal != null){
			setMainFrameView(view);
			return view;
		} else {
			view.setViewName("view/homepage/index.jsp");
			return view;
		}
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value="modifyPwd")
	@ResponseBody
	public JsonMsg modifyPwd(SysCUser user,String newPwd,HttpServletRequest request){
		Principal principal = getEmpSession();
		SysCUser model = sysCUserService.findSysCUser(principal.getUserId());
		JsonMsg jsonMsg = new JsonMsg();
		if (model.getPswd().equals(MD5Util.getSaltMd5(user.getPswd(), SystemConstants.PASS_SALT))) {
			sysCUserService.updateUserPswd(model,newPwd);
			jsonMsg.setSuccess(true);
		} else {
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("工号或密码错误!");
		}
		return jsonMsg;
	}
	
	/**
	 * 修改移动端密码
	 * @param username 用户账号
	 * @param oldPW	原密码
	 * @param newPW	新密码
	 * @param request	
	 * @return
	 */
	@RequestMapping(value="modifyAppPwd")
	@ResponseBody
	public JsonMsg modifyAppPwd(String username,String oldPW,String newPW,HttpServletRequest request){
		Principal principal = getEmpSession();
		SysCUser model = sysCUserService.findSysCUser(principal.getUserId());
		JsonMsg jsonMsg = new JsonMsg();
		if (model.getPswd().equals(MD5Util.getSaltMd5(oldPW, SystemConstants.PASS_SALT))) {
			sysCUserService.updateUserPswd(model, newPW);
			jsonMsg.setSuccess(true);
		}
		
		return jsonMsg;
	}
	
	
	@RequestMapping(value="userAppDetail")
	@ResponseBody
	public JsonMsg userAppDetail(String username,String pswd,HttpServletRequest request){
		Principal principal = getEmpSession();
		SysCUser sysCUser = sysCUserService.findSysCUser(principal.getUserId());
		JsonMsg jsonMsg = new JsonMsg();
		if (sysCUser != null) {
			jsonMsg.setData(sysCUser);
			jsonMsg.setSuccess(true);
		}else {
			jsonMsg.setSuccess(false);
		}
		return jsonMsg;
	}
	
	@RequestMapping(value="toFrontMain")
	@ResponseBody
	public ModelAndView toFrontMain( HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		setMainFrameView(view);
		return view;
	}
	
	/**
	 * 根据点击的节点查询子节点
	 * @param menuCode  菜单code
	 * @param request
	 * @return
	 */
	@RequestMapping(value="findChildByMenuCode", method = RequestMethod.POST)
	@ResponseBody
	public List<EasyUITreeNode> findChildByMenuCode(String menuCode, HttpServletRequest request){
		List<String> roleCodes = getRoleCodes();
		List<EasyUITreeNode> list = sysCMenuService.findChildByMenuCode(menuCode, roleCodes);
		return list ;
	}
	
	/**
	 * 设置跳转到MainFrame的ModelAndView
	 * @param view ModelAndView
	 */
	private void setMainFrameView(ModelAndView view) {
		/*List<EasyUITreeNode> fileList = this.getFileTreeListByRoleIds();
		view.addObject("fileList", fileList);*/
		view.setViewName("view/homepage/mainFrame.jsp");
	}
	
}
