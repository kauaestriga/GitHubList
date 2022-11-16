package com.example.githublist.model.main

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepositoryPayload(
    val name: String,
    val full_name: String,
    val description: String,
    val forks_count: String,
    val stargazers_count: String,
    val owner: OwnerPayload
) : Parcelable

