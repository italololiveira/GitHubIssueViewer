package br.com.githubissueviewer.data.entities

enum class GitHubIssueFilter(val filter: String) {

    ASSIGNED("assigned"),
    CREATED("created"),
    MENTIONED("mentioned"),
    SUBSCRIBED("subscribed"),
    ALL("all");

    override fun toString(): String {
        return filter
    }
}