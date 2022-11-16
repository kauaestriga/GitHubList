package com.example.githublist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githublist.databinding.RepositoryItemBinding
import com.example.githublist.model.GithubRepositoryPayload
import com.squareup.picasso.Picasso

class GithubRepositoryAdapter(
    private val repositories: List<GithubRepositoryPayload>,
    private val picasso: Picasso,
    val clickListener: (GithubRepositoryPayload) -> Unit
) : RecyclerView.Adapter<GithubRepositoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RepositoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val binding = RepositoryItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(repositories[position]){
                binding.repositoryName.text = this.name
                binding.descriptionText.text = this.description
                binding.forksCounter.text = this.forks_count
                binding.startsCounter.text = this.stargazers_count
                binding.usernameProfile.text = this.owner?.login ?: ""
                binding.fullnameProfile.text = this.full_name

                picasso.load(this.owner?.avatar_url).into(binding.profileItem)
                binding.root.setOnClickListener { clickListener(this) }
            }
        }
    }
}