package br.com.githubissueviewer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.githubissueviewer.data.entities.GitHubIssue
import br.com.githubissueviewer.data.GitHubIssueRepository
import br.com.githubissueviewer.data.entities.GitHubUser
import br.com.githubissueviewer.issues.IssuesViewModel
import br.com.githubissueviewer.main.module
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@ExperimentalCoroutinesApi
class IssueViewModelTest : KoinTest {

    val model by inject<IssuesViewModel>()
    private val testDispatcher = TestCoroutineDispatcher()
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        startKoin { modules(module) }
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testLiveDataDeliveryWithNotEmpty() {
        declareMock<GitHubIssueRepository> {
            given { runBlockingTest { getIssuesFromRepo() } }.will { listOf(
                GitHubIssue(
                    0,
                    "TITLE",
                    "opem",
                    "url",
                    "created_at",
                    GitHubUser(0, "logim", "avatar_url"),
                    "body"
                )
            ) }
        }
        model.getIssues()
        assert(!model.issuesListLiveData.value.isNullOrEmpty())
    }

    @Test
    fun testLiveDataDeliveryWithEmpty() {
        declareMock<GitHubIssueRepository> {
            given { runBlockingTest { getIssuesFromRepo() } }.will { emptyList<GitHubIssue>() }
        }
        model.getIssues()
        assert(model.issuesListLiveData.value.isNullOrEmpty())
    }


}

