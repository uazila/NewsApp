package kg.example.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kg.example.newsapp.R
import kg.example.newsapp.databinding.FragmentNewsBinding


class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener(){
            save()
        }
    }
private fun save(){
    val text = binding.editText.text.toString().trim()
    val bundle =Bundle()
    bundle.putString("text", text)
    parentFragmentManager.setFragmentResult("rk_news", bundle)
    findNavController().navigateUp()

}}


