package com.example.spring_security_test.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api")
class HomeController {

    @GetMapping("/")
    fun home(): String {
        return "home"
    }

    @GetMapping("/user")
    fun user(): String {
        return "user"
    }

    @GetMapping("/admin")
    fun admin(): String {
        return "admin"
    }
}