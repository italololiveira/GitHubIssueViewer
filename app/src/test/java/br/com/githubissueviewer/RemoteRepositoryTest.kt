package br.com.githubissueviewer

import android.net.Uri
import br.com.githubissueviewer.data.GitHubIssue
import br.com.githubissueviewer.data.GitHubUser
import br.com.githubissueviewer.data.remote.GitHubIssueNetworkService
import com.google.gson.GsonBuilder
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.net.URI
import java.nio.file.Paths

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RemoteRepositoryTest {

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(GitHubIssueNetworkService::class.java)
    }

    @Test
    fun firstCorrect() {
        runBlocking(Dispatchers.Default){
            val expectedFirstIssue =
                GitHubIssue(
                    523464086,
                    "IR: don't attempt to move defaults to `actual` in another module",
                    "open",
                    "https://api.github.com/repos/JetBrains/kotlin/issues/2796",
                    "2019-11-15T13:16:02Z",
                    GitHubUser(
                        1849909,
                        "pyos",
                        "https://avatars2.githubusercontent.com/u/1849909?v=4"
                    )
                )
            assertEquals(
                webservice.getIssuesFromRepo(
                    "Jetbrains",
                    "kotlin",
                    "open"
                ).first(), expectedFirstIssue
            )
        }
    }


}

