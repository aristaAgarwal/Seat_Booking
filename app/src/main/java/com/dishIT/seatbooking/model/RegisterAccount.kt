package com.dishIT.seatbooking.model

data class RegisterAccount(
    val email: String,
    val firstName: String,
    val id: String,
    val login: String,
    val password: String,
    val activated: Boolean = true,
    val authorities: List<String> =  listOf("employee"),
    val createdBy: String = "",
    val createdDate: String =" ",
    val imageUrl: String = "",
    val langKey: String ="",
    val lastModifiedBy: String = "user",
    val lastModifiedDate: String  ="2022-11-08T18:59:51.488Z",
    val lastName: String = " "
)