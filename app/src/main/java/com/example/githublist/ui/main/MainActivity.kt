package com.example.githublist.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githublist.R
import com.example.githublist.adapters.GithubRepositoryAdapter
import com.example.githublist.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by viewModel()
    val picasso: Picasso by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getRepositories()
    }

    fun getRepositories() {
        mainViewModel.getGithubRepository("Kotlin", "6")

        isLoadingObserver()
        errorObserver()
        completeObserver()
    }

    fun isLoadingObserver() {
        mainViewModel.isLoading.observe(this) {
            if (it == true) {
                repositoryProgressBar.visibility = View.VISIBLE
                recyclerViewRepository.visibility = View.GONE
            } else {
                repositoryProgressBar.visibility = View.GONE
                recyclerViewRepository.visibility = View.VISIBLE
            }
        }
    }

    fun errorObserver() {
        mainViewModel.messageError.observe(this) {
            if (!it.equals("")) {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun completeObserver() {
        mainViewModel.githubRepositories.observe(this) {
            recyclerViewRepository.adapter = GithubRepositoryAdapter(it, picasso) {
//                val intent = Intent(this, PullRequestActivity::class.java)
//                intent.putExtra(Constants.REPOSITORY_ITEM, it)
//                startActivity(intent)
//                finish()
                Toast.makeText(this, it.username, Toast.LENGTH_SHORT).show()
            }
            recyclerViewRepository.layoutManager = LinearLayoutManager(this)
        }
    }
}