package net.xqlee.project.demo.shiro.pojo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;


/**
 * 
 * <pre>
 * [Summary]
 * 通用json数据接口返回口径
 * [Detail]
 * TODO
 * [Author]
 * XQLEE
 * [Version]
 * v1.0
 * 2017年3月27日下午6:32:12
 * </pre>
 */
public final class Result<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Result() {
	}

	private Result(Status status, T data, String message) {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public static <T> Result<T> success(T data) {
		return new Result(Status.Success, data, "");
	}

	public static  <T> Result<T> fail(String message) {
		return new Result(Status.Fail, "", message);
	}

	public static <T> Result<T>  noLogin() {
		return new Result(Status.noLogin, "", "用户未登录");
	}
	public static <T> Result<T>  noPermission() {
		return new Result(Status.noPermission, "", "用户权限不足。");
	}
	
	private String id;
	/** 数据内容 **/
	private T data;
	/** 调用接口状态 **/
	private Status status;
	/** 消息,如错误消息 ***/
	private String message;
	/** 接口调用花费时间（单位:秒） **/
	private BigDecimal cost;
	/*** 接口响应时间 ***/
	private String time;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the cost
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		try {
			return JSON.toJSONString(this);
		} catch (Exception e) {
//			Log.error(e.getMessage(), e, this.getClass());
			return JSON.toJSONString(Result.fail("数据处理异常"));
		}
	}

	enum Status {
		Success, Fail,noLogin,noPermission
	}
}
