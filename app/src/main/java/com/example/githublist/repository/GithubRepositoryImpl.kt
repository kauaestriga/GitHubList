package com.example.githublist.repository

import com.example.githublist.api.GithubService
import com.example.githublist.model.prlist.GithubPullRequestPayload
import com.example.githublist.model.prlist.GithubPullRequestResponse
import com.example.githublist.model.main.GithubRepositoryPayload
import com.example.githublist.model.main.GithubRepositoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepositoryImpl(
    val githubService: GithubService
) : GithubRepository {

    override fun getGithubRepositories(
        language: String,
        page: String,
        onComplete: (List<GithubRepositoryPayload>?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        githubService
            .getGithubRepositories(language, page)
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

    override fun getGithubPullRequests(
        author: String,
        repository: String,
        onComplete: (List<GithubPullRequestPayload>?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        githubService
            .getGithubPullRequests(author, repository)
            .enqueue(object : Callback<List<GithubPullRequestPayload>> {
                override fun onResponse(
                    call: Call<List<GithubPullRequestPayload>>,
                    response: Response<List<GithubPullRequestPayload>>
                ) {
                    if (response.isSuccessful) {
                        onComplete(response.body())
                    } else {
                        onError(Throwable("Não foi possível carregar os PRs"))
                    }
                }

                override fun onFailure(call: Call<List<GithubPullRequestPayload>>, t: Throwable) {
                    onError(t)
                }
            })
    }
}