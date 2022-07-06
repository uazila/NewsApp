package kg.example.newsapp.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.example.newsapp.databinding.ItemNewsBinding
import kg.models.News
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(private val onClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val list = arrayListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onClick(position)
        }
        if (position % 2 == 0){
            holder.itemView.setBackgroundColor(Color.WHITE)
        }else{
            holder.itemView.setBackgroundColor(Color.GRAY)
        }
    }


    override fun getItemCount() = list.size

    fun addItem(news: News) {
        list.add(0, news)
        notifyItemInserted(0)
        // notifyItemInserted(list.indexOf(news))
    }

    fun getItem(position: Int): News {
        return list[position]
    }

    fun updateItem(news: News, position: Int) {
        list.set(position, news)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private var binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.textTitle.text = news.title
            binding.textData.text = getDate(news.createdAt, "dd/MM/yyyy hh:mm:ss.SSS")
        }
    }

    fun getDate(milliSeconds: Long, dateFormat: String?): String? {
        val formatter = SimpleDateFormat(dateFormat)
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }

}
