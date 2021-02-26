package net.xqlee.project.demo.shiro.config.shiro;

import net.xqlee.project.demo.shiro.pojo.LoginAccount;
import net.xqlee.project.demo.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 实现一个基于JDBC的Realm,继承AuthorizingRealm可以看见需要重写两个方法,doGetAuthorizationInfo和doGetAuthenticationInfo
 *
 * @author xqlee
 */
@Component
public class UserRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    /*** 用户业务处理类,用来查询数据库中用户相关信息 ***/
    @Autowired
    UserService userService;

    /**
     * 该Realm仅支持自定义的StatelessAuthenticationToken类型Token，其他类型处理将会抛出异常
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessAuthenticationToken;
    }

    /***
     * 获取用户授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");
        // 获取用户名
        LoginAccount account = (LoginAccount) principalCollection.getPrimaryPrincipal();
        // 判断用户名是否存在
        if (StringUtils.isEmpty(account)) {
           throw new RuntimeException("获取用户授权信息失败");
        }
        // 创建一个授权对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 进行权限设置
        List<String> permissions = account.getPermissions();
        if (permissions != null && !permissions.isEmpty()) {
            info.addStringPermissions(permissions);
        }
        // 角色设置
        List<String> roles = account.getRoles();
        if (roles != null) {
            info.addRoles(roles);
        }
        return info;
    }

    /**
     * 获取用户认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        logger.info("##################执行Shiro登陆认证##################");
        StatelessAuthenticationToken statelessAuthenticationToken = (StatelessAuthenticationToken) authenticationToken;
        // 通过表单接收的用户名
        String token = (String)statelessAuthenticationToken.getPrincipal();
        if (StringUtils.isEmpty(token)) {
            throw new UnknownAccountException("token无效");
        }
        // 根据token获取用户信息
        LoginAccount account = userService.getAccountByToken(token);

        if (account == null) {
            throw new UnknownAccountException("token无效");
        }
        // 创建shiro的用户认证对象
        // 注意该对象的密码将会传递至后续步骤与前面登陆的subject的密码进行比对。
        //这里放入account对象后面授权可以取出来
        //statelessAuthenticationToken会与登录时候的token进行验证这里就放入登录的即可
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(account,
                statelessAuthenticationToken, getName());
        return authenticationInfo;
    }

}
