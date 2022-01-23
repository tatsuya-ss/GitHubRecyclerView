package com.example.githubrecyclerview

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

data class GitHub(
    val full_name: String
    )

interface GitHubDataStore {
    fun fetch(): String?
}

class GitHubDataStoreImpl(): GitHubDataStore {

    override fun fetch(): String? {
        val urlString = "https://api.github.com/users/tatsuya-ss/repos"
        val (_, _, result) = urlString.httpGet().responseString()
        return when (result) {
            is Result.Failure -> {
                return null
            }
            is Result.Success -> {
                val jsonString = result.get()
                return jsonString
            }
        }
    }

}