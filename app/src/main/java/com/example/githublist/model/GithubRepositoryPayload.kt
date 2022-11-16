package com.example.githublist.model

data class GithubRepositoryPayload(
    val name: String?,
    val full_name: String?,
    val description: String?,
    val forks_count: String?,
    val stargazers_count: String?,
    val owner: OwnerPayload?
)

