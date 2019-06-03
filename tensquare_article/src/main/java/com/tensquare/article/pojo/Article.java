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
@Table(name="tb_article")
public class Article implements Serializable{
	/**
	 * id
	 */
	@Id
	private String id;
	/**
	 * 专栏ID
	 */
	private String columnid;
	/**
	 * 用户ID
	 */
	private String userid;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 文章正文
	 */
	private String content;
	/**
	 * 文章封面
	 */
	private String image;
	/**
	 * 发表日期
	 */
	private java.util.Date createtime;
	/**
	 * 修改日期
	 */
	private java.util.Date updatetime;
	/**
	 * 是否公开
	 */
	private String ispublic;
	/**
	 * 是否置顶
	 */
	private String istop;
	/**
	 * 浏览量
	 */
	private Integer visits;
	/**
	 * 点赞数
	 */
	private Integer thumbup;
	/**
	 * 评论数
	 */
	private Integer comment;
	/**
	 * 审核状态
	 */
	private String state;
	/**
	 * 所属频道
	 */
	private String channelid;
	/**
	 * URL
	 */
	private String url;
	/**
	 * 类型
	 */
	private String type;

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getColumnid() {		
		return columnid;
	}
	public void setColumnid(String columnid) {
		this.columnid = columnid;
	}

	public String getUserid() {		
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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

	public String getImage() {		
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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

	public String getIspublic() {		
		return ispublic;
	}
	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}

	public String getIstop() {		
		return istop;
	}
	public void setIstop(String istop) {
		this.istop = istop;
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

	public String getChannelid() {		
		return channelid;
	}
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getUrl() {		
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {		
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	
}
