package com.example.logonrmlocal.buscacep.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class  ClientAPI<T>{

    fun  getClient(c: Class<T>, url: String) : T{
        val okhttp = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()

        val retrofit =
                Retrofit.Builder().
                        baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okhttp)
                        .build()
        return  retrofit.create(c)
    }


}


fun  getEnderecoService(): EnderecoService{
    return ClientAPI<EnderecoService>().getClient(EnderecoService::class.java, "https://viacep.com.br/")
}