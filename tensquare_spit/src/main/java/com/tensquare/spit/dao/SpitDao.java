package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/28
 * com.tensquare.spit.dao
 */
public interface SpitDao extends MongoRepository<Spit,String> {
	/**
	 * 根据上级 id 进行查询
	 * @param parentid 上级 id
	 * @param pageable 分页
	 * @return Page<Spit>
	 */
	Page<Spit> findByParentid(String parentid, Pageable pageable);

}