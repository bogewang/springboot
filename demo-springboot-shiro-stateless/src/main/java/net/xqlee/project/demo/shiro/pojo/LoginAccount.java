package net.xqlee.project.demo.shiro.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户信息
 * 
 * @author xqlee
 *
 */
public class LoginAccount implements Serializable {
	/** 用户名 */
	String loginName;
	List<String> roles;// 测试用
	List<String> permissions;// 测试用直接放用户登录对象里面
	/** 用户密码 **/
	String password;
	boolean enabled;
	Date createDate;
	boolean isExpired;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

}
