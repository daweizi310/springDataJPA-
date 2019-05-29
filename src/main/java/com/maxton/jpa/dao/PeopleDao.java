package com.maxton.jpa.dao;

import com.maxton.jpa.pojo.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @Author maxton.zhang
 *
 */
public interface PeopleDao extends JpaRepository<People,String>, JpaSpecificationExecutor<People> {
	
}
