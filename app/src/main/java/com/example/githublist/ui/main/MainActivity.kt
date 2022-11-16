package com.example.githublist.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githublist.databinding.ActivityMainBinding
import com.example.githublist.ui.prlist.PullRequestsActivity
import com.example.githublist.utils.Constants
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel by viewModel<MainViewModel>()
    val picasso: Picasso by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getRepositories()
    }

    fun getRepositories() {
        viewModel.getGithubRepository("language:Java", "1")

        isLoadingObserver()
        errorObserver()
        completeObserver()
    }

    fun isLoadingObserver() {
        viewModel.isLoading.observe(this@MainActivity) {
            if (it == true) {
                binding.repositoryProgressBar.visibility = View.VISIBLE
                binding.recyclerViewRepository.visibility = View.GONE
            } else {
                binding.repositoryProgressBar.visibility = View.GONE
                binding.recyclerViewRepository.visibility = View.VISIBLE
            }
        }
    }

    fun errorObserver() {
        viewModel.messageError.observe(this@MainActivity) {
            if (!it.equals("")) {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun completeObserver() {
        viewModel.githubRepositories.observe(this@MainActivity) { it ->
            binding.recyclerViewRepository.adapter = GithubRepositoryAdapter(it, picasso) {
                val intent = Intent(this, PullRequestsActivity::class.java)
                intent.putExtra(Constants.REPOSITORY_ITEM, it)
                startActivity(intent)
            }
            binding.recyclerViewRepository.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}