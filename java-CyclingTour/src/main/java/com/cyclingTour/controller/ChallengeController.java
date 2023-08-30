package com.cyclingTour.controller;


import com.cyclingTour.common.Result;
import com.cyclingTour.entity.Challenge;
import com.cyclingTour.entity.ChallengeDetails;
import com.cyclingTour.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ren
 * @since 2023-08-09
 */
@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

//    添加挑战信息
    @PostMapping("/add")
    public Result add(@RequestBody Challenge challenge){
        challengeService.save(challenge);
        return Result.success();
    }

//    挑战记录查询
    @GetMapping()
    public Result queryList(){
       return Result.success(challengeService.queryList());
    }
}
