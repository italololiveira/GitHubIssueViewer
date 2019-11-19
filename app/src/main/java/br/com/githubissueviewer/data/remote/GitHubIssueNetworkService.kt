package br.com.githubissueviewer.data.remote

import br.com.githubissueviewer.data.entities.GitHubIssue
import br.com.githubissueviewer.data.entities.GitHubIssueFilter
import br.com.githubissueviewer.data.entities.GitHubIssueState
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubIssueNetworkService{

    @GET("/repos/{owner}/{repo}/issues")
    suspend fun getIssuesFromRepo(
        @Path("owner") repositoryOwner : String,
        @Path("repo") repositoryName: String,
        @Query("state") state: GitHubIssueState = GitHubIssueState.ALL,
        @Query("filter") filter: GitHubIssueFilter = GitHubIssueFilter.ALL
        ) : List<GitHubIssue>


}