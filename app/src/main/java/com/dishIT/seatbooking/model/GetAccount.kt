package com.dishIT.seatbooking.model

data class GetAccount(
    val activated: Boolean,
    val authorities: List<String>,
    val createdBy: String,
    val createdDate: String,
    val email: String,
    val firstName: String,
    val id: String,
    val imageUrl: Any,
    val langKey: String,
    val lastModifiedBy: String,
    val lastModifiedDate: String,
    val lastName: String,
    val login: String
)