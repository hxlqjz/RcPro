package com.kdgcsoft.power.service.fw.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kdgcsoft.power.common.bean.Principal;
import com.kdgcsoft.power.common.support.Monitor;
import com.kdgcsoft.power.dao.fw.system.HrCDeptDao;
import com.kdgcsoft.power.dao.fw.system.SysCRoleDao;
import com.kdgcsoft.power.dao.fw.system.SysCRolePermDao;
import com.kdgcsoft.power.dao.fw.system.SysCUserDao;
import com.kdgcsoft.power.dao.fw.system.SysCUserDeptDao;
import com.kdgcsoft.power.dao.fw.system.SysCUserRoleDao;
import com.kdgcsoft.power.entity.fw.system.HrCDept;
import com.kdgcsoft.power.entity.fw.system.SysCRole;
import com.kdgcsoft.power.entity.fw.system.SysCRolePerm;
import com.kdgcsoft.power.entity.fw.system.SysCUser;
import com.kdgcsoft.power.entity.fw.system.SysCUserDept;
import com.kdgcsoft.power.entity.fw.system.SysCUserRole;

public class ShiroCasRealm extends CasRealm {

	private static Logger logger = LoggerFactory.getLogger(ShiroCasRealm.class);
	@Autowired
    private SysCUserDao sysCUserDao;
	@Autowired
    private SysCRoleDao sysCRoleDao;
	@Autowired
    private SysCUserDeptDao sysCUserDeptDao;
	@Autowired
    private HrCDeptDao hrCDeptDao;
	@Autowired
    private SysCUserRoleDao sysCUserRoleDao;
	@Autowired
    private SysCRolePermDao sysCRolePermDao;


	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		CasToken casToken = (CasToken) token;
		if (token == null) {
			return null;
		}

		String ticket = (String) casToken.getCredentials();
		if (!StringUtils.hasText(ticket)) {
			return null;
		}
		TicketValidator ticketValidator = ensureTicketValidator();
		try {
			// contact CAS server to validate service ticket
			Assertion casAssertion = ticketValidator.validate(ticket,getCasService());
			// get principal, user id and attributes
			AttributePrincipal casPrincipal = casAssertion.getPrincipal();
			String userId = casPrincipal.getName();
			logger.info(
					"Validate ticket : {} in CAS server : {} to retrieve user : {}",
					new Object[] { ticket, getCasServerUrlPrefix(), userId });

			Map<String, Object> attributes = casPrincipal.getAttributes();
			// refresh authentication token (user id + remember me)
			casToken.setUserId(userId);
			String rememberMeAttributeName = getRememberMeAttributeName();
			String rememberMeStringValue = (String) attributes.get(rememberMeAttributeName);
			boolean isRemembered = rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue);
			if (isRemembered) {
				casToken.setRememberMe(true);
			}
			// create simple authentication info
			// List<Object> principals = CollectionUtils.asList(userId,
			// attributes);
			// PrincipalCollection principalCollection = new
			// SimplePrincipalCollection(principals, getName());
//			PortalWEmp user = empService.getEmpByLoginCode(userId);
			Map<String, String> mapParam = new HashMap<String, String>();
			mapParam.put("loginCode", userId);
			/*PortalWEmp user = (PortalWEmp) HttpPostUrl
					.getBeanFromUap("getEmpByLoginCode", mapParam,
							PortalWEmp.class);*/
			 SysCUser user = null;
			 String UserCode=userId;
			 if(!sysCUserDao.findByUserCode(UserCode).isEmpty()) {
			        //调用userdao根据用户名查询用户
			            user = sysCUserDao.findByUserCode(UserCode).get(0);
			        }
			
