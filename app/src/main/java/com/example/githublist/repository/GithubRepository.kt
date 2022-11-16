package com.example.githublist.repository

import com.example.githublist.model.prlist.GithubPullRequestPayload
import com.example.githublist.model.main.GithubRepositoryPayload
import com.example.githublist.model.prlist.GithubPullRequestResponse

interface GithubRepository {

    fun getGithubRepositories(
        language: String,
        page: String,
        onComplete: (List<GithubRepositoryPayload>?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun getGithubPullRequests(
        author: String,
        repository: String,
        onComplete: (List<GithubPullRequestPayload>?) -> Unit,
        onError: (Throwable?) -> Unit
    )
}