package com.yapp.timitimi.data.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

inline fun <DATA, DOMAIN> apiCall(
    call: () -> Response<DATA>,
    mapper: (DATA) -> DOMAIN,
): Flow<DOMAIN> {
    val data = call().data
    val transformData = mapper(data)
    return flow { emit(transformData) }
}