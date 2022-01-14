package com.example.spring_security_test.service

import com.example.spring_security_test.Repository.AppUserRepository
import com.example.spring_security_test.model.AppUserTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class AppUserService : UserDetailsService {

    @Autowired
    private lateinit var appUserRepository: AppUserRepository


    fun saveUser(appUser: AppUserTest): AppUserTest {
        val appUserTest: AppUserTest = AppUserTest(
            active = appUser.active,
            password = BCryptPasswordEncoder().encode(appUser.password),
            roles = appUser.roles,
            userName = appUser.userName
        )
        return try {
            appUserRepository.save(appUserTest)
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val findUser = try {
            appUserRepository.findByUserName(username!!)
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
        if (findUser.isEmpty()) {
            throw RuntimeException("user does not exists")
        }
        val authority: ArrayList<SimpleGrantedAuthority> = ArrayList()
        authority.add(
            SimpleGrantedAuthority(
                "ROLE_${findUser[0].roles}"
            )
        )
        return try {
            User(
                findUser[0].userName,
                findUser[0].password,
                authority
            )
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
    }
}