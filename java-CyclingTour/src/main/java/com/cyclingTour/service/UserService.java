package com.cyclingTour.service;

import com.cyclingTour.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ren
 * @since 2023-08-02
 */
public interface UserService extends IService<User> {

    HashMap<String, Object> login(User user);

    HashMap<String, Object> getinfo(String token);

    void logout(String token);
}
