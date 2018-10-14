package com.kdgcsoft.power.common.security.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import com.kdgcsoft.power.common.security.shiro.FwAuthenticationToken.ModeEnum;

public class FwCredentialsMatcher extends HashedCredentialsMatcher {

	public FwCredentialsMatcher() {
	}
	
	public FwCredentialsMatcher(String hashAlgorithmName) {
       super(hashAlgorithmName);
    }
	
	@Override  
	public boolean doCredentialsMatch(AuthenticationToken authcToken,AuthenticationInfo info) {
		FwAuthenticationToken token =(FwAuthenticationToken)authcToken;
		if(ModeEnum.agent.equals(token.getLoginMode())){
		   return true; //网关方式不用验证密码
		}else{
		   return super.doCredentialsMatch(authcToken, info);
		}
	}

}
