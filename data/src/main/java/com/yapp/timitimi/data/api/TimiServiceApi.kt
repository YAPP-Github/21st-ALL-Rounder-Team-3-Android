package com.yapp.timitimi.data.api

import com.yapp.timitimi.data.base.Response
import com.yapp.timitimi.data.request.PostProjectsBody
import com.yapp.timitimi.data.request.PutProjectBody
import com.yapp.timitimi.data.response.ParticipantResponse
import com.yapp.timitimi.data.response.ProjectIdResponse
import com.yapp.timitimi.data.response.ProjectResponse
import com.yapp.timitimi.data.response.TaskResponse
import com.yapp.timitimi.data.response.UserProfileResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

//todo@jsh-me response 객체 추가 필요
interface TimiServiceApi {
    //    PROJECT
    @GET("/projects")
    suspend fun getAllProject(): Response<List<ProjectResponse>>

    @POST("/projects")
    suspend fun postProjects(
        @Body body: PostProjectsBody
    ): Response<ProjectIdResponse>

    @GET("/projects/{projectId}")
    suspend fun getProject(
        @Path("projectId") projectId: Int
    ): Response<ProjectResponse>

    @PUT("/projects/{projectId}")
    suspend fun putProject(
        @Path("projectId") projectId: Int,
        @Body body: PutProjectBody,
    ): Response<String>


    //    PARTICIPANTS
    @GET("/projects/{projectId}/participants")
    suspend fun getProjectParticipants(
        @Path("projectId") projectId: Int
    ): Response<List<ParticipantResponse>>

    @POST("/projects/{projectId}")
    suspend fun postProjectParticipants(
        @Path("projectId") projectId: String
    ): Flow<Unit>

    @GET("/participants")
    suspend fun getParticipants()

    //    TASKS
    @GET("/projects/{projectId}/tasks")
    suspend fun getProjectTasks(
        @Path("projectId") projectId: Int
    ): Response<List<TaskResponse>>

    @POST("/projects/{projectId}/tasks")
    suspend fun postProjectTasks(
        @Path("projectId") projectId: String
    )

    @GET("/tasks/{taskId}")
    suspend fun getParticipants(
        @Path("taskId") taskId: String
    )

    //        FEEDBACKS
    @POST("/tasks/{taskId}/feedbacks")
    suspend fun postTaskFeedbacks(
        @Path("taskId") projectId: String
    )

    @GET("/tasks/{taskId}/feedbacks")
    suspend fun getTaskFeedbacks(
        @Path("taskId") projectId: String
    )

    @GET("/members")
    suspend fun getUserInfo(): Response<UserProfileResponse>

    @GET("/auth/refresh")
    suspend fun refreshUserToken(
        @Header("appToken") appToken: String,
        @Header("refreshToken") token: String,
    ): Response<Boolean>
}