package com.tensquare.search.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/29
 * com.tensquare.search.pojo
 */
@Document(indexName = "tensquare" ,type = "article")
public class Article implements Serializable {

	/**
	 * id
	 */
	@Id
	private String id;

	/**
	 * 标题
	 */
	@Field(index = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_mord")
	private String title;

	/**
	 * 文章正文
	 */
	@Field(index = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_mord")
	private String content;

	/**
	 * 审核状态
	 */
	private String state;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
