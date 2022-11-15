package com.example.githublist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githublist.R
import com.example.githublist.model.GithubRepositoryPayload
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repository_item.view.*

class GithubRepositoryAdapter(
    val repositories: List<GithubRepositoryPayload>,
    val picasso: Picasso,
    val clickListener: (GithubRepositoryPayload) -> Unit
) : RecyclerView.Adapter<GithubRepositoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.repository_item,
            parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = repositories[position]
        holder.bindView(pokemon, picasso, clickListener)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(repository: GithubRepositoryPayload,
                     picasso: Picasso,
                     clickListener: (GithubRepositoryPayload) -> Unit) = with(itemView) {

            repositoryName.text = repository.nameRepository
            descriptionText.text = repository.descriptionRepository
            forksCounter.text = repository.forksCount
            startsCounter.text = repository.starsCount
            usernameProfile.text = repository.username
            fullnameProfile.text = repository.fullName

            picasso.load(repository.avatarUrl).into(profileItem)
            setOnClickListener { clickListener(repository) }
        }
    }
}