package com.ramsoft.dogsearchapp.networking

import android.app.Application

import com.ramsoft.dogsearchapp.utils.Util
import com.ramsoft.dogsearchapp.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class CusttomInterceptor @Inject constructor(val application: Application) : Interceptor {

    lateinit var response: Response


    override fun intercept(chain: Interceptor.Chain): Response {
        if (!Util.isNetWorkConnected(application)){
            throw  IOException(application.getString(R.string.no_internet_connection))
        }
        var request = chain.request().newBuilder()
            .build()

        try {
            response = chain.proceed(request)
        }catch (e:Exception){
            throw IOException(e.message)
        }
        return response
    }
}