package net.xqlee.project.demo.shiro.handler;

import com.alibaba.fastjson.JSON;
import net.xqlee.project.demo.shiro.pojo.Result;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ControllerExceptionHandler {
    /***
     * 权限不足处理
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {UnauthorizedException.class})
    public ModelAndView unauthorizedExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        System.out.println(e.getClass().getName());
        String uri = request.getRequestURI();
        boolean isJsonRequest = uri.toLowerCase().endsWith(".html")==false;
        if (isJsonRequest) {
            ModelAndView view = new ModelAndView();
            MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
            jsonView.setAttributesMap(JSON.parseObject(JSON.toJSONString(Result.noPermission())));
            view.setView(jsonView);
            return view;
        } else {
            ModelAndView view = new ModelAndView();
            view.setViewName("redirect:/error/403");
            return view;
        }
    }

    /***
     * 未登录处理
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {UnauthenticatedException.class})
    public ModelAndView unauthenticatedExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        System.out.println(e.getClass().getName());
        String uri = request.getRequestURI();
        boolean isJsonRequest = uri.toLowerCase().endsWith(".html")==false;
        if (isJsonRequest) {
            ModelAndView view = new ModelAndView();
            MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
            jsonView.setAttributesMap(JSON.parseObject(JSON.toJSONString(Result.noLogin())));
            view.setView(jsonView);
            return view;
        } else {
            ModelAndView view = new ModelAndView();
            view.setViewName("redirect:/error/401");
            return view;
        }
    }

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public ModelAndView exception(HttpServletRequest request, Exception e) {
        System.out.println(e.getClass().getName());
        String uri = request.getRequestURI();
        boolean isJsonRequest = uri.toLowerCase().endsWith(".html")==false;
        if (isJsonRequest) {
            ModelAndView view = new ModelAndView();
            MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
            jsonView.setAttributesMap(JSON.parseObject(JSON.toJSONString(Result.fail(e.getMessage()))));
            view.setView(jsonView);
            return view;
        } else {
            ModelAndView view = new ModelAndView();
            view.setViewName("redirect:/error");
            return view;
        }
    }
}
