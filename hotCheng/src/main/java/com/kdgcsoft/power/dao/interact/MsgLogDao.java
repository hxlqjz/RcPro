package com.kdgcsoft.power.dao.interact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgcsoft.power.entity.business.interact.MsgLog;

public interface MsgLogDao extends JpaRepository<MsgLog, Long>{
	
	public List<MsgLog> findByUserCodeAndType(String userCode,String type);

}
