package kg.example.newsapp

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kg.example.newsapp.databinding.FragmentProfileBinding
import kg.example.newsapp.ui.notifications.Prefs


class ProfileFragment : Fragment() {

    private lateinit var prefs  : Prefs
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = Prefs(requireContext())
        binding.imageProfile.setOnClickListener() {
            getContent.launch("image/*")
        }
        if (prefs.getName() != null) {
            binding.editText.setText(prefs.getName())
        }
        savingName()
    }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            Glide.with(binding.imageProfile).load(uri).centerCrop().circleCrop().into(binding.imageProfile)
        }

    private fun savingName() {
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                prefs.saveName(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }
}






