package com.jutak.assignment3.util

import android.content.Context
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

suspend fun launchApi(
    onError: (Throwable) -> Unit,
    api: suspend () -> Unit,
) {
    try {
        api.invoke()
    } catch (e: HttpException) {
        withContext(Dispatchers.Main) {
            onError.invoke(e)
        }
    }
}

@Singleton
class ApiOnError @Inject constructor(
    @ApplicationContext private val context: Context,
): (Throwable) -> Unit {
    override fun invoke(error: Throwable) {
        when (error) {
            is HttpException -> {
                Toast.makeText(context, error.response()?.errorBody()?.string().toString(), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(context, error.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}