package br.com.githubissueviewer.issues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.githubissueviewer.R
import br.com.githubissueviewer.data.entities.GitHubIssue
import br.com.githubissueviewer.databinding.FragmentIssuesBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class IssuesListFragment : Fragment(), IssueContract.IssueListView {

    val viewModel: IssuesViewModel by sharedViewModel()
    lateinit var binding: FragmentIssuesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_issues, container, false)
        binding = FragmentIssuesBinding.bind(view).also { it.lifecycleOwner = this }
        binding.issueListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        viewModel.issuesListLiveData.observe(viewLifecycleOwner, Observer {
            populateIssueList(it)
        })
        viewModel.getIssues()
        return view
    }

    override fun populateIssueList(issueList: List<GitHubIssue>) {
        binding.issueList = issueList
        binding.issueListRecyclerView.adapter = IssueListRecyclerAdapter(issueList) { issue ->
            openIssueDetail(issue)
        }
    }

    override fun openIssueDetail(issue: GitHubIssue) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            add(
                R.id.container,
                IssueDetailFragment().apply {
                    arguments = Bundle().apply { putLong("ISSUE_ID", issue.id) }
                })
            hide(this@IssuesListFragment)
            addToBackStack(null)
        }?.commit()
    }

}