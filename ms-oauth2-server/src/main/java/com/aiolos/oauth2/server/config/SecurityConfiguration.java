package com.aiolos.oauth2.server.config;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Security 配置类
 * @author Aiolos
 * @date 2021/8/15 11:04 下午
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final RedisConnectionFactory redisConnectionFactory;

    public SecurityConfiguration(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    /**
     * 初始化 RedisTokenStore 用于将token存储至redis
     * @return
     */
    @Bean
    public RedisTokenStore redisTokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix("TOKEN:");    // 设置key的层级前缀，方便查询
        return redisTokenStore;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {

            /**
             * 加密
             *
             * @param charSequence 原始密码
             * @return
             */
            @Override
            public String encode(CharSequence charSequence) {
                return DigestUtil.md5Hex(charSequence.toString());
            }

            /**
             * 校验密码
             *
             * @param charSequence 原始密码
             * @param s            加密密码
             * @return
             */
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return DigestUtil.md5Hex(charSequence.toString()).equals(s);
            }
        };
    }

    /**
     * 初始化认证管理对象
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * 放行和认证规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()   // csrf认证除了get的以外的请求都是不安全的，这里禁用掉，按自己配置的方式去做拦截和放行
                .authorizeRequests()
                // 匹配请求放行的规则
                .antMatchers("/oauth/**", "/actuator/**").permitAll()
                .and()
                .authorizeRequests()
                // 其他请求必须认证才能访问
                .anyRequest().authenticated();
    }
}
