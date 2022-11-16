package com.example.githublist.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githublist.model.main.GithubRepositoryPayload
import com.example.githublist.repository.GithubRepository

class MainViewModel(val githubRepository: GithubRepository) : ViewModel() {

    val messageError: MutableLiveData<String> = MutableLiveData()
    val githubRepositories: MutableLiveData<List<GithubRepositoryPayload>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getGithubRepository(language: String, page: String) {
        isLoading.value = true

        githubRepository.getGithubRepositories(
            language = language,
            page = page,
            {
                githubRepositories.value = it
                messageError.value = ""
                isLoading.value = false
            }, {
                githubRepositories.value = emptyList()
                messageError.value = it?.message
                isLoading.value = false
            }
        )
    }
}