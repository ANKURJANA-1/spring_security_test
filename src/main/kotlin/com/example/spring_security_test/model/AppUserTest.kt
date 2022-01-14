package com.example.spring_security_test.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "app_users")
data class AppUserTest(
    @Id
    val id: String = UUID.randomUUID().toString(),
    var active: Boolean = true,
    var password: String = "",
    var roles: String = "",
    @Column(unique = true)
    var userName: String = ""
)
