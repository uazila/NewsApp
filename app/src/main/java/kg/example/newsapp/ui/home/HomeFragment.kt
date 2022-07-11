package kg.example.newsapp.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kg.example.newsapp.R
import kg.example.newsapp.databinding.FragmentHomeBinding
import kg.example.newsapp.ui.notifications.App
import kg.models.News

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: NewsAdapter
    private var check: Boolean = false
    private var position: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NewsAdapter(this::onClick, this::onLongClick)
        val list = App.database.newsDao().sortAll()
        adapter.addItems(list!!)

    }

    private fun onLongClick(news: News) {
        AlertDialog.Builder(requireContext()).setTitle("Удаление!")
            .setMessage("Вы точно хотите удалить?")
            .setPositiveButton("Удалить", DialogInterface.OnClickListener { dialogInterface, i ->
                App.database.newsDao().delete(news)
                adapter.deleteItem(news)
            }).setNegativeButton("Нет", DialogInterface.OnClickListener { dialogInterface, i ->

            }).show()
    }

    private fun onClick(position: Int) {
        check = true
        val bundle = Bundle()
        val news = adapter.getItem(position)
        bundle.putSerializable("news", news)
        findNavController().navigate(R.id.newsFragment, bundle)
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
        getFragmentManagerNewsFragments()
        getRoomData()
        search()
        initListener()
    }

    private fun getRoomData() {
        adapter.addItems(App.database.newsDao().sortAll())
    }

    private fun getFragmentManagerNewsFragments() {
        parentFragmentManager
            .setFragmentResultListener("rk_news", viewLifecycleOwner) { requestKey, bundle ->
                val news = bundle.getSerializable("news") as News
                if (check) {
                    position?.let {
                        adapter.updateItem(news, it)
                        App.database.newsDao().update(news)
                    }
                }
                Log.e("Home", "text ${news.title} ${news.createdAt}")
                Toast.makeText(requireContext(), news.title, Toast.LENGTH_SHORT).show()
            }
        binding.recyclerView.adapter = adapter
    }

    private fun initListener() {
        binding.fab.setOnClickListener {
            check = false
            findNavController().navigate(R.id.newsFragment)

        }
    }

    private fun search() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val list = App.database.newsDao().getSearch(p0.toString())
                adapter.addItems(list)
            }

        })
    }

}