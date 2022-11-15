package com.example.githublist.api

import com.example.githublist.model.GithubRepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/search/repositories?q=language:{language}&sort=stars&page={page}")
    fun getGithubRepository(
        @Path("language") language: String,
        @Path("page") page: String
    ) : Call<GithubRepositoryResponse>
}