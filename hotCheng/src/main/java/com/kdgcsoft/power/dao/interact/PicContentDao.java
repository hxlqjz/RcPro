package com.kdgcsoft.power.dao.interact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgcsoft.power.entity.business.interact.PicContent;

public interface PicContentDao extends JpaRepository<PicContent, Long>{

	public List<PicContent> findByTableNameAndTabKey(String tableName,String tabKey);
}
