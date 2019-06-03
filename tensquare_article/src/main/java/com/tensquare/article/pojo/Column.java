package com.tensquare.article.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/27
 * com.tensquare.article.pojo
 */
@Entity
@Table(name="tb_column")
public class Column implements Serializable{
	/**
	 * ID
	 */
	@Id
	private String id;


	/**
	 * 专栏名称
	 */
	private String name;
	/**
	 * 专栏简介
	 */
	private String summary;
	/**
	 * 用户ID
	 */
	private String userid;
	/**
	 *  申请日期
	 */
	private java.util.Date createtime;
	/**
	 * 审核日期
	 */
	private java.util.Date checktime;
	/**
	 * 状态
	 */
	private String state;

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {		
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUserid() {		
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public java.util.Date getCreatetime() {		
		return createtime;
	}
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	public java.util.Date getChecktime() {		
		return checktime;
	}
	public void setChecktime(java.util.Date checktime) {
		this.checktime = checktime;
	}

	public String getState() {		
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


	
}
