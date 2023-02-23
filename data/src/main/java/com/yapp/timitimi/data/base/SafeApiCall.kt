@file:OptIn(FlowPreview::class)

package com.yapp.timitimi.data.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import timber.log.Timber

suspend fun <DATA, DOMAIN> apiCall(
    call: suspend () -> Response<DATA>,
    mapper: (DATA) -> DOMAIN,
): Flow<Result<DOMAIN>> = call.asFlow()
    .map {
        Result.success(mapper(it.data))
    }
    .catch { emit(Result.failure(it)) }
    .flowOn(Dispatchers.IO)


/* flow {
 val f = call.asFlow()
 val data = call().data
 val transformData = mapper(data)
 emit(transformData)
}
*/
