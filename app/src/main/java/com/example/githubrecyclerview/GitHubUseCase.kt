package com.example.githubrecyclerview

interface GitHubUseCase {
    fun fetch(): List<GitHubEntity>?
}

class GitHubUseCaseImpl(private val repository: GitHubRepository = GitHubRepositoryImpl()): GitHubUseCase {

    override fun fetch(): List<GitHubEntity>? {
        return repository.fetch()
    }

}