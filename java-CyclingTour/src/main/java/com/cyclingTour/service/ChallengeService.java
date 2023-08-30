package com.cyclingTour.service;

import com.cyclingTour.common.Result;
import com.cyclingTour.entity.Challenge;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyclingTour.entity.ChallengeDetails;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ren
 * @since 2023-08-09
 */
public interface ChallengeService extends IService<Challenge> {

    public List<ChallengeDetails> queryList();
}
