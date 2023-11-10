package com.zss.demo.data.dto

import com.zss.demo.base.LookupDto

class SsoAuthenticateDto {
    var type: LookupDto = LookupDto()

    var username: String? = null

    var password: String? = null

    var wechatUnionId: String? = null

    var keepSignedIn = false
}