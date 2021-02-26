package net.xqlee.project.demo.shiro.controller;

import net.xqlee.project.demo.shiro.pojo.LoginAccount;
import net.xqlee.project.demo.shiro.pojo.Result;
import net.xqlee.project.demo.shiro.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录用
 *
 * @author xqlee
 */
@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    /****
     * 用户未登录
     *
     * @return
     */
    @GetMapping("userNoLogin")
    public Object noLogin() {
        return Result.noLogin();
    }

    @GetMapping(value = "/login")
    public Object login(String loginName, String password) {
        try {
            LoginAccount account = userService.getLoginAccountByLoginName(loginName);
            if (account == null) {
                return Result.fail("账号不存在");
            }

            if (!account.getPassword().equals(password)) {
                return Result.fail("密码错误");
            }

            if (!account.isEnabled()) {
                return Result.fail("账号被锁");
            }
            String token = userService.createToken(account);
            return Result.success(token);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

}
