package com.demo.activiti.config;

import com.demo.activiti.entity.Permission;
import com.demo.activiti.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

/**
 * @author wangxing
 * @date 2024-08-12 16:01
 */
@Slf4j
@Configuration
public class SecurityConfig {

    @Autowired
    private PermissionService permissionService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
                authorizeRequests = http.csrf().disable().authorizeRequests();
        // 读取数据库权限配置
        // 1.查询到所有的权限
        List<Permission> allPermission = permissionService.list();
        // 2.分别添加权限规则
        allPermission.forEach(p -> {
            authorizeRequests.antMatchers(p.getUrl()).hasAnyAuthority(p.getName());
        });
        authorizeRequests.antMatchers("/**").fullyAuthenticated()
                .anyRequest().authenticated().and().formLogin();
        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            web.ignoring().antMatchers("/css/**");
            web.ignoring().antMatchers("/js/**");
            web.ignoring().antMatchers("/img/**");
            web.ignoring().antMatchers("/plugins/**");
            web.ignoring().antMatchers("/login");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
