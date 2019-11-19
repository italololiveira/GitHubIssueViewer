package br.com.githubissueviewer.issues

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.githubissueviewer.R
import br.com.githubissueviewer.data.entities.GitHubIssue
import br.com.githubissueviewer.databinding.FragmentIssuesDetailBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class IssueDetailFragment : Fragment() , IssueContract.IssueDetailView {

    val viewModel: IssuesViewModel by sharedViewModel()
    lateinit var binding: FragmentIssuesDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_issues_detail, container, false)
        binding = FragmentIssuesDetailBinding.bind(view).also { it.lifecycleOwner = this }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.issuesListLiveData.observe(this, Observer { issueList ->
            populateView(issueList.first { it.id == arguments?.getLong("ISSUE_ID") })
        })
    }

    override fun populateView(issue: GitHubIssue) {
        binding.issue = issue
        binding.issueDetailGithubButton.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(issue.html_url)
                )
            )
        }
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = "Issue detail"
    }

}