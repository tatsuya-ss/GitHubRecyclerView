package com.example.githubrecyclerview

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface GitHubRepository {
    fun fetch(): List<GitHubEntity>?
}

class GitHubRepositoryImpl(private val dataStore: GitHubDataStore = GitHubDataStoreImpl()): GitHubRepository {

    override fun fetch(): List<GitHubEntity>? {
        val result = dataStore.fetch()
        when(result) {
            null -> { return null }
            else -> {
                val listType = object: TypeToken<List<GitHub>>() {}.type
                val gitHubData = Gson().fromJson< List<GitHub> >(result, listType)
                val gitHubEntities = convert(gitHubData)
                return gitHubEntities
            }
        }
    }

}

fun GitHubRepositoryImpl.convert(results: List<GitHub>): List<GitHubEntity> {
    return results.map {
        GitHubEntity(it.full_name)
    }
}