package com.maxton.jpa.service;

import com.maxton.jpa.dao.ArticleDao;
import com.maxton.jpa.pojo.Article;
import com.maxton.jpa.pojo.People;
import com.maxton.jpa.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 服务层
 *
 * @Author maxton.zhang
 */
@Service
@Transactional
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<Article> findAll() {
        return articleDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param article
     * @param page
     * @param size
     * @return
     */
    public Page<Article> findSearch(Article article, int page, int size) {
        Specification<Article> specification = createSpecification(article);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return articleDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param article
     * @return
     */
    public List<Article> findSearch(Article article) {
        Specification<Article> specification = createSpecification(article);
        return articleDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public Article findById(String id) {
        return articleDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param article
     */
    public void add(Article article) {
        article.setAid(idWorker.nextId() + "");
        articleDao.save(article);
    }

    /**
     * 修改
     *
     * @param article
     */
    public void update(Article article) {
        articleDao.save(article);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        articleDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param article
     * @return
     */
    private Specification<Article> createSpecification(Article article) {

        return new Specification<Article>() {

            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                Join<Article, People> join = root.join("people", JoinType.LEFT);
                if (null != article.getPeople() && null != article.getPeople().getPname()) {
                    predicateList.add(cb.like(join.get("pname").as(String.class), "%" + article.getPeople().getPname() + "%"));
                }
                if (null != article.getPeople() && null != article.getPeople().getSex()) {
                    predicateList.add(cb.like(join.get("sex").as(String.class), "%" + article.getPeople().getSex() + "%"));
                }
                //
                if (article.getAid() != null && !"".equals(article.getAid())) {
                    predicateList.add(cb.like(root.get("aid").as(String.class), "%" + (String) article.getAid() + "%"));
                }
                // 
                if (article.getTitle() != null && !"".equals(article.getTitle())) {
                    predicateList.add(cb.like(root.get("title").as(String.class), "%" + (String) article.getTitle() + "%"));
                }
                // 
                if (article.getContent() != null && !"".equals(article.getContent())) {
                    predicateList.add(cb.like(root.get("content").as(String.class), "%" + (String) article.getContent() + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
