package com.example.pjv4

import android.os.Bundle

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.pjv4.ScreenshotsAdapter
import com.example.pjv4.databinding.ActivityAppcardBinding
import com.example.pjv4.network.AppAll
import com.example.pjv4.network.RetrofitClient
import kotlinx.coroutines.launch
import java.util.Locale

class AppCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppcardBinding
    private val apiService = RetrofitClient.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppcardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appId = 1 // ID для теста

        binding.btnBack.setOnClickListener {
            finish()
        }

        // Запускаем загрузку данных
        loadAppData(appId)
    }

    private fun loadAppData(appId: Int) {
        lifecycleScope.launch {
            try {
                // Выполняем один-единственный запрос
                val appData = apiService.getAppDetails(appId)
                // Сразу обновляем UI, как только получили данные
                updateUi(appData)
            } catch (e: Exception) {
                // Обработка ошибок осталась прежней
                e.printStackTrace()
                Toast.makeText(this@AppCardActivity, "Ошибка загрузки: ${e.message}", Toast.LENGTH_LONG).show()
                binding.tvDescription.text = "Не удалось загрузить данные"
                // Можно добавить другие действия, например, скрыть ProgressBar
            }
        }
    }

    private fun updateUi(app: AppAll) {
        binding.apply {
            val baseUrl = "http://fitfully-thrilling-dingo.cloudpub.ru" // Используем HTTP
            val iconUrl = baseUrl + app.iconUrl

            // 1. Заполняем основную информацию
            ivIcon.load(iconUrl) { crossfade(true) }
            tvTitle.text = app.title
            tvCompany.text = app.developerCompanyName
            tvDescription.text = app.description
            tvSize.text = String.format(Locale.US, "%.1f МБ", app.size)
            btnInstall.text = if (app.price > 0) String.format("Купить за $%.2f", app.price) else "Установить"

            // 2. Заглушки для данных, которых нет в API
            // TODO: Реализовать получение категории и рейтинга, если они появятся в API
            tvAge.text = "${app.ageRule}+"

            tvCategory.text = "Категория" // Заглушка

            // 3. Настраиваем галерею скриншотов из обновленной модели
            if (app.imagesUrls.isNotEmpty()) {
                viewPager.visibility = View.VISIBLE
                val adapter = ScreenshotsAdapter(app.imagesUrls) // Используем поле imagesUrls
                viewPager.adapter = adapter
            } else {
                viewPager.visibility = View.GONE
            }
        }
    }
}
