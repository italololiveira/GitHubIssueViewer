package br.com.githubissueviewer.issues

import br.com.githubissueviewer.data.entities.GitHubIssue

interface IssueContract {

    interface UserActions{

    }

    interface IssueListView {
        fun populateIssueList(issueList: List<GitHubIssue>)
        fun openIssueDetail(issue: GitHubIssue)
    }

    interface IssueDetailView{
        fun populateView(issue: GitHubIssue)
    }

}