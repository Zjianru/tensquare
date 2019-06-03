package com.tensquare.spit.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/28
 * com.tensquare.spit.pojo
 */
public class Spit implements Serializable {
	/**
	 * id
	 */
	@Id
	private String _id;
	/**
	 * content
	 */
	private String content;
	/**
	 * 发布时间
	 */
	private Date publishtime;
	/**
	 * 用户 id
	 */
	private String userid;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 浏览
	 */
	private Integer visits;
	/**
	 * 点赞
	 */
	private Integer thumbup;
	/**
	 * 分享
	 */
	private Integer share;
	/**
	 * 备注
	 */
	private Integer comment;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 上层 id
	 */
	private String parentid;


	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
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

	public Integer getVisits() {
		return visits;
	}

	public void setVisits(Integer visits) {
		this.visits = visits;
	}

	public Integer getThumbup() {
		return thumbup;
	}

	public void setThumbup(Integer thumbup) {
		this.thumbup = thumbup;
	}

	public Integer getShare() {
		return share;
	}

	public void setShare(Integer share) {
		this.share = share;
	}

	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
}
