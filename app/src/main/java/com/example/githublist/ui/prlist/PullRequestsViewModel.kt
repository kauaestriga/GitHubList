package com.example.githublist.ui.prlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githublist.model.prlist.GithubPullRequestPayload
import com.example.githublist.model.prlist.GithubPullRequestResponse
import com.example.githublist.repository.GithubRepository

class PullRequestsViewModel(val githubRepository: GithubRepository) : ViewModel() {

    val messageError: MutableLiveData<String> = MutableLiveData()
    val githubPullRequests: MutableLiveData<List<GithubPullRequestPayload>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getGithubPullRequests(author: String, repository: String) {
        isLoading.value = true

        githubRepository.getGithubPullRequests(
            author = author,
            repository = repository,
            {
                githubPullRequests.value = it
                messageError.value = ""
                isLoading.value = false
            }, {
                githubPullRequests.value = emptyList()
                messageError.value = it?.message
                isLoading.value = false
            }
        )
    }
}