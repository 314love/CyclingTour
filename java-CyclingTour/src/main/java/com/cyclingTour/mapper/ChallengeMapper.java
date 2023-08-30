package com.cyclingTour.mapper;

import com.cyclingTour.common.Result;
import com.cyclingTour.entity.Challenge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyclingTour.entity.ChallengeDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ren
 * @since 2023-08-09
 */
@Mapper
public interface ChallengeMapper extends BaseMapper<Challenge> {
//查看挑战记录
    public List<ChallengeDetails> queryList();
}
