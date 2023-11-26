package com.wafflestudio.assignment4.network

import com.squareup.moshi.Moshi
import com.wafflestudio.assignment4.network.dto.login.StatusResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class MyCallAdapterFactory(
    private val moshi: Moshi,
) : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *> {
        return MyCallAdapter<Any>(moshi, returnType)
    }
}

class MyCallAdapter<R>(
    private val moshi: Moshi,
    private val bodyType: Type,
) : CallAdapter<R, Any> {

    override fun responseType(): Type {
        return (bodyType as? ParameterizedType)?.let {
            it.actualTypeArguments[0]
        } ?: Unit::class.java
    }

    override fun adapt(call: Call<R>): Any {
        return call.let {
            object : Call<R> by it {
                override fun enqueue(callback: Callback<R>) {
                    it.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            if (response.isSuccessful) {
                                callback.onResponse(call, response)
                            } else {
                                callback.onFailure(call, parseErrorBody(response))
                            }
                        }

                        override fun onFailure(call: Call<R>, t: Throwable) {
                            callback.onFailure(call, t)
                        }
                    })
                }
            }
        }
    }

    private fun parseErrorBody(response: Response<*>): Throwable {
        val errorBodyJsonString = response.errorBody()?.string().toString()
        val errorDTO =
            moshi.adapter(StatusResponse::class.java).fromJson(errorBodyJsonString)
        return ErrorParsedHttpException(response, errorDTO)
    }
}

class ErrorParsedHttpException(
    response: Response<*>,
    val errorDTO: StatusResponse? = null,
) : HttpException(response)
