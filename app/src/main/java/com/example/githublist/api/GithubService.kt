package com.example.githublist.api

import com.example.githublist.model.GithubRepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

//    @GET("search/repositories?q=language:Kotlin&sort=stars&page=6")
//    fun getGithubRepository(
////        @Path("language") language: String,
////        @Path("page") page: String
//    ) : Call<GithubRepositoryResponse>

    @GET("search/repositories")
    fun getGithubRepository(
        @Query("q") language: String,
        @Query("page") page: String,
        @Query("sort") sort: String = "stars"
    ) : Call<GithubRepositoryResponse>
}