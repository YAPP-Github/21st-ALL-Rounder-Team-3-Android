package com.yapp.timitimi.presentation.ui.projectlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yapp.timitimi.domain.entity.Participant
import com.yapp.timitimi.presentation.databinding.ViewholderParticipantExtrasBinding
import com.yapp.timitimi.presentation.databinding.ViewholderParticipantProfileBinding
import com.yapp.timitimi.presentation.databinding.ViewholderProjectItemBinding
import com.yapp.timitimi.presentation.ui.projectlist.model.ProjectListItem

class ProjectsItemViewHolder private constructor(
    private val binding: ViewholderProjectItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ProjectListItem) {
        binding.item = item
        bindParticipants(item.participant)
        binding.executePendingBindings()
    }

    private fun bindParticipants(participants: List<Participant>) {
        if (participants.size <= 3) {
            participants.map { participant ->
                val profileBinding = ViewholderParticipantProfileBinding.inflate(
                    LayoutInflater.from(binding.root.context),
                    binding.participantsView,
                    false
                ).apply {
                    crwon.isVisible = participant.isLeader
                    Glide.with(profileImage)
                        .load(participant.imageUrl)
                        .centerCrop()
                        .circleCrop()
                        .into(profileImage)
                }

                binding.participantsView.addView(profileBinding.root)
            }
        } else {
            participants.map { participant ->
                val profileBinding = ViewholderParticipantProfileBinding.inflate(
                    LayoutInflater.from(binding.root.context),
                    binding.participantsView,
                    false
                ).apply {
                    crwon.isVisible = participant.isLeader
                    Glide.with(profileImage)
                        .load(participant.imageUrl)
                        .centerCrop()
                        .circleCrop()
                        .into(profileImage)
                }
                binding.participantsView.addView(profileBinding.root)
            }

            val extraBinding = ViewholderParticipantExtrasBinding.inflate(
                LayoutInflater.from(binding.root.context),
                binding.participantsView,
                false
            ).apply {
                extraText.text = (participants.size - 3).toString()
            }
            binding.participantsView.addView(extraBinding.root)
        }
    }

    companion object {
        fun from(parent: ViewGroup): ProjectsItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewholderProjectItemBinding.inflate(layoutInflater, parent, false)
            return ProjectsItemViewHolder(binding)
        }
    }
}