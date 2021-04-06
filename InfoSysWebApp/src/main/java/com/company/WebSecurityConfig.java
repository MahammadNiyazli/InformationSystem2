package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailService")
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth
               .userDetailsService(userDetailsService)
               .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/login**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/sign**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/base").hasAnyAuthority("USER","ADMIN")
                .and()
                .authorizeRequests().antMatchers("/journal").hasAnyAuthority("USER","ADMIN")
                .and()
                .authorizeRequests().antMatchers("/audio").hasAnyAuthority("USER","ADMIN")
                .and()
                .authorizeRequests().antMatchers("/document").hasAnyAuthority("USER","ADMIN")
                .and()
                .authorizeRequests().antMatchers("/video").hasAnyAuthority("USER","ADMIN")
                .and()
                .authorizeRequests().antMatchers("/image").hasAnyAuthority("USER","ADMIN")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/base",true).permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
                 .and().csrf().disable();
    }
}
