package com.kdgcsoft.power.dao.fw.base;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kdgcsoft.power.entity.fw.base.ComCAtt;

@Repository
public interface ComCAttDao extends JpaRepository<ComCAtt, Long>,JpaSpecificationExecutor<ComCAtt>{

}
