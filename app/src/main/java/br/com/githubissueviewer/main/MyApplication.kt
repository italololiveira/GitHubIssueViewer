package br.com.githubissueviewer.main

import android.app.Application
import br.com.githubissueviewer.data.GitHubIssueDataSource
import br.com.githubissueviewer.data.GitHubIssueRepository
import br.com.githubissueviewer.data.remote.GitHubIssueRemoteDataSource
import br.com.githubissueviewer.issues.IssuesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin { androidContext(this@MyApplication) }

        loadKoinModules(module)
    }


    val module = module {

        single { GitHubIssueRepository(get(named("remote"))) }
        single(named("remote")) { GitHubIssueRemoteDataSource() }
        viewModel { IssuesViewModel() }
    }
}