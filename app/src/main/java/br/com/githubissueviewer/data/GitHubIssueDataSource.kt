package br.com.githubissueviewer.data

import br.com.githubissueviewer.data.entities.GitHubIssue

interface GitHubIssueDataSource {

    suspend fun getIssuesFromRepo(): List<GitHubIssue>

}