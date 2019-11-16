package br.com.githubissueviewer.issues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.githubissueviewer.R
import br.com.githubissueviewer.data.GitHubIssue
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssuesListFragment : Fragment(){


    val viewModel : IssuesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_issues,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.issuesListLiveData.observe(this, Observer {
            populateIssueList(it)
        })

    }

    private fun populateIssueList(issueList: List<GitHubIssue>?) {

    }

}