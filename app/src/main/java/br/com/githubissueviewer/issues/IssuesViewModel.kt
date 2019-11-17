package br.com.githubissueviewer.issues

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.githubissueviewer.data.GitHubIssue
import br.com.githubissueviewer.data.GitHubIssueRepository
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class IssuesViewModel : ViewModel(), KoinComponent {

    val repository: GitHubIssueRepository by inject()
    val issuesListLiveData = MutableLiveData<List<GitHubIssue>>()

    fun getIssues() {
        viewModelScope.launch {
            issuesListLiveData.postValue(repository.getIssuesFromRepo())
        }
    }

}