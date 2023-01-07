package com.yapp.core.kotlin

fun <T> List<T>.copy(mutator: MutableList<T>.() -> Unit) = toMutableList().apply(mutator).toList()
