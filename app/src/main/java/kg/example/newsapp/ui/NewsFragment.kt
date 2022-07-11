package kg.example.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kg.example.newsapp.databinding.FragmentNewsBinding
import kg.example.newsapp.ui.notifications.App
import kg.models.News


class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        binding.btnSave.setOnClickListener() {
            save()
        }
    }

    private fun getData() {
        if (arguments != null) {
            val news = requireArguments().getSerializable("news") as News
            binding.editText.setText(news.title)
        }
    }

    private fun save() {
        val text = binding.editText.text.toString().trim()
        val news = News(0, text, System.currentTimeMillis())
        if (arguments != null) {
            val newsArg = requireArguments().getSerializable("news") as News
            val news = News(newsArg.id, text, System.currentTimeMillis())
            App.database.newsDao().update(news)
        } else {
            App.database.newsDao().insert(news!!)
        }
        val bundle = Bundle()
        bundle.putSerializable("news", news)
        parentFragmentManager.setFragmentResult("rk_news", bundle)
        findNavController().navigateUp()

    }
}


