package br.com.githubissueviewer.data

import com.google.gson.annotations.SerializedName

data class GitHubIssue(
    val id: Long,
    val title: String,
    val state: String,
    val url: String,
    val created_at: String,
    val user: GitHubUser
)