package com.yxr.library.controller;

import cn.hutool.crypto.SecureUtil;
import com.yxr.library.model.BUser;
import com.yxr.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @描述
 * @作者 yxr
 * @日期 4/22/2020 7:35 PM
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //前往注册页面
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }
    //前往添加
    @RequestMapping("/toLogin")
    public String toAdd(){
        return "login";
    }

    //注册
    @RequestMapping("/register")
    public String register(BUser bUser, Model model){
        //判断传过来的数据是否为空
        if (bUser!=null&&!"".equals(bUser.getUname())){
            //给id赋值
            bUser.setUserid(UUID.randomUUID().toString());
            //创建时间
            bUser.setCreatTime(new Date());
            //加密密码
            bUser.setPwd(SecureUtil.md5(bUser.getPwd()));
            userService.register(bUser);
            return "login";
        }else {
            //如果为空则返回注册页面
            model.addAttribute("msg","不能为空请重新注册");
            return "register";
        }
    }

    //登录
    @RequestMapping("/login")
    public String login(BUser bUser, Model model, HttpServletRequest request){
        //加密密码
        bUser.setPwd(SecureUtil.md5(bUser.getPwd()));
        //判断用户是否为空如果为空则返回页面如果不为空则进行下一次判断
        if (!StringUtils.isEmpty(bUser.getUname())&&!StringUtils.isEmpty(bUser.getPwd())){
            //进行查询
            BUser login=userService.login(bUser);
            //判断是否登录成功成功前往图书列表否则则返回登录页面给提示
            if (login!=null){
                request.getSession().setAttribute("userInfo",login);
                return "redirect:/book/queryBookList";
            }else {
                model.addAttribute("msg","用户名或者密码错误");
                return "login";
            }
        }else {
            model.addAttribute("msg","用户名或者密码不能为空");
            return "login";
        }
    }

    @RequestMapping("/checkPhone")
    @ResponseBody
    public String checkPhone(String phone){
        BUser bUser=userService.checkPhone(phone);
        return  bUser==null?"yes":"no";
    }

    @RequestMapping("/checkEmail")
    @ResponseBody
    public String checkEmail(String email){
        BUser bUser=userService.checkEmail(email);
        return  bUser==null?"yes":"no";
    }

}
