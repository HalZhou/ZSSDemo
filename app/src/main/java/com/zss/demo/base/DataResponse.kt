package com.zss.demo.base

import com.zss.demo.data.dto.ConfirmationDto
import com.zss.demo.data.dto.ErrorDto
import com.zss.demo.data.dto.MetaDto
import com.zss.demo.data.dto.WarningDto

class DataResponse<T> {
    val data: MutableList<T> = arrayListOf()

    //错误信息，statusCode == 400 时
    val errors: MutableList<ErrorDto> = arrayListOf()

    // 二次确认信息 statusCode == 400
    val confirmations: MutableList<ConfirmationDto> = arrayListOf()

    // //分页信息
    val meta : MetaDto? = null

    // 提示信息，数据保存成功，但是需要下一步操作 statusCode == 200
    val warnings : MutableList<WarningDto> = arrayListOf()
}