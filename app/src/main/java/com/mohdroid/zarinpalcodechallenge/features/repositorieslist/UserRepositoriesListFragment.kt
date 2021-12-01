package com.mohdroid.zarinpalcodechallenge.features.repositorieslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mohdroid.zarinpalcodechallenge.databinding.FragmentRepositoriesBinding
import com.mohdroid.zarinpalcodechallenge.features.common.FrgParent
import com.mohdroid.zarinpalcodechallenge.features.common.RootApp
import com.mohdroid.zarinpalcodechallenge.features.common.viewmodel.ViewModelFactory
import javax.inject.Inject

class UserRepositoriesListFragment : FrgParent<RepositoriesListViewModel>() {

    private var _binding: FragmentRepositoriesBinding? = null

    private val binding get() = _binding!!

    private var pastVisibleItems: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var onLoading: Boolean = false
    private var adapter: RepositoriesAdapter? = null

    @Inject
    lateinit var factory: ViewModelFactory

    companion object {
        private const val TAG = "RepositoriesFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel.showUserRepositories.observe(viewLifecycleOwner, {
            if (adapter == null) {
                adapter = RepositoriesAdapter(it)
                binding.rv.adapter = adapter
            } else {
                val positionStart = adapter?.itemCount!!
                adapter?.addRange(positionStart, it)
            }
            onLoading = false
        })

        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    if (!onLoading) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            onLoading = true
                            viewModel.onLoadMore()
                        }
                    }
                }
            }
        })

    }

    private lateinit var layoutManager: LinearLayoutManager


    private fun initRecyclerView() {
        layoutManager = LinearLayoutManager(actParent, RecyclerView.VERTICAL, false)
        binding.rv.layoutManager = layoutManager
        binding.rv.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getFactory(): ViewModelProvider.Factory = factory

    override fun inject() {
        (context?.applicationContext as RootApp).appGraph.inject(this)
    }

    override fun showProgress() {
        binding.prg.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.prg.visibility = View.INVISIBLE
    }

    override fun getFactoryClass(): Class<RepositoriesListViewModel> =
        RepositoriesListViewModel::class.java
}