package com.kdgcsoft.power.dao.interact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgcsoft.power.entity.business.interact.ConfigPageMain;
import com.kdgcsoft.power.entity.business.interact.PicContent;

public interface ConfigPageMainDao extends JpaRepository<ConfigPageMain, Long>{
	
	public List<ConfigPageMain> findByPageSuperior(String pageSuperior);

}
