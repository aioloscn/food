package com.aiolos.oauth2.server.service;

import com.aiolos.commons.domain.SignInIdentity;
import com.aiolos.commons.enums.ErrorEnum;
import com.aiolos.food.pojo.Diners;
import com.aiolos.oauth2.server.mapper.DinersMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Aiolos
 * @date 2021/8/16 3:55 下午
 */
@Service
public class UserService implements UserDetailsService {

    @Resource
    private DinersMapper dinersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (StringUtils.isBlank(username))
            throw new UsernameNotFoundException(ErrorEnum.USERNAME_CAN_NOT_BE_EMPTY.getErrMsg());
        QueryWrapper<Diners> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).or().eq("phone", username).or().eq("email", username);
        Diners diners = dinersMapper.selectOne(queryWrapper);

        if (diners == null)
            throw new UsernameNotFoundException(ErrorEnum.WRONG_USERNAME_OR_PASSWORD.getErrMsg());

        SignInIdentity signInIdentity = new SignInIdentity();
        BeanUtils.copyProperties(diners, signInIdentity);
        return signInIdentity;
    }
}
