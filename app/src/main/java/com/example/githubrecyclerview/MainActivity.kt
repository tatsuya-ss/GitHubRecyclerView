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
import kotlinx.coroutines.*

class MainActivity(private val gitHubUseCase: GitHubUseCase = GitHubUseCaseImpl()) : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private var githubData = listOf<GitHubEntity>()

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

                withContext(Dispatchers.IO) {
                    val result = gitHubUseCase.fetch()

                    when(result) {
                        null -> println("null")
                        else -> {
                            githubData = result
                            withContext(Dispatchers.Main) {
                                println(githubData)
                                val adapder = recyclerView.adapter as RecyclerViewAdapder
                                adapder.updateList(githubData)
                                adapder.notifyDataSetChanged()
                            }
                        }
                    }

                }
            }
        }
    }

}