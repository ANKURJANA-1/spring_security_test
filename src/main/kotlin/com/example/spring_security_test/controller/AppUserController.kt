package com.example.spring_security_test.controller

import com.example.spring_security_test.model.AppUserTest
import com.example.spring_security_test.service.AppUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/")
class AppUserController {

    @Autowired
    private lateinit var appUserService: AppUserService

    @PostMapping("addUser")
    fun addUser(
        @RequestBody
        appUserTest: AppUserTest
    ): AppUserTest {
        appUserTest.let { it ->
            println(it.id)
            println(it.active)
            println(it.userName)
            println(it.password)
            println(it.roles)

        }
        return appUserService.saveUser(appUserTest)
    }

    @GetMapping("hello")
    fun hello(): String {
        return "in user controller"
    }

}