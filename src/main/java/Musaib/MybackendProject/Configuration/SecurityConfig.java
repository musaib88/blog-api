package Musaib.MybackendProject.Configuration;

import Musaib.MybackendProject.Services.MyUserDetailService;
import Musaib.MybackendProject.Utilities.JwtVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    MyUserDetailService myUserDetailService;
    @Autowired
     JwtVerification jwtVerification ;
    @Override
    protected void configure(HttpSecurity http) throws Exception{
                 http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate/users")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilterBefore(jwtVerification, UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {auth.userDetailsService(myUserDetailService).passwordEncoder(password());
     }
     @Bean
     public PasswordEncoder password(){
        return new BCryptPasswordEncoder();
}}