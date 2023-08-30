package com.cyclingTour.mapper;

import com.cyclingTour.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ren
 * @since 2023-08-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