			if (user != null) {
				Principal loginUser = new Principal();
				loginUser.setUserId(user.getUserId());
				loginUser.setPswd(user.getPswd());
				loginUser.setUserCode(user.getUserCode());
				loginUser.setUserName(user.getUserName());
				loginUser.setOrgCode(user.getDeptCode());
				loginUser.setOrgName(user.getDeptName());
//				setLoginUser(user,attributes);
				List<HrCDept> deptList = new ArrayList<HrCDept>();
	        	List<SysCUserDept> userDeptList = sysCUserDeptDao.findByUserCode(user.getUserCode());
	        	if(!userDeptList.isEmpty()){
	        		for(SysCUserDept userDept:userDeptList){
	        			deptList.addAll(hrCDeptDao.findByDeptCode(userDept.getDeptCode()));
//	        			if(userDept.getOrgCode().equals(userDept.getDeptCode())){
//	        				principal.setOrgCode(userDept.getOrgCode());
//	        			}
	        		}
	        	}
	        	loginUser.setDeptList(deptList);
	        	List<SysCUserRole> userRoleList = sysCUserRoleDao.findByUserCode(user.getUserCode());
	        	List<SysCRole> roleList = new ArrayList<SysCRole>();
	        	if(!userRoleList.isEmpty()){
	        		for(SysCUserRole userRole:userRoleList){
	        			roleList.addAll(sysCRoleDao.findByRoleCode(userRole.getRoleCode()));
	        		}
	        	}
	        	loginUser.setRoleList(roleList);
				PrincipalCollection principalCollection = new SimplePrincipalCollection(
						loginUser, getName());
				return new SimpleAuthenticationInfo(principalCollection, ticket);
			} else {
				return null;
			}
		} catch (TicketValidationException e) {
			throw new CasAuthenticationException("Unable to validate ticket ["+ ticket + "]", e);
		}
	}

	/**
	 * Retrieves the AuthorizationInfo for the given principals (the CAS
	 * previously authenticated user : id + attributes).
	 * 
	 * @param principals
	 *            the primary identifying principals of the AuthorizationInfo
	 *            that should be retrieved.
	 * @return the AuthorizationInfo associated with this principals.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection paramPrincipalCollection) {
		  //创建授权信息对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //根据当前用户查询数据库,获取其权限对象
        //获取当前用户，由于shiro的过滤器在struts的过滤器之前执行，因此此时的用户还没有被封装到session中，
        //故获取当前用户在session中娶不到
        Principal  loginUser =  (Principal) paramPrincipalCollection.getPrimaryPrincipal();
        if (loginUser != null) {
            List<SysCRolePerm> list = null;
            //表示用户存在，调用roledao根据用户查询所有权限
            if ("admin".equals(loginUser.getUserCode())) {
                //如果是超级管理员，赋予所有权限
                list = sysCRolePermDao.findAll();
                if (list != null && list.size() > 0) {
                    for (SysCRolePerm rolePerm : list) {
                        simpleAuthorizationInfo.addStringPermission(rolePerm.getPermCode());
                    }
                }
            } else {
            	List<SysCUserRole> roleList = sysCUserRoleDao.findByUserCode(loginUser.getUserCode());
            	for(SysCUserRole role:roleList){
            		//TODO 此处以后需要优化
            		list = sysCRolePermDao.findByRoleCode(role.getRoleCode());
            	     if (list != null && list.size() > 0) {
                         for (SysCRolePerm rolePerm : list) {
   //为用户授权
                             simpleAuthorizationInfo.addStringPermission(rolePerm.getPermCode());
                         }
                     }
            	}
                //根据用户id查询所有权限
            }
       
            return simpleAuthorizationInfo;
        } else {
            return null;
        }
		
	}
	
//	private void setLoginUser(PortalWEmp portalWEmp,Map<String, Object> attributes){
//		if (null != portalWEmp) {
//			Principal loginUser = new Principal();
//			loginUser.setUserCode(portalWEmp.getLogincode());
//			loginUser.setUserName(portalWEmp.getHrname());
//			loginUser.setOrgCode(portalWEmp.getOrgCode());
//			loginUser.setOrgName(portalWEmp.getOrgName());
//			//设置cas登录返回的人员对应业务系统的账号信息,以业务系统的编码为键值
////			loginUser.setBusimap(attributes);
//			setSession("userContext",loginUser);
//			
//		}
//	}
	 /**
     * ShiroSession设置
     * 
     * @see 使用时直接用HttpSession.getAttribute(key)就可以取到
     */
	@Monitor(actionName = "setSession", actionDescr = "单点登录")
    private void setSession(String key, Principal loginUser) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
//        	HttpServletRequest request =(HttpServletRequest)((WebSubject)SecurityUtils.getSubject()).getServletRequest();
        	
//            if(null != request){
//            	// 兼职人员可以以不同组织机构身份登录
//            	String loginAs = request.getParameter("login_as");
//            	if (StringUtil.isEmpty(loginAs)) {
//					loginUser.setOrgCode("default");
//				} else {
//					loginUser.setOrgCode(loginAs);
//				}
//            }
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, loginUser);
            }
        }
        
    }

}
