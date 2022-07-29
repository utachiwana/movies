package com.utachiwana.movies.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.utachiwana.movies.R
import javax.inject.Inject
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.utachiwana.movies.appComponent
import com.utachiwana.movies.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MovieViewModel.Factory
    private val viewModel: MovieViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var view : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)

        val adapter = MovieAdapter()
        view.movieRecycler.adapter = adapter
        view.movieRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        lifecycleScope.launch {
            viewModel.getMovies().observe(this@MainActivity) {
                adapter.submitData(lifecycle, it)
            }
        }

    }
}