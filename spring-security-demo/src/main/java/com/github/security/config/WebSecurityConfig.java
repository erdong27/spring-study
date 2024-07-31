package com.github.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity//开启方法验证
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize ->
                        authorize
//                                .requestMatchers("/user/list").hasAuthority("USER_LIST")
//                                .requestMatchers("/user/add").hasAuthority("USER_ADD")
                                .requestMatchers("/user/**").hasRole("ADMIN")
//                                .requestMatchers("/**").hasAuthority("ADMIN")
                        //所有都需要认证
                        .anyRequest()
                       //已经认证成功的自动授权
                        .authenticated()
                );
                http.formLogin((login)->{
                    //不需要认证
                        login.loginPage("/login").permitAll()
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .failureUrl("/login?error")
                   // 登陆成功
                    .successHandler(new MyAuthenticationSuccessHandler())
                    //登陆失败
                    .failureHandler(new MyAuthenticationFailureHandler());

                });
                http.logout((logout)->{
                    logout.logoutSuccessHandler(new MyLogoutSuccessHandler())
                    ;
                });
                http.exceptionHandling((e)->{
                    //认证失败处理
                    e.authenticationEntryPoint(new MyAuthenticationEntryPoint());
                    //授权失败处理
                    e.accessDeniedHandler(new MyAccessDeniedHandler());
                });

        http.sessionManagement((session)->{
            session
                    .maximumSessions(1)
                    .expiredSessionStrategy(new MySessionInformationExpiredStrategy());
        });
//          .httpBasic(Customizer.withDefaults());
        http.cors(withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

}
