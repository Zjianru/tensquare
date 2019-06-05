package entity;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/21
 * common - 统一返回对象
 */
public class Result {

	/**
	 * 返回码
	 */
	private Integer code;
	/**
	 * 是否成功
	 */
	private boolean flag;

	/**
	 * 返回信息
	 */
	private String message;
	/**
	 * 返回数据
	 */
	private Object data;


	public Result() {
	}

	public Result( Integer code,boolean flag, String message) {
		this.code = code;
		this.flag = flag;
		this.message = message;
	}

	public Result( Integer code,boolean flag, String message, Object data) {
		this.code = code;
		this.flag = flag;
		this.message = message;
		this.data = data;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
