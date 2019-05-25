package com.tensquare.base.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/21
 * com.tensquare.base.pojo
 */
@Entity
@Table(name = "tb_label")
public class Label implements Serializable {
	/**
	 * 标签 ID
	 */
	@Id
	private String id;
	/**
	 * 标签名称
	 */
	private String labelname;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 使用数量
	 */
	private String count;
	/**
	 * 是否推荐
	 */
	private String recommend;
	/**
	 * 粉丝数
	 */
	private String fans;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabelname() {
		return labelname;
	}

	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getFans() {
		return fans;
	}

	public void setFans(String fans) {
		this.fans = fans;
	}
}