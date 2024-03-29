package com.example.submissionawal_fundamentalandroid

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionawal_fundamentalandroid.data.remote.ApiClient
import com.example.submissionawal_fundamentalandroid.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewmodel:ViewModel() {

    val resultUser = MutableLiveData<Result>()

    fun getUser(){
        viewModelScope.launch {


                flow {
                    val response = ApiClient
                        .githubService
                        .getUserGithub()

                    emit(response)
                }.onStart {
                    resultUser.value = Result.loading(true)
                }.onCompletion {
                    resultUser.value = Result.loading(false)
                }.catch {
                   Log.e("error",it.message.toString())
                    it.printStackTrace()
                    resultUser.value = Result.Error(it)
                }.collect {
                    resultUser.value = Result.Success(it)
                }

        }
    }
}