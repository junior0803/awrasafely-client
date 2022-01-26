package com.pragmanila.awrasafely

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pragmanila.awrasafely.api.ApiInterface
import com.pragmanila.awrasafely.api.RetrofitClient
import com.pragmanila.awrasafely.api.model.Post
import com.pragmanila.awrasafely.api.model.User
import com.pragmanila.awrasafely.api.model.UserResponse
//import com.pragmanila.awrasafely.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainViewModel: ViewModel() {
    private val _createNewUserLiveData:MutableLiveData<UserResponse?> = MutableLiveData()
    var createNewUserLiveData: MutableLiveData<UserResponse?> = _createNewUserLiveData

    // User Info in personal information
    private val _userinfo = MutableLiveData<User>()
    val userInfo : LiveData<User> = _userinfo

    init {
        resetInfo()
    }

    // Reset Value
    fun resetInfo(){
        _userinfo.value = null
        _createNewUserLiveData.value = null
    }

    fun setUserInfo(user: User) {
        Log.e("junior", "setUserInfo : " + user)
        _userinfo.value = user

    }

    fun createNewUser(user: User) {
        val retroService  = RetrofitClient.getRetroInstance().create(ApiInterface::class.java)
        val call = retroService.pushPost(user)
        call.enqueue(object: Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {

                _createNewUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful) {
                    Log.e("junior", "isSuccessful")
                    _createNewUserLiveData.postValue(response.body())
                } else {
                    _createNewUserLiveData.postValue(null)
                }
            }
        })
    }
}