package com.example.githubrecyclerview

import com.github.kittinunf.fuel.httpGet

data class GitHub(
    val full_name: String
    )

interface GitHubDataStore {
    fun fetch(): String?
}

class GitHubDataStoreImpl(): GitHubDataStore {

    override fun fetch(): String? {
        val urlString = "https://api.github.com/users/tatsuya-ss/repos"
        val (_, response, result) = urlString.httpGet().responseString()
        when(response.statusCode) {
            200 -> {
                val jsonString = result.get()
                return jsonString
            }
            else -> {
                return null
            }
        }
    }

}