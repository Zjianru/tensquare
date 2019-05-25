package com.tensquare.recruit.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/25
 * com.tensquare.recruit.pojo
 */
@Entity
@Table(name = "tb_enterprise")
public class Enterprise implements Serializable {

	/**
	 * id
	 */
	@Id
	private String id;


	/**
	 * 企业名称
	 */
	private String name;
	/**
	 * 企业简介
	 */
	private String summary;
	/**
	 * 企业地址
	 */
	private String address;
	/**
	 * 标签列表
	 */
	private String labels;
	/**
	 * 坐标
	 */
	private String coordinate;
	/**
	 * 是否热门
	 */
	private String ishot;
	/**
	 * LOGO
	 */
	private String logo;
	/**
	 * 职位数
	 */
	private Integer jobcount;
	/**
	 * URL
	 */
	private String url;


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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public String getIshot() {
		return ishot;
	}

	public void setIshot(String ishot) {
		this.ishot = ishot;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getJobcount() {
		return jobcount;
	}

	public void setJobcount(Integer jobcount) {
		this.jobcount = jobcount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}