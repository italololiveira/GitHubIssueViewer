package br.com.githubissueviewer.data

interface GitHubIssueDataSource {

    suspend fun getIssuesFromRepo(): List<GitHubIssue>

}