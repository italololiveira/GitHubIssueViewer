package br.com.githubissueviewer.data.entities

enum class GitHubIssueState(val state: String){

    OPEN("open"),
    CLOSED("closed"),
    ALL("all");

    override fun toString(): String {
        return state
    }}