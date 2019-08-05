package org.jbestie.sqlexp.config;

import javax.sql.DataSource;

import org.jbestie.sqlexp.handler.SqlExpSimpleUrlLogoutSuccessHandler;
import org.jbestie.sqlexp.handler.SqlExpSavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    final DataSource dataSource;

    public SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/","/registration", "/createuser", "/resources/**")
                .permitAll()
            .antMatchers("/**")
                .hasAnyRole("ADMIN", "USER")
            .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler())
                .permitAll()
            .and()
                .httpBasic()
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler())
                .logoutSuccessUrl("/")
            .and()
                .csrf()
                .disable();
    }


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) 
        throws Exception {
        
        auth.jdbcAuthentication().dataSource(dataSource)
            .passwordEncoder(passwordEncoder())
            .usersByUsernameQuery("select login, password, active from users where login=?")
            .authoritiesByUsernameQuery("select u.login as login, r.name from users u join roles r on u.role = r.id where login=?");
    }   


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SqlExpSavedRequestAwareAuthenticationSuccessHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new SqlExpSimpleUrlLogoutSuccessHandler();
    }
}
