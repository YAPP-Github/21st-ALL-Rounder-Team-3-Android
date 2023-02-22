package com.yapp.timitimi.presentation.ui.projectlist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yapp.timitimi.presentation.ui.projectlist.model.ProjectListItem

class ProjectListAdapter: ListAdapter<ProjectListItem, ProjectsItemViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProjectsItemViewHolder {
        return ProjectsItemViewHolder.from(parent)
    }
    override fun onBindViewHolder(holder: ProjectsItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<ProjectListItem>() {
        override fun areItemsTheSame(oldItem: ProjectListItem, newItem: ProjectListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProjectListItem, newItem: ProjectListItem): Boolean {
            return oldItem == newItem
        }
    }
}