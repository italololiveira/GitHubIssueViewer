package br.com.githubissueviewer.data

data class GitHubIssue(
    val id: Long,
    val title: String,
    val state: String,
    val url: String,
    val createdAt: String,
    val user: GitHubUser
)