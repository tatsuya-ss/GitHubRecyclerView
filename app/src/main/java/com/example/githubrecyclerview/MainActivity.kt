package com.example.githubrecyclerview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView

    val todoList = listOf(
        "宿題", "掃除",
        "選択", "メール",
        "読書", "アニメ見る",
        "Kotlinの勉強", "散歩",
        "📱買い替え", "ゲーム",
        "宿題", "掃除",
        "選択", "メール",
        "読書", "アニメ見る",
        "Kotlinの勉強", "散歩",
        "📱買い替え", "ゲーム",
        "宿題", "掃除",
        "選択", "メール",
        "読書", "アニメ見る",
        "Kotlinの勉強", "散歩",
        "📱買い替え", "ゲーム"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupRecyclerView()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.RecyclerView)
        recyclerView.adapter = RecyclerViewAdapder(todoList)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}