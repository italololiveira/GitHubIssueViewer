package br.com.githubissueviewer.data.remote

import br.com.githubissueviewer.data.entities.GitHubIssue
import br.com.githubissueviewer.data.GitHubIssueDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

class GitHubIssueRemoteDataSource : GitHubIssueDataSource, KoinComponent {

    private val webservice : GitHubIssueNetworkService by inject()

    override suspend fun getIssuesFromRepo(): List<GitHubIssue> = withContext(Dispatchers.IO) {
        runCatching {
            webservice.getIssuesFromRepo(
                "Jetbrains",
                "kotlin"
            )
        }.getOrThrow()
    }
}