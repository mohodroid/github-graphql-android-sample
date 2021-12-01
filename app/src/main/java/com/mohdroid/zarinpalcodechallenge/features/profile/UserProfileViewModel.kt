package com.mohdroid.zarinpalcodechallenge.features.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mohdroid.domain.dto.profile.service.ProfileResponseService
import com.mohdroid.domain.result.ServiceResult
import com.mohdroid.domain.service.UserProfileService
import com.mohdroid.zarinpalcodechallenge.features.common.viewmodel.AbsViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserProfileViewModel @Inject constructor(
    private val profileService: UserProfileService
) : AbsViewModel() {

    private val _showUserBasicInfo: MutableLiveData<ProfileResponseService> =
        MutableLiveData()
    val showUserBasicInfo: LiveData<ProfileResponseService>
        get() = _showUserBasicInfo

    private val _showUserAvatar: MutableLiveData<String> = MutableLiveData()
    val showUserAvatar : LiveData<String>
    get() = _showUserAvatar

    init {
        showProgressAction()
        viewModelScope.launch {
            when (val userProfile = profileService.getUserProfile()) {
                is ServiceResult.Success<ProfileResponseService> -> {
                    hideProgressAction()
                    if (userProfile.data?.avatarUrl != null)
                        _showUserAvatar.value = userProfile.data?.avatarUrl
                    _showUserBasicInfo.value = userProfile.data
                }
                is ServiceResult.Failure ->
                    handleError(userProfile.error)
            }
        }
    }

}