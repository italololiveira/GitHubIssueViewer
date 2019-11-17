package br.com.githubissueviewer.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitHubIssueRepository(val remoteRepository: GitHubIssueDataSource) : GitHubIssueDataSource {

    var cachedIssues: List<GitHubIssue> = emptyList()

    override suspend fun getIssuesFromRepo(): List<GitHubIssue> =
        withContext(Dispatchers.Default) {
            runCatching { remoteRepository.getIssuesFromRepo() }
                .onSuccess { cachedIssues = it }
                .getOrThrow()
        }

}
