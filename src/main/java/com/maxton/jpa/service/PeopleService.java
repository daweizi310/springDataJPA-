package com.maxton.jpa.service;


import com.maxton.jpa.dao.PeopleDao;
import com.maxton.jpa.pojo.People;
import com.maxton.jpa.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 服务层
 * 
 * @Author maxton.zhang
 *
 */
@Service
@Transactional
public class PeopleService {

	@Autowired
	private PeopleDao peopleDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<People> findAll() {
		return peopleDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<People> findSearch(Map whereMap, int page, int size) {
		Specification<People> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return peopleDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<People> findSearch(Map whereMap) {
		Specification<People> specification = createSpecification(whereMap);
		return peopleDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public People findById(String id) {
		return peopleDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param people
	 */
	public void add(People people) {
		people.setPid( idWorker.nextId()+"" );
		peopleDao.save(people);
	}

	/**
	 * 修改
	 * @param people
	 */
	public void update(People people) {
		peopleDao.save(people);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		peopleDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<People> createSpecification(Map searchMap) {

		return new Specification<People>() {

			@Override
			public Predicate toPredicate(Root<People> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 
                if (searchMap.get("pid")!=null && !"".equals(searchMap.get("pid"))) {
                	predicateList.add(cb.like(root.get("pid").as(String.class), "%"+(String)searchMap.get("pid")+"%"));
                }
                // 
                if (searchMap.get("pname")!=null && !"".equals(searchMap.get("pname"))) {
                	predicateList.add(cb.like(root.get("pname").as(String.class), "%"+(String)searchMap.get("pname")+"%"));
                }
                // 
                if (searchMap.get("sex")!=null && !"".equals(searchMap.get("sex"))) {
                	predicateList.add(cb.like(root.get("sex").as(String.class), "%"+(String)searchMap.get("sex")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
