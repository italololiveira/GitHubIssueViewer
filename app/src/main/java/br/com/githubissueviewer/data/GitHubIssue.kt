package br.com.githubissueviewer.data

import android.icu.text.TimeZoneFormat
import java.text.DateFormat
import java.text.Format
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.util.*

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