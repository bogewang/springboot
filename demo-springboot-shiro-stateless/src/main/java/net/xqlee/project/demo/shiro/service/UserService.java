package net.xqlee.project.demo.shiro.service;

import java.util.*;

import org.springframework.stereotype.Component;

import net.xqlee.project.demo.shiro.pojo.LoginAccount;

/**
 * 用户业务服务类
 * 
 * @author xqlee
 *
 */
@Component("userService")
public class UserService {
	/** 由于重点不在数据库,这里需要使用数据库的地方全部用map代替 **/
	/** 用户信息 **/
	static Map<String, LoginAccount> users = new HashMap<>();

	static Map<String,LoginAccount> tokens=new HashMap<>();

	static {
		// 创建一个用户
		LoginAccount account = new LoginAccount();
		account.setLoginName("leftso");
		account.setPassword("123456");
		account.setEnabled(true);
		account.setExpired(false);

		// 角色添加
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_USER");
		account.setRoles(roles);

		// List<String> permissions = new ArrayList<>();
		// permissions.add("query");
		// permissions.add("delete");
		// permissions.add("user:h");
		//
		// account.setPermissions(permissions);
		users.put(account.getLoginName(), account);


	}

	/**
	 * 通过用户名获取用户权限集合
	 * 
	 * @param loginName
	 *            用户名
	 * @return 用户的权限集合
	 */
	public List<String> getPermissionsByLoginName(String loginName) {

		if (users.containsKey(loginName)) {
			return users.get(loginName).getPermissions();
		}
		return new ArrayList<>();

	}

	/**
	 * 通过用户名获取用户信息
	 * 
	 * @param loginName
	 *            用户名
	 * @return 用户信息
	 */
	public LoginAccount getLoginAccountByLoginName(String loginName) {
		if (users.containsKey(loginName)) {
			return users.get(loginName);
		}
		return null;
	}


	public String createToken(LoginAccount account){
		 String token= UUID.randomUUID().toString().replace("-","").toUpperCase();
		 tokens.put(token,account);
		 return token;
	}

	public LoginAccount getAccountByToken(String token){
		return tokens.get(token);
	}
}
