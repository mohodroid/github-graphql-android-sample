package com.mohdroid.zarinpalcodechallenge.features.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mohdroid.zarinpalcodechallenge.R
import com.mohdroid.zarinpalcodechallenge.databinding.FragmentProfileBinding
import com.mohdroid.zarinpalcodechallenge.features.common.FrgParent
import com.mohdroid.zarinpalcodechallenge.features.common.RootApp
import com.mohdroid.zarinpalcodechallenge.features.common.viewmodel.ViewModelFactory
import com.mohdroid.zarinpalcodechallenge.utils.LoadImageHelper
import javax.inject.Inject

class UserProfileFragment : FrgParent<UserProfileViewModel>() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    @Inject
    lateinit var imageHelper: LoadImageHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.showUserBasicInfo.observe(viewLifecycleOwner, {
            binding.emailText.text = it.email
            binding.nameText.text = it.name
            binding.followersText.text = String.format(resources.getString(R.string.followers),it.followersCount, it.followingCount)
        })
        viewModel.showUserAvatar.observe(viewLifecycleOwner, {
            imageHelper.loadImage(it, binding.profileImage)
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getFactory(): ViewModelProvider.Factory = factory

    override fun getFactoryClass(): Class<UserProfileViewModel> = UserProfileViewModel::class.java

    override fun showProgress() {
        binding.prg.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.prg.visibility = View.INVISIBLE
    }

    override fun inject() {
        (context?.applicationContext as RootApp).appGraph.inject(this)

    }

}


