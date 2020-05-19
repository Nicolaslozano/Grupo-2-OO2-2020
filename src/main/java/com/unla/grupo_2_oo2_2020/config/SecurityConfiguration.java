
package com.unla.grupo_2_oo2_2020.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    
    @Override
     public void configure(WebSecurity web) throws Exception {
         web.ignoring().antMatchers("/resources/**");
         // Spring Security should completely ignore URLs starting with /resources/
     }

     @Override
     public void configure(HttpSecurity security) throws Exception {
         security.httpBasic().disable();
     }
}
