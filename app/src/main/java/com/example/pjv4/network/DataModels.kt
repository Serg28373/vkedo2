package com.example.pjv4.network

import com.google.gson.annotations.SerializedName

// ОБНОВЛЕНО: Теперь эта модель содержит и скриншоты
data class AppAll(val id: Int,
                  val title: String,
                  val description: String,
                  @SerializedName("category_id") val categoryId: Int,
                  val size: Float,
                  val version: String,
                  @SerializedName("developer_company_name") val developerCompanyName: String,
                  val price: Float,
                  @SerializedName("apk_file_url") val apkFileUrl: String,
                  @SerializedName("icon_url") val iconUrl: String,
    // ДОБАВЛЕНО: Список URL-адресов скриншотов
                  @SerializedName("images_urls") val imagesUrls: List<String>,
    // ДОБАВЛЕНО: Возрастной рейтинг
                  @SerializedName("age_rule") val ageRule: Int
)
