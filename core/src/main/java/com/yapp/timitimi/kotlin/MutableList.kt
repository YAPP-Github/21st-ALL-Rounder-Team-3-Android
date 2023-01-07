package com.yapp.timitimi.kotlin

fun <T> List<T>.copy(mutator: MutableList<T>.() -> Unit) = toMutableList().apply(mutator).toList()
