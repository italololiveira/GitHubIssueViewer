package br.com.githubissueviewer.data.remote

import androidx.lifecycle.MutableLiveData
import br.com.githubissueviewer.data.GitHubIssue
import br.com.githubissueviewer.data.GitHubIssueDataSource
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubIssueRemoteDataSource : GitHubIssueDataSource{

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(GitHubIssueNetworkService::class.java)
    }

    val issuesLiveData = MutableLiveData<List<GitHubIssue>>()

    override suspend fun getIssuesFromRepo(): List<GitHubIssue> = withContext(Dispatchers.Default) {
        runCatching {
            webservice.getIssuesFromRepo(",","","")
        }.getOrThrow()
    }


}