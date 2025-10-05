package com.example.pjv4.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val BASE_URL = "https://fitfully-thrilling-dingo.cloudpub.ru:443/api/v1/"

    // Создаем логгер для перехвата и вывода в консоль сетевых запросов и ответов.
    // Это очень полезно для отладки.
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Создаем кастомный OkHttp-клиент, чтобы добавить к нему логгер и настроить таймауты
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // Добавляем логгер
        .connectTimeout(30, TimeUnit.SECONDS) // Таймаут на подключение
        .readTimeout(30, TimeUnit.SECONDS)    // Таймаут на чтение ответа
        .build()

    // 'lazy' означает, что объект Retrofit будет создан только при первом обращении к нему,
    // а не при запуске приложения.
    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // Используем наш кастомный клиент
            .addConverterFactory(GsonConverterFactory.create()) // Указываем, что ответы в формате JSON
            .build()

        // Этот синтаксис 100% корректен для Retrofit 2.9.0
        retrofit.create(ApiService::class.java)
    }
}
