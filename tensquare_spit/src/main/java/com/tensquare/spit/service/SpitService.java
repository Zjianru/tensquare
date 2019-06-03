package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/28
 * com.tensquare.spit.service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpitService {
	@Autowired
	private SpitDao spitDao;
	@Autowired
	private IdWorker idWorker;
	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 查找全部
	 *
	 * @return List<Spit>
	 */
	public List<Spit> findAll() {
		return spitDao.findAll();
	}

	/**
	 * 根据 id 查找
	 *
	 * @param id id
	 * @return Spit 对象
	 */
	public Spit findById(String id) {
		return spitDao.findById(id).get();
	}

	/**
	 * 添加  保存
	 *
	 * @param spit spit 对象
	 */
	public void save(Spit spit) {
		spit.set_id(idWorker.nextId() + "");
		spit.setPublishtime(new Date());
		spit.setVisits(0);
		spit.setThumbup(0);
		spit.setShare(0);
		spit.setComment(0);
		spit.setState("1");
		if (spit.getParentid() != null && !"".equals(spit.getParentid())) {
			mongoTemplate.updateFirst(new Query().addCriteria(Criteria.where("_id").is(spit.getParentid())),
					new Update().inc("comment", 1), "spit");
		}
		spitDao.save(spit);
	}

	/**
	 * 更新
	 *
	 * @param spit spit 对象
	 */
	public void update(Spit spit) {
		spitDao.save(spit);
	}

	/**
	 * 根据 id 删除
	 *
	 * @param id id
	 */
	public void deleteById(String id) {
		spitDao.deleteById(id);
	}

	/**
	 * 根据上级 id 查询
	 *
	 * @param parentId 上级 id
	 * @param page     分页
	 * @param size     页内容量
	 * @return Page<Spit>
	 */
	public Page<Spit> findByParentid(String parentId, int page, int size) {
		return spitDao.findByParentid(parentId, PageRequest.of(page - 1, size));
	}

	/**
	 * 点赞
	 *
	 * @param id id
	 */
	public void thumbup(String id) {
		mongoTemplate.updateFirst(new Query().addCriteria(Criteria.where("_id").is(id)),
				new Update().inc("thumbup", 1), "spit");
	}


}