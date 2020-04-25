package com.yxr.library.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxr.library.model.BUser;
import com.yxr.library.model.Book;
import com.yxr.library.service.BookService;
import com.yxr.library.utils.UploadFileUtils;
import com.yxr.library.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * @描述
 * @作者 yxr
 * @日期 4/22/2020 8:15 PM
 */
@Controller
@RequestMapping("/book")
public class BookController {
    //注入redis
    @Autowired
    RedisTemplate redisTemplate;
    @Value("${file.upload.path}")
    private String realPath;
    @Autowired
    BookService bookService;
    //查询出最近借出的状态的多表联查
    @RequestMapping("/queryBookList")
    public String queryBookList(Model model,BookVo bookVo,@RequestParam(defaultValue = "1") Integer cPage) {
        PageHelper.startPage(cPage,3);
        List<BookVo> books = bookService.queryBookList(bookVo);
        PageInfo<BookVo> pageInfo = new PageInfo(books);
        model.addAttribute("books", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        return "list";
    }

    //ajax借书的日志
    @RequestMapping("/lendBook")
    @ResponseBody//返回json
    public String lendBook(String bid, HttpServletRequest request) {
        //获取到session中的数据获取到用户
        BUser userInfo = (BUser) request.getSession().getAttribute("userInfo");
        //添加借出的日志同时更新借出的次数还有状态
        boolean b = bookService.lendBook(userInfo, bid);
       if (b){
        return   "yes";
       }else {
         return  "no";
       }
    }
    //ajax归还状态修改
    @RequestMapping("/backBook")
    @ResponseBody//返回json
    public String backBook(String bid) {
        //判断bid是否为空
        if (!StringUtils.isEmpty(bid)){
            //修改状态
            boolean flag=bookService.backBook(bid);
            if (flag){
                return  "yes";
            }else {
                return  "no";
            }
        }else {
            return "no";
        }
    }
    //前往添加图书页面
    @RequestMapping("toAdd")
    public String toAdd(){
        return "add";
    }
    //添加书籍
    @RequestMapping("/addBook")
    public String addBook(Book book, @RequestParam("file") MultipartFile file, Model model){
        //判断是否有图片传入
        if (file.getSize()>0){
            //如果有就上传
            String upload = UploadFileUtils.upload(file,realPath);
            book.setPhoto(upload);
        }
        int i = bookService.addBook(book);
        if (i>0){
            return "redirect:/book/queryBookList";
        }else {
            model.addAttribute("msg","添加失败请重试");
            return "add";
        }
    }

    //点赞功能
    @RequestMapping("/likeBook")
    @ResponseBody
    public String likeBook(String bid,HttpServletRequest request){
        BUser userInfo = (BUser) request.getSession().getAttribute("userInfo");
        if (userInfo!=null){
            //一个用户只能点击一次判断用户是否为其点赞
            Boolean aBoolean = redisTemplate.hasKey(userInfo.getUserid() + "_like" + bid);
            if (!aBoolean){
                //完成点赞功能
                redisTemplate.opsForHash().put(userInfo.getUserid()+"_like"+bid,userInfo.getUname(),1);
                return "yes";
            }else {
                //取消点赞
                redisTemplate.opsForHash().delete(userInfo.getUserid()+"_like"+bid,userInfo.getUname());
                return "no";
            }

        }else {
            return "noLogin";
        }
    }
    //图书详情
    @RequestMapping("/queryBookListById")
    public String queryBookListById(String bid,Model model){
       BookVo book = bookService.queryBookListById(bid);

        //判断是否存在
        Boolean aBoolean = redisTemplate.hasKey(bid);
        if (aBoolean){//如果存在则取值
            redisTemplate.opsForValue().increment(bid,1);
            Integer clickCount = (Integer) redisTemplate.opsForValue().get(bid);
            book.setClickCount(BigDecimal.valueOf(clickCount));
        }else {
            //不存在则设置点击量为0
            redisTemplate.opsForValue().increment(bid,1);
            Integer clickCount = (Integer) redisTemplate.opsForValue().get(bid);
            book.setClickCount(BigDecimal.valueOf(clickCount));
        }
        model.addAttribute("book", book);
        return "book_info";
    }
    //注销
    @RequestMapping("/reset")
    public String reset(HttpServletRequest request){
        request.getSession().removeAttribute("userInfo");
        return "login";
    }

}
