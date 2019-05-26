package com.tensquare.qa.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/26
 * com.tensquare.qa.pojo
 */
@Entity
@Table(name = "tb_problem")
public class Problem implements Serializable {
	/**
	 * id
	 */
	@Id
	private String id;

	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 创建日期
	 */
	private java.util.Date createtime;
	/**
	 * 修改日期
	 */
	private java.util.Date updatetime;
	/**
	 * 用户ID
	 */
	private String userid;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 浏览量
	 */
	private Long visits;
	/**
	 * 点赞数
	 */
	private Long thumbup;
	/**
	 * 回复数
	 */
	private Long reply;
	/**
	 * 是否解决
	 */
	private String solve;
	/**
	 * 回复人昵称
	 */
	private String replyname;
	/**
	 * 回复日期
	 */
	private java.util.Date replytime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	public java.util.Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Long getVisits() {
		return visits;
	}

	public void setVisits(Long visits) {
		this.visits = visits;
	}

	public Long getThumbup() {
		return thumbup;
	}

	public void setThumbup(Long thumbup) {
		this.thumbup = thumbup;
	}

	public Long getReply() {
		return reply;
	}

	public void setReply(Long reply) {
		this.reply = reply;
	}

	public String getSolve() {
		return solve;
	}

	public void setSolve(String solve) {
		this.solve = solve;
	}

	public String getReplyname() {
		return replyname;
	}

	public void setReplyname(String replyname) {
		this.replyname = replyname;
	}

	public java.util.Date getReplytime() {
		return replytime;
	}

	public void setReplytime(java.util.Date replytime) {
		this.replytime = replytime;
	}


}
