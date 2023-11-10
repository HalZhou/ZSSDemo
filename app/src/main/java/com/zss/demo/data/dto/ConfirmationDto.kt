package com.zss.demo.data.dto

class ConfirmationDto {
    val code : String? = null

    val parameters : MutableList<String> = arrayListOf()

    val message : String? = null

    val confirmedOption : ConfirmationOptionMessage? = null

    val options : MutableList<ConfirmationOptionMessage> = arrayListOf()

    val doubleCheck : Boolean = false
}