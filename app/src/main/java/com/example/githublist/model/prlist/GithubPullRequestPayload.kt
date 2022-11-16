package com.example.githublist.model.prlist

data class GithubPullRequestPayload(
    val title: String,
    val created_at: String,
    val body: String,
    val html_url: String,
    val user: UserPayload
)