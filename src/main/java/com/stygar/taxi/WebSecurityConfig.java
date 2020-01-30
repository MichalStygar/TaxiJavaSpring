package com.stygar.taxi;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/klient/addklient").permitAll()
                .antMatchers("/adres/**" ).hasAnyAuthority("admin")
                .antMatchers("/klient/**").hasAnyAuthority("admin")
                .antMatchers("/kurs/**").hasAnyAuthority("admin,user,taksowkarz")
                .antMatchers("/paragon/**").hasAnyAuthority("admin,user,taksowkarz")
                .antMatchers("/samochod/**").hasAnyAuthority("admin")
                .antMatchers("/taksowkarz/**").hasAnyAuthority("admin")
                .antMatchers("/taryfa/**").hasAnyAuthority("admin") 
                .antMatchers("/js/**").permitAll()
                .and().formLogin().loginPage("/login").permitAll()
            .defaultSuccessUrl("/home/taxi",true).and().logout().logoutSuccessUrl("/login");
    }

    @Autowired
    DataSource dataSource;
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select login,haslo, enabled from klient where login=?")
                .authoritiesByUsernameQuery("select login as principal, upraw as role from klient where login=?");
        
        
    }
    
}

