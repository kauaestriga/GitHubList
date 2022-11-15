package com.example.githublist.repository

import com.example.githublist.model.GithubRepositoryPayload

interface GithubRepository {

    fun getGithubRepository(
        language: String,
        page: String,
        onComplete: (List<GithubRepositoryPayload>?) -> Unit,
        onError: (Throwable?) -> Unit
    )
}