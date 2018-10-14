package com.kdgcsoft.power.service.fw.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.kdgcsoft.power.common.bean.Principal;
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


 public class ShiroDbRealm extends AuthorizingRealm {
	@Autowired
    private SysCUserDao sysCUserDao;
	@Autowired
    private SysCRolePermDao sysCRolePermDao;
	@Autowired
    private SysCUserRoleDao sysCUserRoleDao;
	@Autowired
    private SysCUserDeptDao sysCUserDeptDao;
	@Autowired
    private HrCDeptDao hrCDeptDao;
	@Autowired
    private SysCRoleDao sysCRoleDao;
	
	

    //权限认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken paramAuthenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) paramAuthenticationToken;
        String username = token.getUsername();
        SysCUser user = null;
        if(!sysCUserDao.findByUserCode(username).isEmpty()) {
        //调用userdao根据用户名查询用户
            user = sysCUserDao.findByUserCode(username).get(0);
        }
        if (user != null) {
        	Principal principal= new Principal();
        	principal.setUserId(user.getUserId());
        	principal.setUserCode(user.getUserCode());
        	principal.setUserName(user.getUserName());
        	principal.setPswd(user.getPswd());
        	List<HrCDept> deptList = new ArrayList<HrCDept>();
        	List<SysCUserDept> userDeptList = sysCUserDeptDao.findByUserCode(user.getUserCode());
        	if(!userDeptList.isEmpty()){
        		for(SysCUserDept userDept:userDeptList){
        			deptList.addAll(hrCDeptDao.findByDeptCode(userDept.getDeptCode()));
//        			if(userDept.getOrgCode().equals(userDept.getDeptCode())){
//        				principal.setOrgCode(userDept.getOrgCode());
//        			}
        		}
        	}
        	principal.setDeptList(deptList);
        	List<SysCUserRole> userRoleList = sysCUserRoleDao.findByUserCode(user.getUserCode());
        	List<SysCRole> roleList = new ArrayList<SysCRole>();
        	if(!userRoleList.isEmpty()){
        		for(SysCUserRole userRole:userRoleList){
        			roleList.addAll(sysCRoleDao.findByRoleCode(userRole.getRoleCode()));
        		}
        	}
        	principal.setRoleList(roleList);
            //如果查询的用户存在
            // 与数据库中用户名和密码进行比对。比对成功则返回info，比对失败则抛出对应信息的异常AuthenticationException
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, 
            		principal.getPswd(), this.getClass().getName());
            return authenticationInfo;
        } else {
            //如果查询不到用户, 返回空, Shiro会抛出UnknownAccountException异常
            return null;
        }
    }
    
  //授予权限
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

}