package entity;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/21
 * entity - 状态码
 */
public class   StatusCode {
	/**
	 * 成功
	 */
	public static final int OK = 20000;
	/**
	 * 失败
	 */
	public static final int ERROR = 20001;
	/**
	 * 登录失败
	 */
	public static final int LOGINERROR = 20002;
	/**
	 * 权限不足
	 */
	public static final int ACCESSERROR = 20003;
	/**
	 * 远程调用失败
	 */
	public static final int REMOTEERROR = 20004;
	/**
	 * 重复操作
	 */
	public static final int REPERROR = 20005;
}
