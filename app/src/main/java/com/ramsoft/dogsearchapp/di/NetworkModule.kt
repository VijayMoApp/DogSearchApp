package com.ramsoft.dogsearchapp.di


import android.app.Application
import com.ramsoft.dogsearchapp.BuildConfig

import com.ramsoft.dogsearchapp.networking.ApiServices
import com.ramsoft.dogsearchapp.networking.CusttomInterceptor
import com.ramsoft.dogsearchapp.utils.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {


    @Provides
    @Singleton
    fun provideBaseUrl () = ApiConstants.BASE_URL

    @Provides
    @Singleton
    fun provideInterceptor( application: Application) : CusttomInterceptor {
        return CusttomInterceptor((application))

    }


    @Provides
    @Singleton
    fun provideOkHttp(customInterceptor: CusttomInterceptor) : OkHttpClient{
        val logs = HttpLoggingInterceptor().apply {
            if(BuildConfig.DEBUG){
                this.level = HttpLoggingInterceptor.Level.BODY
            }else{
                this.level = HttpLoggingInterceptor.Level.NONE
            }
        }
        return OkHttpClient.Builder()
            .readTimeout(60L,TimeUnit.SECONDS)
            .writeTimeout(60L,TimeUnit.SECONDS)
            .connectTimeout(60L,TimeUnit.SECONDS)
            .build()

    }

  @Singleton
  @Provides
  fun provideConverttoFactory(): GsonConverterFactory =
      GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl : String,
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory): Retrofit{
        return  Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

}