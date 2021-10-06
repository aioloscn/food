package com.aiolos.follow.mapper;

import com.aiolos.food.pojo.Follow;
import com.aiolos.food.pojo.vo.ShortDinerInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowMapper extends BaseMapper<Follow> {

}