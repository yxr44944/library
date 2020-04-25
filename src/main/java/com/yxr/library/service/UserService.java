package com.yxr.library.service;

import com.yxr.library.dao.BUserMapper;
import com.yxr.library.model.BUser;
import com.yxr.library.model.BUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @描述
 * @作者 yxr
 * @日期 4/22/2020 7:36 PM
 */
@Service
public class UserService {
    @Autowired
    BUserMapper bUserMapper;


    public void register(BUser bUser) {
        bUserMapper.insert(bUser);
    }

    public BUser login(BUser bUser) {
        BUserExample bUserExample = new BUserExample();
        bUserExample.createCriteria().andUnameEqualTo(bUser.getUname()).andPwdEqualTo(bUser.getPwd());
        List<BUser> bUsers = bUserMapper.selectByExample(bUserExample);
        if (bUsers!=null&&bUsers.size()>0){
            return bUsers.get(0);
        }else {
            return null;
        }
    }

    public BUser checkPhone(String phone) {
        BUserExample bUserExample = new BUserExample();
        bUserExample.createCriteria().andPhoneEqualTo(phone);
        List<BUser> bUsers = bUserMapper.selectByExample(bUserExample);
        if (bUsers!=null&&bUsers.size()>0){
            return bUsers.get(0);
        }else {
            return null;
        }
    }
    public BUser checkEmail(String email) {
        BUserExample bUserExample = new BUserExample();
        bUserExample.createCriteria().andEmailEqualTo(email);
        List<BUser> bUsers = bUserMapper.selectByExample(bUserExample);
        if (bUsers!=null&&bUsers.size()>0){
            return bUsers.get(0);
        }else {
            return null;
        }
    }
}
