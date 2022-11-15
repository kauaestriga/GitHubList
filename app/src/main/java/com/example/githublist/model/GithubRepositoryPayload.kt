package com.example.githublist.model

import com.google.gson.annotations.SerializedName

class GithubRepositoryPayload(
    @SerializedName("name") val nameRepository: String,
    @SerializedName("full_name")val fullName: String,
    @SerializedName("description")val descriptionRepository: String,
    @SerializedName("forks_count")val forksCount: String,
    @SerializedName("stargazers_count")val starsCount: String,
    @SerializedName("owner/login")val username: String,
    @SerializedName("owner/avatar_url")val avatarUrl: String
)