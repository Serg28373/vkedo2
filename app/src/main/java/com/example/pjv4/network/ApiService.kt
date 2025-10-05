package com.example.pjv4.network

import retrofit2.http.GET
import retrofit2.http.Query  // ИЗМЕНЕНО: Path -> Query

interface ApiService {
    // ОБНОВЛЕНО: Один-единственный метод для получения всех данных
    @GET("app/get_by_params")
    suspend fun getAppDetails(
        @Query("app_id") appId: Int  // @Query добавляет "?app_id=..." к URL
    ): AppAll // Возвращает обновленную модель AppAll
}
