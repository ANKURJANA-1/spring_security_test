package com.example.spring_security_test.Repository

import com.example.spring_security_test.model.AppUserTest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AppUserRepository : JpaRepository<AppUserTest, String> {

    @Query(
        value = "SELECT * FROM app_users WHERE user_name=:userName AND active=true",
        nativeQuery = true
    )
    fun findByUserName(userName: String): List<AppUserTest>
}