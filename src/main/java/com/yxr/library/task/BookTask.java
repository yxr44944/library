package com.yxr.library.task;

import com.yxr.library.dao.BookMapper;
import com.yxr.library.model.Book;
import com.yxr.library.model.BookExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


/**
 * @描述
 * @作者 yxr
 * @日期 4/25/2020 4:23 PM
 */
@Component
public class BookTask {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    BookMapper mapper;


    @Scheduled(cron = "0/30 * * * * ?") //每隔30s，更新一下数据库中的点击量的数据
    public void updateRedisTraffic() {
        System.out.println("=============30秒更新一次数据库================================");
        //得到当前库中所有的书籍
        List<Book> books = mapper.selectByExample(new BookExample());
        //判断id是否存在在如果存在则更新数据库中的点击量
        if(books!=null && books.size()>0){
            for (Book book : books) {
                String id = book.getBid();
                //判断id在redis是否存在
                if(redisTemplate.hasKey(id)) {
                    //如果当前key在redis是存在的，则去同步数据库
                    Integer  count = (Integer) redisTemplate.opsForValue().get(id);
                    //赋值
                    book.setClickCount(BigDecimal.valueOf(count));
                    //更新数据库
                    mapper.updateByPrimaryKeySelective(book);
                }
            }
        }


    }


}
