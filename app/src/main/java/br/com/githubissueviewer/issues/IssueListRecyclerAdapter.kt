package br.com.githubissueviewer.issues

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.githubissueviewer.data.entities.GitHubIssue
import br.com.githubissueviewer.databinding.ItemIssueBinding

class IssueListRecyclerAdapter(val issueList : List<GitHubIssue>, val onItemCLick: (GitHubIssue) -> Unit) : RecyclerView.Adapter<IssueItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueItemViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        return IssueItemViewHolder(ItemIssueBinding.inflate(inflater,parent,false))
    }

    override fun getItemCount(): Int =
        issueList.size

    override fun onBindViewHolder(holder: IssueItemViewHolder, position: Int) {
        holder.bind(issueList[position])
        holder.itemView.setOnClickListener { onItemCLick.invoke(issueList[position]) }
    }
}

class IssueItemViewHolder (val binding : ItemIssueBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(issue: GitHubIssue){
        binding.issue = issue
        binding.executePendingBindings()
    }

}