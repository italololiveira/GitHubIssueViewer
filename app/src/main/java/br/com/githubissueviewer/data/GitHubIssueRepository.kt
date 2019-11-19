package br.com.githubissueviewer.data

import br.com.githubissueviewer.data.entities.GitHubIssue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitHubIssueRepository(val remoteRepository: GitHubIssueDataSource) : GitHubIssueDataSource {

    var cachedIssues: List<GitHubIssue> = emptyList()

    override suspend fun getIssuesFromRepo(): List<GitHubIssue> =
        withContext(Dispatchers.Default) {
            runCatching { remoteRepository.getIssuesFromRepo() }
                .onSuccess { cachedIssues = it }
                .getOrDefault(emptyList())
        }

}
