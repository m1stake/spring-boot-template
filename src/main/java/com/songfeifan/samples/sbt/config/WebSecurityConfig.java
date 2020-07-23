package com.songfeifan.samples.sbt.config;

import com.songfeifan.samples.sbt.security.AuthEntryPoint;
import com.songfeifan.samples.sbt.security.AuthFailedHandler;
import com.songfeifan.samples.sbt.security.AuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/static/**",
                        "/webjars/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v2/api-docs",

                        "/h2-console/**"

                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .headers().frameOptions().sameOrigin()

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new AuthEntryPoint())
        ;


        http
                .addFilterBefore(usernamePasswordAuthenticationFilter(), AnonymousAuthenticationFilter.class);

    }


    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() {

        UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();

        filter.setAuthenticationManager(providerManager());
        filter.setAuthenticationSuccessHandler(new AuthSuccessHandler());
        filter.setAuthenticationFailureHandler(new AuthFailedHandler());

        return filter;
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public ProviderManager providerManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);

        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

}
