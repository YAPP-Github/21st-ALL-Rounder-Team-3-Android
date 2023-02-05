package com.yapp.timitimi.kotlin

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

fun <T, R> ImmutableList<T>.immutableMap(transform: (T) -> R) = map(transform).toImmutableList()

fun <T> ImmutableList<T>.immutableFilter(predicate: (T) -> Boolean) =
    filter(predicate).toImmutableList()