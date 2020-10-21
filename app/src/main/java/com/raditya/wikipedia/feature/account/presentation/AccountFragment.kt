package com.raditya.wikipedia.feature.account.presentation

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.raditya.wikipedia.R
import com.raditya.wikipedia.feature.account.presentation.state.AccountState
import com.raditya.wikipedia.file.FileUtils
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.fragment_account) {

    @Inject
    internal lateinit var vm: AccountViewModel

    private var uris: List<Uri>? = null
    private val safeArgs: AccountFragmentArgs by navArgs()
    private lateinit var avatar: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val flows = setupView(view)
        vm.state.observe(viewLifecycleOwner, ::states)
        //vm.processIntents(flows, lifecycleScope)
    }

    private fun setupView(view: View) {
        val email = safeArgs.email
        val password = safeArgs.password
        view.findViewById<TextView>(R.id.tvEmail).text = email
        view.findViewById<TextView>(R.id.tvPassword).text = password
        view.findViewById<ImageView>(R.id.ivAvatar)
            .also { avatar = it }
            .setOnClickListener {
                pickImage.launch("image/*")
            }
    }

    private fun states(state: AccountState) {

    }

    private val pickImage: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                val file = FileUtils.getFile(requireActivity(), it)
                val mediaType = requireActivity().contentResolver.getType(it)!!.toMediaTypeOrNull()!!
                vm.upload(file, mediaType)
                avatar.load(it) {
                    transformations(CircleCropTransformation())
                }
            }
        }
}

