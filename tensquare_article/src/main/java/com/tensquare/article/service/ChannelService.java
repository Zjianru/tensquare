package com.tensquare.article.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import util.IdWorker;

import com.tensquare.article.dao.ChannelDao;
import com.tensquare.article.pojo.Channel;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/27
 * com.tensquare.article.service
 */
@Service
public class ChannelService {

	@Autowired
	private ChannelDao channelDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return List<Channel>
	 */
	public List<Channel> findAll() {
		return channelDao.findAll();
	}


	/**
	 * 条件查询+分页
	 *
	 * @param whereMap 条件 map
	 * @param page     分页
	 * @param size     页内容量
	 * @return Page<Channel>
	 */
	public Page<Channel> findSearch(Map whereMap, int page, int size) {
		Specification<Channel> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return channelDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap 条件 map
	 * @return List<Channel>
	 */
	public List<Channel> findSearch(Map whereMap) {
		Specification<Channel> specification = createSpecification(whereMap);
		return channelDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id id
	 * @return Channel
	 */
	public Channel findById(String id) {
		return channelDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param channel Channel
	 */
	public void add(Channel channel) {
		channel.setId( idWorker.nextId()+"" );
		channelDao.save(channel);
	}

	/**
	 * 修改
	 * @param channel Channel
	 */
	public void update(Channel channel) {
		channelDao.save(channel);
	}

	/**
	 * 删除
	 * @param id id
	 */
	public void deleteById(String id) {
		channelDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap 条件 map
	 * @return Specification<Channel>
	 */
	private Specification<Channel> createSpecification(Map searchMap) {

		return new Specification<Channel>() {

			@Override
			public Predicate toPredicate(Root<Channel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 频道名称
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // 状态
                if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))) {
                	predicateList.add(cb.like(root.get("state").as(String.class), "%"+(String)searchMap.get("state")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
