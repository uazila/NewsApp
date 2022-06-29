package kg.example.newsapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kg.example.newsapp.R
import kg.example.newsapp.databinding.FragmentHomeBinding
import kg.models.News

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: NewsAdapter
    private var check: Boolean = false
    private var position: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NewsAdapter {
            position = it
            check = true
            val bundle = Bundle()
            val news = adapter.getItem(it)
            bundle.putSerializable("news", news)
            findNavController().navigate(R.id.newsFragment, bundle)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            check = false
            findNavController().navigate(R.id.newsFragment)

        }
        parentFragmentManager
            .setFragmentResultListener("rk_news", viewLifecycleOwner) { requestKey, bundle ->
                val news = bundle.getSerializable("news") as News
                if (check == true) {
                    position?.let { adapter.updateItem(news, it) }
                } else {
                    adapter.addItem(news)
                }
                Log.e("Home", "text ${news.title} ${news.createdAt}")
                Toast.makeText(requireContext(), news.title, Toast.LENGTH_SHORT).show()
            }


        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // _binding = null
    }
}