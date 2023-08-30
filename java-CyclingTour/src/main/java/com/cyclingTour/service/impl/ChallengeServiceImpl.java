package com.cyclingTour.service.impl;

import com.cyclingTour.common.Result;
import com.cyclingTour.entity.Challenge;
import com.cyclingTour.entity.ChallengeDetails;
import com.cyclingTour.mapper.ChallengeMapper;
import com.cyclingTour.service.ChallengeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ren
 * @since 2023-08-09
 */
@Service
public class ChallengeServiceImpl extends ServiceImpl<ChallengeMapper, Challenge> implements ChallengeService {
    @Autowired
    private ChallengeMapper challengeMapper;
    @Override
    public List<ChallengeDetails> queryList() {
        List<ChallengeDetails> result = challengeMapper.queryList();
        return result;
    }
}
