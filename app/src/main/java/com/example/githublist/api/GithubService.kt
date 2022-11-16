package com.example.githublist.api

import com.example.githublist.model.main.GithubRepositoryResponse
import com.example.githublist.model.prlist.GithubPullRequestPayload
import com.example.githublist.model.prlist.GithubPullRequestResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("search/repositories")
    fun getGithubRepositories(
        @Query("q") language: String,
        @Query("page") page: String,
        @Query("sort") sort: String = "stars"
    ) : Call<GithubRepositoryResponse>

    @GET("repos/{author}/{repository}/pulls")
    fun getGithubPullRequests(
        @Path("author") author: String,
        @Path("repository") repository: String
    ) : Call<List<GithubPullRequestPayload>>
}