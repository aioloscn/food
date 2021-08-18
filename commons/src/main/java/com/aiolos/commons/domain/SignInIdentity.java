package com.aiolos.commons.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 登录认证对象
 * @author Aiolos
 * @date 2021/8/16 10:23 下午
 */
@Getter
@Setter
public class SignInIdentity implements UserDetails {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 角色
     */
    private String roles;

    /**
     * 是否有效 0=无效 1=有效
     */
    private Integer isValid;

    /**
     * 角色集合, 不能为空
     */
    private List<GrantedAuthority> authorities;

    /**
     * 获取角色信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (StringUtils.isNotBlank(this.roles)) {
            // 获取角色信息
            this.authorities = Stream.of(this.roles.split(",")).map(role -> {
                return new SimpleGrantedAuthority(role);
            }).collect(Collectors.toList());
        } else {
            // 如果角色为空则设置为 ROLE_USER
            this.authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        }
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isValid == 0 ? false : true;
    }
}
