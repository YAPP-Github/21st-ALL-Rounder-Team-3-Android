package com.yapp.timitimi.presentation.ui.projectlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.yapp.timitimi.presentation.R
import com.yapp.timitimi.presentation.databinding.ActivityProjectsBinding
import com.yapp.timitimi.presentation.ui.createproject.CreateProjectActivity
import com.yapp.timitimi.presentation.ui.projectlist.adapter.ProjectListAdapter
import com.yapp.timitimi.presentation.ui.projectlist.adapter.ProjectListListener
import com.yapp.timitimi.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProjectListActivity: BaseActivity<ActivityProjectsBinding>(R.layout.activity_projects), ProjectListListener {
    private val viewModel: ProjectListViewModel by viewModels()

    private val projectsAdapter: ProjectListAdapter by lazy {
        ProjectListAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        observe()
    }

    private fun initViews() {
        binding.projectRecyclerview.adapter = projectsAdapter
        binding.createButton.setOnClickListener {
            startActivity(Intent(this, CreateProjectActivity::class.java))
            finish()
        }
    }

    private fun observe() {
        viewModel.uiState
            .onEach { state ->
                binding.state = state

                when (state) {
                    is ProjectListUiState.Succeed -> {
                        projectsAdapter.submitList(state.list)
                    }

                    else -> Unit
                }
            }
            .launchIn(lifecycleScope)
    }

    override fun onClickCard(id: Int) {
        viewModel.setLastViewedProjectId(id)
        finish()
    }
}