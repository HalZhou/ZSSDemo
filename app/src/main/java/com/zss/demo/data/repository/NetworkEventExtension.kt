package com.zss.demo.data.repository

import android.net.ParseException
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.MalformedJsonException
import com.zss.common.http.NETWORK_ERROR_CODE
import com.zss.common.http.NETWORK_ERROR_MESSAGE
import com.zss.common.http.PARSE_ERROR_CODE
import com.zss.common.http.PARSE_ERROR_MESSAGE
import com.zss.common.http.SSL_ERROR_CODE
import com.zss.common.http.SSL_ERROR_MESSAGE
import com.zss.common.http.SYSTEM_ERROR_MESSAGE
import com.zss.common.http.TIMEOUT_ERROR_CODE
import com.zss.common.http.TIMEOUT_ERROR_MESSAGE
import com.zss.common.http.UNKNOW_ERROR_CODE
import com.zss.common.http.UNKNOW_ERROR_MESSAGE
import com.zss.demo.base.DataResponse
import com.zss.demo.data.dto.ConfirmationDto
import com.zss.demo.data.dto.WarningDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

fun <T> CoroutineScope.launchRequest(
    block: suspend () -> DataResponse<T>?,
    onSuccess: (DataResponse<T>) -> Unit,
    onFailure: (String?, String?) -> Unit,
    onConfirm: ((ConfirmationDto) -> Unit)? = null,
    onWarning: ((WarningDto) -> Unit)? = null
) = launch {
    handleDataResponse(block, onSuccess, onFailure, onConfirm, onWarning)
}

suspend fun <T> handleDataResponse(
    block: suspend () -> DataResponse<T>?,
    onSuccess: (DataResponse<T>) -> Unit,
    onFailure: (String?, String?) -> Unit,
    onConfirm: ((ConfirmationDto) -> Unit)? = null,
    onWarning: ((WarningDto) -> Unit)? = null
) {
    try {
        block()?.let { it.handleData(onSuccess, onFailure, onConfirm, onWarning) }
    } catch (e: Exception) {
        when (e) {
            is HttpException -> {
                val code = e.code()
                val body = e.response()?.errorBody()
                when (code) {
                    400 -> {
                        try {
                            val type = object : TypeToken<DataResponse<T>>() {}.type
                            val data = Gson().fromJson<DataResponse<T>>(body?.charStream(), type)
                            data.handleData(onSuccess, onFailure, onConfirm, onWarning)
                        } catch (e: Exception) {
                            onFailure("$code", e.message)
                        }
                    }

                    403 -> {
                        //过期，重新登录

                    }

                    500 -> {
                        //系统异常
                        onFailure("$code", SYSTEM_ERROR_MESSAGE)
                    }

                    503 -> {
                        //系统异常
                        onFailure("$code", SYSTEM_ERROR_MESSAGE)
                    }

                    else -> {
                        //其他异常
                        onFailure("$code", e.message() ?: SYSTEM_ERROR_MESSAGE)
                    }
                }
            }

            is JsonParseException, is JSONException, is ParseException, is MalformedJsonException, is NumberFormatException -> {
                //数据解析错误
                onFailure("$PARSE_ERROR_CODE", PARSE_ERROR_MESSAGE)
            }

            is ConnectException -> {
                //网络链接异常
                onFailure("$NETWORK_ERROR_CODE", NETWORK_ERROR_MESSAGE)
            }

            is SSLException -> {
                //SSL 证书验证异常
                onFailure("$SSL_ERROR_CODE", SSL_ERROR_MESSAGE)
            }

            is SocketTimeoutException, is ConnectTimeoutException -> {
                //链接超时异常
                onFailure("$TIMEOUT_ERROR_CODE", TIMEOUT_ERROR_MESSAGE)
            }

            is UnknownHostException -> {
                //未知主机异常
                onFailure("$NETWORK_ERROR_CODE", NETWORK_ERROR_MESSAGE)
            }

            else -> {
                //其他异常
                onFailure("$UNKNOW_ERROR_CODE", UNKNOW_ERROR_MESSAGE)
            }
        }
    }
}


fun <T> DataResponse<T>.handleData(
    onSuccess: (DataResponse<T>) -> Unit,
    onFailure: (String?, String?) -> Unit,
    onConfirm: ((ConfirmationDto) -> Unit)? = null,
    onWarning: ((WarningDto) -> Unit)? = null
) {
    if (this.confirmations.isNotEmpty()) {
        onConfirm?.invoke(this.confirmations[0])
        return
    }
    if (this.errors.isNotEmpty()) {
        val error = this.errors[0]
        onFailure(error.code, error.message)
        return
    }
    if (this.warnings.isNotEmpty()) {
        onWarning?.invoke(this.warnings[0])
        return
    }
    onSuccess(this)
}

