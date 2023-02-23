package com.yapp.timitimi.data.repository

import com.yapp.timitimi.data.api.TimiServiceApi
import com.yapp.timitimi.data.base.apiCall
import com.yapp.timitimi.data.response.toDomain
import com.yapp.timitimi.domain.entity.Task
import com.yapp.timitimi.domain.respository.TasksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val timiService: TimiServiceApi,
) : TasksRepository {
    override suspend fun postProjectTasks(projectId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getProjectTasks(projectId: Int): Flow<Result<List<Task>>> {
        return apiCall(
            call = { timiService.getProjectTasks(projectId) },
            mapper = { data ->
                data.map { it.toDomain() }
            },
        )
    }

    override suspend fun getTasks(taskId: String) {
        TODO("Not yet implemented")
    }
}