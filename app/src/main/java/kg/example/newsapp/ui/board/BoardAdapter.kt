package kg.example.newsapp.ui.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.example.newsapp.R
import kg.example.newsapp.databinding.PagerBoardBinding

class BoardAdapter(private val onClickStart: () -> Unit) :
    RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    private val titles = arrayListOf("Cалам", "Привет", "Hello")
    private val lottieAnimationView = arrayListOf(R.raw.charecter, R.raw.music, R.raw.happypencil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PagerBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BoardAdapter.ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = titles.size

    inner class ViewHolder(private var binding: PagerBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.imageView.setAnimation(lottieAnimationView[position])
            binding.textTitle.text = titles[position]
            if (position == titles.size - 1) {
                binding.btnStart.visibility = View.VISIBLE
            } else {
                binding.btnStart.visibility = View.INVISIBLE
            }
            binding.btnStart.setOnClickListener {
                onClickStart()
            }
        }
    }
}
