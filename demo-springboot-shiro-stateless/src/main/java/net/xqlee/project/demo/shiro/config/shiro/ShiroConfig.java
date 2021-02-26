package net.xqlee.project.demo.shiro.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.xqlee.project.demo.shiro.config.shiro.cache.RedisCacheManager;

/***
 *
 * @author xqlee
 *
 */
@Configuration
public class ShiroConfig {

	/**
	 * ehcache缓存方案<br/>
	 * 简单的缓存,后续可更换为redis缓存,通过自己实现shiro的CacheManager接口和Cache接口
	 * 
	 * @return
	 */
	@Bean
	public CacheManager shiroEhCacheManager() {
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return cacheManager;
	}

	/**
	 * redis缓存方案
	 * 
	 * @return
	 */
	@Bean
	public CacheManager shiroRedisCacheManager() {
		return new RedisCacheManager();
	}

	/****
	 * 自定义Real
	 * 
	 * @return
	 */
	@Bean
	public UserRealm userRealm() {
		UserRealm realm = new UserRealm();
		// 根据情况使用缓存器
		realm.setCacheManager(shiroRedisCacheManager());// shiroEhCacheManager()
		return realm;
	}

	/**
	 * 自定义的无状态（无session）Subject工厂
	 * 
	 * @return
	 */
	@Bean
	public StatelessDefaultSubjectFactory subjectFactory() {
		return new StatelessDefaultSubjectFactory();
	}

	/**
	 * sessionManager通过sessionValidationSchedulerEnabled禁用掉会话调度器，
	 * 因为我们禁用掉了会话，所以没必要再定期过期会话了。
	 * 
	 * @return
	 */
	@Bean
	public DefaultSessionManager sessionManager() {
		DefaultSessionManager sessionManager = new DefaultSessionManager();
		sessionManager.setSessionValidationSchedulerEnabled(false);
		//缓存
		sessionManager.setCacheManager(shiroRedisCacheManager());
		return sessionManager;
	}

	/***
	 * 安全管理配置
	 * 
	 * @return
	 */
	@Bean
	public SecurityManager defaultWebSecurityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

		// 注意这里必须配置securityManager
		SecurityUtils.setSecurityManager(securityManager);
		// 根据情况选择缓存器
		securityManager.setCacheManager(shiroRedisCacheManager());// shiroEhCacheManager()

		// 设置Subject工厂
		securityManager.setSubjectFactory(subjectFactory());

		// 配置
		securityManager.setRealm(userRealm());

		// session
		securityManager.setSessionManager(sessionManager());

		// 禁用Session作为存储策略的实现。
		DefaultSubjectDAO defaultSubjectDAO = (DefaultSubjectDAO) securityManager.getSubjectDAO();
		DefaultSessionStorageEvaluator defaultSessionStorageEvaluatord = (DefaultSessionStorageEvaluator) defaultSubjectDAO
				.getSessionStorageEvaluator();
		defaultSessionStorageEvaluatord.setSessionStorageEnabled(false);

		return securityManager;
	}

	/**
	 * 配置shiro的拦截器链工厂,默认会拦截所有请求
	 * 
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
		// 配置安全管理(必须)
		filterFactoryBean.setSecurityManager(defaultWebSecurityManager());

		//配置自定义Filter
		filterFactoryBean.getFilters().put("statelessAuthc", statelessAuthcFilter());
		// 配置登陆的地址
		filterFactoryBean.setLoginUrl("/userNoLogin.do");// 未登录时候跳转URL,如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		filterFactoryBean.setSuccessUrl("/welcome.do");// 成功后欢迎页面
		filterFactoryBean.setUnauthorizedUrl("/403.do");// 未认证页面


		// 配置拦截地址和拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();// 必须使用LinkedHashMap,因为拦截有先后顺序
		// statelessAuthc:所有url都必须认无状态证通过才可以访问; anon:所有url都都可以匿名访问

		filterChainDefinitionMap.put("/userNoLogin.do**", "anon");// 未登录跳转页面不设权限认证
		filterChainDefinitionMap.put("/login.do**", "anon");// 登录接口不设置权限认真
		filterChainDefinitionMap.put("/logout.do**", "anon");// 登出不需要认证

		// 下面的的其他资源地址全部需要通过代理登录步骤,注意顺序,必须先进过无状态代理登录后，后面的权限和角色认证才能使用
		filterChainDefinitionMap.put("/**", "statelessAuthc");

		filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		// 全部配置
		// anon org.apache.shiro.web.filter.authc.AnonymousFilter 匿名访问
		//
		// authc org.apache.shiro.web.filter.authc.FormAuthenticationFilter
		// 需要登录,不需要权限和角色可访问
		//
		// authcBasic
		// org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
		//
		// perms
		// org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter
		// 需要给定的权限值才能访问
		//
		// port org.apache.shiro.web.filter.authz.PortFilter
		//
		// rest org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
		//
		// roles org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
		// 需要给定的角色才能访问
		//
		// ssl org.apache.shiro.web.filter.authz.SslFilter
		//
		// user org.apache.shiro.web.filter.authc.UserFilter
		//
		// logout org.apache.shiro.web.filter.authc.LogoutFilter
		return filterFactoryBean;
	}

	/**
	 * Add.4.1 访问控制器.
	 * 
	 * @return
	 */
	@Bean
	public StatelessAccessControlFilter statelessAuthcFilter() {
		StatelessAccessControlFilter statelessAuthcFilter = new StatelessAccessControlFilter();
		return statelessAuthcFilter;
	}

	/**
	 * Add.5.1 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * Add.5.2 自动代理所有的advisor: 由Advisor决定对哪些类的方法进行AOP代理。
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}
}
