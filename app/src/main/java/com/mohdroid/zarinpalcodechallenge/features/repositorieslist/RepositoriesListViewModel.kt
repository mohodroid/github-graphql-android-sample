package com.mohdroid.zarinpalcodechallenge.features.repositorieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mohdroid.domain.dto.repositories.service.RepositoryResponseService
import com.mohdroid.domain.dto.repositories.service.UserRepositoriesRequestService
import com.mohdroid.domain.result.ServiceResult
import com.mohdroid.domain.service.UserRepositoriesService
import com.mohdroid.zarinpalcodechallenge.features.common.viewmodel.AbsViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoriesListViewModel @Inject constructor(
    private val userRepositoriesService: UserRepositoriesService
) : AbsViewModel() {

    private var offset: Int = 0 //page index
    private val limit: Int = 4 // number items in each page
    private var totalResult = -1

    private val _showUserRepositories: MutableLiveData<RepositoryResponseService> =
        MutableLiveData()
    val showUserRepositories: LiveData<RepositoryResponseService>
        get() = _showUserRepositories

    init {
        viewModelScope.launch {
            getUserRepos()
        }
    }

    private fun getUserRepos() {
        showProgressAction()
        val request = UserRepositoriesRequestService(limit, offset * limit)
        viewModelScope.launch {
            when (val result = userRepositoriesService.getUserRepos(request)) {
                is ServiceResult.Success<RepositoryResponseService> ->{
                    handleResponse(result)
                    totalResult = result.data!!.count
                }
                is ServiceResult.Failure ->
                    handleError(result.error)
            }
        }
    }

    fun onLoadMore() {
        if (totalResult != 0) {
            val totalPage = totalResult / limit
            if (offset >= totalPage) return
        }
        offset += 1
        getUserRepos()
    }

    private fun handleResponse(result: ServiceResult.Success<RepositoryResponseService>) {
        hideProgressAction()
        _showUserRepositories.postValue(result.data)
    }

}