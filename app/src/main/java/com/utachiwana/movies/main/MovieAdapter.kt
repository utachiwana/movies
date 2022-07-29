package com.utachiwana.movies.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.utachiwana.movies.R
import com.utachiwana.movies.data.model.MovieClass


class MovieAdapter :
    PagingDataAdapter<MovieClass, MovieAdapter.MovieViewHolder>(MovieDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MovieClass?) {
            with(itemView) {
                findViewById<TextView>(R.id.movie_title).text = item?.display_title
                findViewById<TextView>(R.id.movie_desc).text = item?.summary_short
                itemView.setOnClickListener{
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item?.link?.url)))
                }
                Glide.with(this)
                    .load(item?.multimedia?.src)
                    .fitCenter()
                    .into(findViewById(R.id.movie_preview))
            }
        }
    }

    private object MovieDiffItemCallback : DiffUtil.ItemCallback<MovieClass>() {

        override fun areItemsTheSame(oldItem: MovieClass, newItem: MovieClass): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieClass, newItem: MovieClass): Boolean {
            return oldItem.link == newItem.link
        }

    }

}