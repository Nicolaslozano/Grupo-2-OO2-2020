
package com.unla.grupo_2_oo2_2020.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsServiceImplementation")
    private UserDetailsService userDetailsService;

    @Bean("bCryptPasswordEncoder")
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**","/resources/**", "/vendor/**", "/static/**", "/js/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/resources/**", "/registration").permitAll().anyRequest().authenticated()
                .and().formLogin().loginPage("/login").loginProcessingUrl("/loginValidation").defaultSuccessUrl("/index", true).permitAll().and().logout()
                .logoutUrl("/requestLogout").deleteCookies("JSESSIONID").permitAll().and().httpBasic().disable().csrf()
                .disable().cors();

        /*
         * http.authorizeRequests() .antMatchers("/resources/**", "/static/**",
         * "/login-style.css", "/log-in.js").permitAll() .anyRequest().authenticated()
         * .and() .formLogin() .loginPage("/login") .loginProcessingUrl("/login/...")
         * .defaultSuccessUrl("/index", true) .permitAll()
         * .usernameParameter("username").passwordParameter("password") .and()
         * .csrf().disable() .logout() .permitAll();
         */
    }

    @Bean("authenticationManager")
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
