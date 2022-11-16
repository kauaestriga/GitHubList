package com.example.githublist.repository

import com.example.githublist.api.GithubService
import com.example.githublist.di.networkModule
import com.example.githublist.model.GithubRepositoryPayload
import com.example.githublist.model.GithubRepositoryResponse
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepositoryImpl(
    val githubService: GithubService
) : GithubRepository {

    override fun getGithubRepository(
        language: String,
        page: String,
        onComplete: (List<GithubRepositoryPayload>?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        githubService
            .getGithubRepository(language, page)
            .enqueue(object : Callback<GithubRepositoryResponse> {
                override fun onResponse(
                    call: Call<GithubRepositoryResponse>,
                    response: Response<GithubRepositoryResponse>
                ) {
                    if (response.isSuccessful) {
                        onComplete(response.body()?.items)
                    } else {
                        onError(Throwable("Não foi possível carregar os repositórios"))
                    }
                }

                override fun onFailure(call: Call<GithubRepositoryResponse>, t: Throwable) {
                    onError(t)
                }
            })
    }
}