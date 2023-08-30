package com.cyclingTour.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cyclingTour.common.Result;
import com.cyclingTour.entity.User;
import com.cyclingTour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ren
 * @since 2023-08-02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public Result list() {
        return Result.success(userService.list());
    }

    //    登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        HashMap<String, Object> data = userService.login(user);
        return Result.success(data);
    }

    //    获取用户信息 info接口
    @GetMapping("/info")
    public Result info(@RequestParam("token") String token) {
        HashMap<String, Object> data = userService.getinfo(token);
        return Result.success(data);
    }
    //    logout登出,请求头里面携带token，根据token删除存储在redis里面的数据
    @PostMapping("/logout")
    public Result logout(@RequestHeader("X-Token") String token){
        userService.logout(token);
        return Result.success();
    }
//    分页查询
    @GetMapping("/query")
    public Result query(@RequestParam(value = "username",required = false) String username,
                        @RequestParam(value = "phone",required = false) String phone,
                        @RequestParam("currentPage") Integer currentPage,
                        @RequestParam("sizePage") Integer sizePage){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq((username!=null&&!username.isEmpty()), User::getUsername,username);
        wrapper.eq((phone!=null&&!phone.isEmpty()),User::getPhone,phone);
        Page<User> page1 = new Page<>(currentPage,sizePage);
        userService.page(page1, wrapper);

        HashMap<String,Object> data=new HashMap<>();
        data.put("total",page1.getTotal());
        data.put("records",page1.getRecords());
        return Result.success(data);

    }

//     新增
    @PostMapping("/add")
    public Result addUser(@RequestBody User user){
//        对密码进行加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return Result.success();
    }
//    编辑
    @PostMapping("/update")
    public Result updateUser(@RequestBody User user){
        user.setPassword(null);
        userService.updateById(user);
        return Result.success();
    }
//   通过id查询到数据，展示到编辑框里面
    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        return Result.success(user);
    }
//  删除
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") Integer id){
        userService.removeById(id);
        return Result.success();
    }
}
