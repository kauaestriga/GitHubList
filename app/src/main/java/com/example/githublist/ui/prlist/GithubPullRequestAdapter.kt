package com.example.githublist.ui.prlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githublist.databinding.PullRequestItemBinding
import com.example.githublist.databinding.RepositoryItemBinding
import com.example.githublist.model.main.GithubRepositoryPayload
import com.example.githublist.model.prlist.GithubPullRequestPayload
import com.example.githublist.model.prlist.GithubPullRequestResponse
import com.squareup.picasso.Picasso

class GithubPullRequestAdapter(
    private val pullRequests: List<GithubPullRequestPayload>,
    private val picasso: Picasso,
    val clickListener: (GithubPullRequestPayload) -> Unit
) : RecyclerView.Adapter<GithubPullRequestAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: PullRequestItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val binding = PullRequestItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pullRequests.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(pullRequests[position]){
                binding.pullRequestTitle.text = this.title
                binding.pullRequestDescription.text = this.body
                binding.pullRequestUsername.text = this.user.login
                binding.pullRequestFullName.text = this.user.login

                picasso.load(this.user.avatar_url).into(binding.pullRequestProfile)
                binding.root.setOnClickListener { clickListener(this) }
            }
        }
    }
}