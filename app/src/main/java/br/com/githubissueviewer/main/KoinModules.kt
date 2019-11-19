package br.com.githubissueviewer.main

import br.com.githubissueviewer.BuildConfig
import br.com.githubissueviewer.data.GitHubIssueRepository
import br.com.githubissueviewer.data.remote.GitHubIssueNetworkService
import br.com.githubissueviewer.data.remote.GitHubIssueRemoteDataSource
import br.com.githubissueviewer.issues.IssuesViewModel
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val REMOTE_DATASOURCE = "remote"
const val GITHUB_API = "GitHubApi"

val module = module {
    single { GitHubIssueRepository(get(named(REMOTE_DATASOURCE))) }
    single(named(REMOTE_DATASOURCE)) { GitHubIssueRemoteDataSource() }
    viewModel { IssuesViewModel() }
    single(named(GITHUB_API)) { provideRetrofit() }
    single { provideGitHubIssueApi(get(named(GITHUB_API))) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.GITHUB_API_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
}

fun provideGitHubIssueApi(retrofit: Retrofit): GitHubIssueNetworkService {
    return retrofit.create(GitHubIssueNetworkService::class.java)
}