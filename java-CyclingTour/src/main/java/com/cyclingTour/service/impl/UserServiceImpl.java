package com.cyclingTour.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyclingTour.config.RedisConfig;
import com.cyclingTour.entity.User;
import com.cyclingTour.mapper.UserMapper;
import com.cyclingTour.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javafx.collections.ObservableArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ren
 * @since 2023-08-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private RedisConfig redisConfig;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public HashMap<String, Object> login(User user) {
//        判断账户与密码
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
//        wrapper.eq(User::getPassword, user.getPassword());

//        在sevice使用mapper,查询一条数据，账户与密码
        User data1 = this.baseMapper.selectOne(wrapper);
//        值不为空，生成token存入redis
        if (data1 != null&&passwordEncoder.matches(user.getPassword(),data1.getPassword())) {
//      使用UUID生成token
            String key = "user" + UUID.randomUUID();

            HashMap<String, Object> data = new HashMap<>();
            data.put("token", key);
//          密码设置为null
            data1.setPassword(null);
            redisConfig.redisTemplate().opsForValue().set(key, data1, 30, TimeUnit.MINUTES);

            return data;
        }
        return null;
    }

    //未加密密码
//    @Override
//    public HashMap<String, Object> login(User user) {
////        判断账户与密码
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(User::getUsername,user.getUsername());
//        wrapper.eq(User::getPassword,user.getPassword());
////        在sevice使用mapper,查询一条数据，账户与密码
//        User data1= this.baseMapper.selectOne(wrapper);
////        值不为空，生成token存入redis
//        if(data1 !=null){
////      使用UUID生成token
//            String key = "user" + UUID.randomUUID();
//
//            HashMap<String, Object> data=new HashMap<>();
//            data.put("token",key);
////          密码设置为null
//            data1.setPassword(null);
//            redisConfig.redisTemplate().opsForValue().set(key,data1,30, TimeUnit.MINUTES);
//
//            return data;
//        }
//        return null;
//    }
//  获取用户信息 info 接口
    @Override
    public HashMap<String, Object> getinfo(String token) {

//        从redis里面查询数据
        Object o = redisConfig.redisTemplate().opsForValue().get(token);
//        反序列化
        User user = JSON.parseObject(JSON.toJSONString(o), User.class);
        if (user != null) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("id", user.getId());
            data.put("avatar", user.getAvatar());
            data.put("username", user.getUsername());
            data.put("roles", user.getRoles());
            data.put("email", user.getEmail());
            data.put("phone", user.getPhone());
            return data;
        }
        return null;
    }

    //    logout
    @Override
    public void logout(String token) {
        redisConfig.redisTemplate().delete(token);
    }


}
