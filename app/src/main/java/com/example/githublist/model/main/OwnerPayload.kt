package com.example.githublist.model.main

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OwnerPayload(
    val login: String,
    val avatar_url: String
): Parcelable