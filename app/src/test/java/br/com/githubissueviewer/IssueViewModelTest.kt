package br.com.githubissueviewer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.githubissueviewer.data.GitHubIssue
import br.com.githubissueviewer.data.GitHubIssueDataSource
import br.com.githubissueviewer.data.GitHubIssueRepository
import br.com.githubissueviewer.issues.IssuesViewModel
import br.com.githubissueviewer.main.module
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.willReturn
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
import org.koin.core.qualifier.named
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
    // kotlin :
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
    fun testLiveDataDeliveryWithEmpty() {
        declareMock<GitHubIssueRepository> {
            given { runBlockingTest { getIssuesFromRepo() } }.will { emptyList<GitHubIssue>() }
        }
        model.getIssues()
        assert(!model.issuesListLiveData.value.isNullOrEmpty())
    }

    @Test
    fun testLiveDataDeliveryWithNotEmpty() {
        declareMock<GitHubIssueRepository> {
            given { runBlockingTest { getIssuesFromRepo() } }.will { emptyList<GitHubIssue>() }
        }
        model.getIssues()
        assert(model.issuesListLiveData.value.isNullOrEmpty())
    }

}

