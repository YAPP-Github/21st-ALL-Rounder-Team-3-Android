package com.yapp.timitimi.data.base

inline fun <DATA, DOMAIN> apiCall(
    call: () -> Response<DATA>,
    mapper: (DATA) -> DOMAIN,
): Result<DOMAIN> = runCatching {
    val data = call().data
    mapper(data)
}