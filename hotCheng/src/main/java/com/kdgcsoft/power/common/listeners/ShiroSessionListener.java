package com.kdgcsoft.power.common.listeners;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;


public class ShiroSessionListener implements SessionListener {
	
	@Override
	public void onStart(Session session) {	
	}

	@Override
	public void onStop(Session session) {
		sessionDestroyed(session);
	}

	@Override
	public void onExpiration(Session session) {
		sessionDestroyed(session);
	}

	
	private void sessionDestroyed(Session session) {}
}
