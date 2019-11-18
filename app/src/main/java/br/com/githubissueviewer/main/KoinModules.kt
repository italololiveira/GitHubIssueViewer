package br.com.githubissueviewer.main

import br.com.githubissueviewer.data.GitHubIssueRepository
import br.com.githubissueviewer.data.remote.GitHubIssueRemoteDataSource
import br.com.githubissueviewer.issues.IssuesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val module = module {

    single { GitHubIssueRepository(get(named("remote"))) }
    single(named("remote")) { GitHubIssueRemoteDataSource() }
    viewModel { IssuesViewModel() }
}