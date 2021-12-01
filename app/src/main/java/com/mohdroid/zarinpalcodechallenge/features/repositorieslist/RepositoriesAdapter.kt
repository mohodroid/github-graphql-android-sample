package com.mohdroid.zarinpalcodechallenge.features.repositorieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mohdroid.domain.dto.repositories.service.RepositoryResponseService
import com.mohdroid.zarinpalcodechallenge.R

class RepositoriesAdapter(
    private val repositoryResponseService: RepositoryResponseService
) : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(repositoryResponseService, position)
    }

    override fun getItemCount(): Int = repositoryResponseService.repositories.size

    fun addRange(positionStart: Int, repositoryResponseService: RepositoryResponseService) {
        this.repositoryResponseService.repositories.addAll(
            positionStart,
            repositoryResponseService.repositories)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val stars: TextView = view.findViewById(R.id.start_text)
        private val repoName: TextView = view.findViewById(R.id.repo_name_text)
        fun onBind(
            repos: RepositoryResponseService,
            position: Int,
        ) {
            repoName.text = repos.repositories[position].name
            stars.text = repos.repositories[position].stars.toString()
        }

    }
}