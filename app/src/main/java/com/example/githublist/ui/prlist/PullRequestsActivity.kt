package com.example.githublist.ui.prlist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githublist.databinding.ActivityPullRequestsBinding
import com.example.githublist.model.main.GithubRepositoryPayload
import com.example.githublist.utils.Constants
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class PullRequestsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPullRequestsBinding
    private lateinit var githubRepositoryPayload: GithubRepositoryPayload
    val viewModel by viewModel<PullRequestsViewModel>()
    val picasso: Picasso by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPullRequestsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getIntentValues(intent)
    }

    fun getIntentValues(intent: Intent) {
        githubRepositoryPayload = intent.getParcelableExtra(Constants.REPOSITORY_ITEM)!!
        getPullRequests(githubRepositoryPayload.owner.login, githubRepositoryPayload.name)
    }

    fun getPullRequests(author: String, repository: String) {
        viewModel.getGithubPullRequests(author, repository)

        isLoadingObserver()
        errorObserver()
        completeObserver()
    }

    fun isLoadingObserver() {
        viewModel.isLoading.observe(this@PullRequestsActivity) {
            if (it == true) {
                binding.pullRequestProgressBar.visibility = View.VISIBLE
                binding.recyclerViewPullRequest.visibility = View.GONE
            } else {
                binding.pullRequestProgressBar.visibility = View.GONE
                binding.recyclerViewPullRequest.visibility = View.VISIBLE
            }
        }
    }

    fun errorObserver() {
        viewModel.messageError.observe(this@PullRequestsActivity) {
            if (!it.equals("")) {
                Toast.makeText(this@PullRequestsActivity, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun completeObserver() {
        viewModel.githubPullRequests.observe(this@PullRequestsActivity) { it ->
            binding.recyclerViewPullRequest.adapter = GithubPullRequestAdapter(it, picasso) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.html_url))
                startActivity(browserIntent)
            }
            binding.recyclerViewPullRequest.layoutManager = LinearLayoutManager(this@PullRequestsActivity)
        }
    }
}