package com.example.spring_security_test

import com.example.spring_security_test.Repository.AppUserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class SpringSecurityTestApplication

fun main(args: Array<String>) {
    runApplication<SpringSecurityTestApplication>(*args)
}
