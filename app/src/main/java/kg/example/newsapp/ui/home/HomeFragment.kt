package kg.example.newsapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kg.example.newsapp.R
import kg.example.newsapp.databinding.FragmentHomeBinding
import kotlin.math.log

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding



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
        binding.fab.setOnClickListener{
    findNavController().navigate(R.id.newsFragment)

        }
        parentFragmentManager
            .setFragmentResultListener("rk_news", viewLifecycleOwner){requestKey, bundle ->
                val text = bundle.getString("text")
                Log.e("Home", "text $text")
                Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
            }


    }

    override fun onDestroyView() {
        super.onDestroyView()
       // _binding = null
    }
}