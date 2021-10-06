package com.aiolos.diners.mapper;

import com.aiolos.food.pojo.Diners;
import com.aiolos.food.pojo.vo.ShortDinerInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DinersMapper extends BaseMapper<Diners> {

    /**
     * 根据id集合查询多个食客信息
     * @param ids
     * @return
     */
    List<ShortDinerInfo> findByIds(@Param("ids") String[] ids);
}