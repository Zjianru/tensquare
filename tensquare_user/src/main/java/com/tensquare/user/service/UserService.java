package com.tensquare.user.service;

import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import entity.TokenCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import com.tensquare.user.dao.UserDao;
import com.tensquare.user.pojo.User;
import util.JwtUtil;

/**
 * 服务层
 *
 * @author Administrator
 */
@Transactional
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private IdWorker idWorker;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private HttpServletRequest httpServletRequest;
	/**
	 * 查询全部列表
	 *
	 * @return
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}


	/**
	 * 条件查询+分页
	 *
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<User> findSearch(Map whereMap, int page, int size) {
		Specification<User> specification = createSpecification(whereMap);
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return userDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 *
	 * @param whereMap
	 * @return
	 */
	public List<User> findSearch(Map whereMap) {
		Specification<User> specification = createSpecification(whereMap);
		return userDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 *
	 * @param id
	 * @return
	 */
	public User findById(String id) {
		return userDao.findById(id).get();
	}

	/**
	 * 增加
	 *
	 * @param user user
	 */
	public void add(User user) {
		user.setId(idWorker.nextId() + "");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setFollowcount(0);
		user.setFanscount(0);
		user.setOnline(0L);
		user.setRegdate(new Date());
		user.setUpdatedate(new Date());
		user.setLastdate(new Date());
		userDao.save(user);
	}

	/**
	 * 修改
	 *
	 * @param user
	 */
	public void update(User user) {
		userDao.save(user);
	}

	/**
	 * 删除
	 *
	 * @param id
	 */
	public void deleteById(String id) {
		String token = (String) httpServletRequest.getAttribute(TokenCode.CLAIMS_ROLE_ADMIN);
		if (token == null || "".equals(token)){
			throw new RuntimeException("权限不足");
		}
		userDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 *
	 * @param searchMap searchMap
	 * @return Specification<User>
	 */
	private Specification<User> createSpecification(Map searchMap) {

		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				// ID
				if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
					predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
				}
				// 手机号码
				if (searchMap.get("mobile") != null && !"".equals(searchMap.get("mobile"))) {
					predicateList.add(cb.like(root.get("mobile").as(String.class), "%" + (String) searchMap.get("mobile") + "%"));
				}
				// 密码
				if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
					predicateList.add(cb.like(root.get("password").as(String.class), "%" + (String) searchMap.get("password") + "%"));
				}
				// 昵称
				if (searchMap.get("nickname") != null && !"".equals(searchMap.get("nickname"))) {
					predicateList.add(cb.like(root.get("nickname").as(String.class), "%" + (String) searchMap.get("nickname") + "%"));
				}
				// 性别
				if (searchMap.get("sex") != null && !"".equals(searchMap.get("sex"))) {
					predicateList.add(cb.like(root.get("sex").as(String.class), "%" + (String) searchMap.get("sex") + "%"));
				}
				// 头像
				if (searchMap.get("avatar") != null && !"".equals(searchMap.get("avatar"))) {
					predicateList.add(cb.like(root.get("avatar").as(String.class), "%" + (String) searchMap.get("avatar") + "%"));
				}
				// E-Mail
				if (searchMap.get("email") != null && !"".equals(searchMap.get("email"))) {
					predicateList.add(cb.like(root.get("email").as(String.class), "%" + (String) searchMap.get("email") + "%"));
				}
				// 兴趣
				if (searchMap.get("interest") != null && !"".equals(searchMap.get("interest"))) {
					predicateList.add(cb.like(root.get("interest").as(String.class), "%" + (String) searchMap.get("interest") + "%"));
				}
				// 个性
				if (searchMap.get("personality") != null && !"".equals(searchMap.get("personality"))) {
					predicateList.add(cb.like(root.get("personality").as(String.class), "%" + (String) searchMap.get("personality") + "%"));
				}

				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

	/**
	 * 验证码短信发送
	 * @param mobile 手机
	 */
	public void sendSms(String mobile) {
		String checkCode = RandomStringUtils.randomNumeric(6);
		redisTemplate.opsForValue().set("_checkcode"+mobile, checkCode, 60, TimeUnit.SECONDS);
		Map<String, String> info = new HashMap<>();
		info.put("mobile", mobile);
		info.put("checkCode", checkCode);
		rabbitTemplate.convertAndSend("sms", info);
		System.out.println("-----------: " + "_checkcode"+mobile +" ---- "+checkCode);
	}

	/**
	 * 登录
	 * @param user user info
	 * @return User
	 */
	public User login(User user) {
		User result = userDao.findByMobile(user.getMobile());
		if (result != null && bCryptPasswordEncoder.matches(user.getPassword(),result.getPassword())){
			return result;
		}
		return null;
	}

	/**
	 * 更新粉丝数和关注数
	 * @param x 主梁
	 * @param userid userid
	 * @param friendid friendid
	 */
	public void updateFanscountAndFollowcount(int x, String userid, String friendid) {
		userDao.updatefanscount(x,friendid);
		userDao.updatefollowcount(x,userid);
	}
}
