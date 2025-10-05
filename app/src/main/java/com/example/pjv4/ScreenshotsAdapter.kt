package com.example.pjv4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load

// Адаптер принимает список URL-адресов картинок в конструкторе
class ScreenshotsAdapter(private val imageUrls: List<String>) :
    RecyclerView.Adapter<ScreenshotsAdapter.ScreenshotViewHolder>() {

    // Этот класс (ViewHolder) хранит ссылки на View-элементы одного слайда.
    // В нашем случае — это всего один ImageView.
    inner class ScreenshotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ivScreenshot)
    }

    // Этот метод вызывается, когда ViewPager'у нужен новый слайд.
    // Он "надувает" (inflate) наш макет item_screenshot.xml.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenshotViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_screenshot, parent, false)
        return ScreenshotViewHolder(view)
    }

    // Этот метод связывает данные (URL картинки) с конкретным слайдом.
    // Он вызывается для каждого элемента в списке.
    override fun onBindViewHolder(holder: ScreenshotViewHolder, position: Int) {
        // Собираем полный URL для загрузки
        val baseUrl = "https://fitfully-thrilling-dingo.cloudpub.ru"
        val fullUrl = baseUrl + imageUrls[position]

        // Используем библиотеку Coil для асинхронной загрузки картинки в ImageView
        holder.imageView.load(fullUrl) {
            crossfade(true) // Добавляем эффект плавного появления
            // Можно добавить заглушку на время загрузки, если у вас есть такой ресурс
            // placeholder(R.drawable.placeholder_image)
        }
    }

    // Этот метод просто возвращает общее количество слайдов.
    override fun getItemCount(): Int {
        return imageUrls.size
    }
}
