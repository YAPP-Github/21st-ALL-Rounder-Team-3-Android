package com.yapp.data.retrofit

import retrofit2.Retrofit

interface RetrofitProvider {
    fun provide(baseUrl: String): Retrofit
    fun provide(): Retrofit
}
