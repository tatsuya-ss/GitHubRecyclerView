package com.example.githubrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrecyclerview.databinding.ActivityMainBinding
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private var githubData = listOf<GitHubDataStore>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupRecyclerView()
        setupButton()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.RecyclerView)
        recyclerView.adapter = RecyclerViewAdapder(githubData)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupButton() {
        binding.fetchButton.setOnClickListener {
            GlobalScope.launch {
                launch {
                    val result = fetch()
                    when(result) {
                        null -> println("null")
                        else -> {
                            githubData = result
                        }
                    }
                }
            }
            val adapder = recyclerView.adapter as RecyclerViewAdapder
            adapder.updateList(githubData)
            adapder.notifyDataSetChanged()
        }
    }

    private suspend fun fetch(): List<GitHubDataStore>? {
        val urlString = "https://api.github.com/users/tatsuya-ss/repos"
        val (_, _, result) = urlString.httpGet().responseString()
        return when(result) {
            is Result.Failure -> {
                println(result.getException().toString())
                return null
            }
            is Result.Success -> {
                val jsonString = result.get()
                val listType = object: TypeToken< List<GitHubDataStore> >() {}.type
                val gitHubData = Gson().fromJson< List<GitHubDataStore> >(jsonString, listType)
                return gitHubData
            }
        }
    }
}

data class GitHubDataStore(val full_name: String)