package com.kdgcsoft.power.common.bean;
import java.io.Serializable;
import java.util.List;

import com.kdgcsoft.power.entity.fw.system.HrCDept;
import com.kdgcsoft.power.entity.fw.system.SysCRole;
/**
 * 当前登录人信息
 *
 */
public class Principal  implements Serializable {
	private static final long serialVersionUID = -2800706812806810300L;
	private Long userId;//用户id
	private String userCode;//用户登录账号
	private String userName;//用户名称
	private String pswd;//用户密码
	private String orgCode;//企业编码
	private String orgName;//企业名称
	private List<HrCDept> deptList;//所属部门列表
	private List<SysCRole> roleList;//所属角色列表
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public List<HrCDept> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<HrCDept> deptList) {
		this.deptList = deptList;
	}
	public List<SysCRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<SysCRole> roleList) {
		this.roleList = roleList;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	
}