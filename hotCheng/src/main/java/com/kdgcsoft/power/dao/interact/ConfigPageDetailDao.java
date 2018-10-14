package com.kdgcsoft.power.dao.interact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgcsoft.power.entity.business.interact.ConfigPageDetail;

public interface ConfigPageDetailDao extends JpaRepository<ConfigPageDetail, Long>{
	
	List<ConfigPageDetail> findByPageId(Long pageId);

}
