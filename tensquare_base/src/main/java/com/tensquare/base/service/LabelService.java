package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/21
 * com.tensquare.base.service
 */
@Service
@Transactional
public class LabelService {

	@Autowired
	private LabelDao labelDao;

	@Autowired
	private IdWorker idWorker;


	/**
	 * 增加标签
	 *
	 * @param label Label 标签对象
	 */
	public void save(Label label) {
		label.setId(idWorker.nextId() + "");
		labelDao.save(label);
	}

	/**
	 * 标签全部列表
	 *
	 * @return List<Label>
	 */
	public List<Label> findAll() {
		return labelDao.findAll();
	}


	/**
	 * 根据 ID 查询标签
	 *
	 * @param id 标签ID
	 * @return Label 对象
	 */
	public Label findById(String id) {
		return labelDao.findById(id).get();
	}


	/**
	 * 更新标签
	 *
	 * @param label 标签ID
	 */
	public void updateById(Label label) {
		labelDao.save(label);
	}

	/**
	 * 根据 ID 删除标签
	 *
	 * @param id 标签ID
	 */
	public void deleteById(String id) {
		labelDao.deleteById(id);
	}


	/**
	 * 标签分页
	 *
	 * @param label 标签信息
	 * @return 标签 List
	 */
	public List<Label> findSearch(Label label) {
		return labelDao.findAll(toSpecificationObject(label));
	}

	/**
	 * 标签分页
	 *
	 * @param label 标签信息
	 * @param page  分页
	 * @param size  页内数量
	 * @return Page<Label> 对象
	 */
	public Page<Label> pageQuery(Label label, Integer page, Integer size) {
		return labelDao.findAll(toSpecificationObject(label), PageRequest.of(page - 1, size));
	}

	/**
	 * 内部对象方法
	 * @param label Label 对象
	 * @return Specification<Label>对象，满足本类方法调用
	 */
	private Specification<Label> toSpecificationObject(Label label) {

		return new Specification<Label>() {
			/**
			 *
			 * @param root 根对象 也就是要把条件封装到那个对象中  where 类名 = label.getId
			 * @param criteriaQuery 封装的查询关键字 比如 group by  order by
			 * @param criteriaBuilder 用来封装条件对象，如果直接返回 null ，表示不需要任何条件
			 * @return Predicate
			 */
			@Override
			public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();
				if (label.getId() != null && !"".equals(label.getId())) {
					Predicate predicate = criteriaBuilder.like(root.get("id").as(String.class), label.getId());
					list.add(predicate);
				}
				if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
					Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
					list.add(predicate);
				}
				if (label.getState() != null && !"".equals(label.getState())) {
					Predicate predicate = criteriaBuilder.like(root.get("state").as(String.class), label.getState());
					list.add(predicate);
				}
				if (label.getCount() != null && !"".equals(label.getCount())) {
					Predicate predicate = criteriaBuilder.like(root.get("count").as(String.class), label.getCount());
					list.add(predicate);
				}
				if (label.getRecommend() != null && !"".equals(label.getRecommend())) {
					Predicate predicate = criteriaBuilder.like(root.get("recommend").as(String.class), label.getRecommend());
					list.add(predicate);
				}
				Predicate[] persistence = new Predicate[list.size()];
				list.toArray(persistence);
				return criteriaBuilder.and(persistence);
			}
		};
	}
}