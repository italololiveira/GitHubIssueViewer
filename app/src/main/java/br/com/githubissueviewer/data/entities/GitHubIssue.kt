package br.com.githubissueviewer.data.entities

data class GitHubIssue(
    val id: Long,
    val title: String,
    val state: String,
    val html_url: String,
    val created_at: String,
    val user: GitHubUser,
    val body: String
) {
    fun getCreationDateFormatted(): String =
        created_at.dropLast(1).replace("T",", ")

}