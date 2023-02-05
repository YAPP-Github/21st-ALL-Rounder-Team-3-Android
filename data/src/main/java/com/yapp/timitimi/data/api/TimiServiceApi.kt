package com.yapp.timitimi.data.api

import com.yapp.timitimi.data.base.Response
import com.yapp.timitimi.data.request.PostProjectsBody
import com.yapp.timitimi.data.response.ParticipantResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

//todo@jsh-me response 객체 추가 필요
interface TimiServiceApi {
    //    PROJECT
    @GET("/projects")
    suspend fun getAllProject()

    @POST("/projects")
    suspend fun postProjects(
        @Body body: PostProjectsBody
    )

    @GET("/projects/{projectId}")
    suspend fun getProject(
        @Path("projectId") projectId: String
    )

    //    PARTICIPANTS
    @GET("/projects/{projectId}/participants")
    suspend fun getProjectParticipants(
        @Path("projectId") projectId: String
    ): Response<List<ParticipantResponse>>

    @POST("/projects/{projectId}/participants")
    suspend fun postProjectParticipants(
        @Path("projectId") projectId: String
    )

    @GET("/participants")
    suspend fun getParticipants()

    //    TASKS
    @GET("/projects/{projectId}/tasks")
    suspend fun getProjectTasks(
        @Path("projectId") projectId: String
    )

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
}