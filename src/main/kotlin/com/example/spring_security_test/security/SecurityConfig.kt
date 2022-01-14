package com.example.spring_security_test.security


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@EnableWebSecurity
@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    override fun configure(auth: AuthenticationManagerBuilder?) {
        /*  auth!!.inMemoryAuthentication()
              .withUser("user")
              .password(passwordEncoder().encode("password"))
              .roles("USER")
              .and()
              .withUser("admin")
              .password(passwordEncoder().encode("password"))
              .roles("ADMIN")
        */

        auth!!.authenticationProvider(authProvider())
    }

    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
            .antMatchers("/api/user/**", "/api/admin/**", "/addUser/**").hasRole("ADMIN")
            .antMatchers("/api/user/**").hasRole("USER")
            .antMatchers("/**").hasAnyRole("ADMIN", "USER")
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
            .and()
            .logout()
            .and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authProvider(): DaoAuthenticationProvider? {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }
}