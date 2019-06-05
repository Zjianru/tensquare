package entity;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/21
 * common - 统一返回对象 - 分页
 */
public class PageResult <T> {
	/**
	 * 分页数据
	 */
	private Long total;

	/**
	 * 分页美容
	 */
	private List<T> rows;


	public PageResult() {
	}

	public PageResult(Long total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
