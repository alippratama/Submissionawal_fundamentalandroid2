package com.example.githubuser

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.ActivityMainBinding
import com.example.submissionawal_fundamentalandroid.MainViewmodel
import com.example.submissionawal_fundamentalandroid.data.model.ResponseUserGithub
import com.example.submissionawal_fundamentalandroid.data.remote.ApiClient
import com.example.submissionawal_fundamentalandroid.utils.Result
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {
        UserAdapter()
    }

    private val viewModel by viewModels<MainViewmodel> ()
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        binding.recyclerView.layoutManager =LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

        viewModel.resultUser.observe(this){
           when(it){
               is Result.Success<*> -> {
                   adapter.setData(it.data as MutableList<ResponseUserGithub.Item>)
               }
               is Result.Error -> {
                   Toast.makeText(this,it.exception.message.toString(), Toast.LENGTH_SHORT).show()
               }
               is Result.loading -> {
                   binding.progressBar.isInvisible = it.isloading
               }
           }
        }

    viewModel.getUser()
    }
}