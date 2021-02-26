package net.xqlee.project.demo.shiro.controller;

import net.sf.json.JSONObject;
import net.xqlee.project.demo.shiro.pojo.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ResourcesController {

    @RequiresPermissions("user:h")//需要用户拥有user:h权限
    @GetMapping("/user/hello")
    public String hello() {
        return "Hello User, From Server";
    }

    @GetMapping("/hello")
	@RequiresRoles(value = "ROLE_USER")//需要用户拥有ROLE_USER角色
    public String hello2() {
        return "Hello User2,Form Server";
    }

    @RequiresPermissions("admin:h")//需要用户拥有admin:h权限
    @GetMapping("/admin/hello")
    public String helloAdmin() {
        return "Hello Admin, From Server";
    }

    @GetMapping("/welcome")//
    public String loginSuccess() {
        return "welcome";
    }

    @GetMapping("/403")
    public Object error403(HttpServletResponse response) {
        response.setStatus(403);
        return Result.noPermission();
    }
}
