package com.cyclingTour.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cyclingTour.common.Result;
import com.cyclingTour.entity.TripRoutes;
import com.cyclingTour.entity.User;
import com.cyclingTour.service.TripRoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ren
 * @since 2023-08-06
 */
@RestController
@RequestMapping("/trip")
public class TripRoutesController {

    @Autowired
    private TripRoutesService tripRoutesService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping()
    public Result getTrip(){
       return Result.success(tripRoutesService.list());
    }

    //    分页查询
    @GetMapping("/query")
    public Result query(@RequestParam(value = "routeName",required = false) String routeName,
                        @RequestParam(value = "routeRating",required = false) String routeRating,
                        @RequestParam("currentPage") Integer currentPage,
                        @RequestParam("sizePage") Integer sizePage){
        LambdaQueryWrapper<TripRoutes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq((routeName!=null&&!routeName.isEmpty()), TripRoutes::getRouteName,routeName);
        wrapper.eq((routeRating!=null&&!routeRating.isEmpty()),TripRoutes::getRouteRating,routeRating);
        Page<TripRoutes> page1 = new Page<>(currentPage,sizePage);
        tripRoutesService.page(page1, wrapper);

        HashMap<String,Object> data=new HashMap<>();
        data.put("total",page1.getTotal());
        data.put("records",page1.getRecords());
        return Result.success(data);

    }
    //     新增
    @PostMapping("/add")
    public Result addTrip(@RequestBody TripRoutes tripRoutes){

        tripRoutesService.save(tripRoutes);
        return Result.success();
    }
    //    编辑
    @PostMapping("/update")
    public Result updateTrip(@RequestBody TripRoutes tripRoutes){
        tripRoutesService.updateById(tripRoutes);
        return Result.success();
    }
    //   通过id查询到数据，展示到编辑框里面
    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") Integer id){
        TripRoutes byId = tripRoutesService.getById(id);
        return Result.success(byId);
    }
    //  删除
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") Integer id){
        tripRoutesService.removeById(id);
        return Result.success();
    }
}
