package com.example.pushmessagetestapp.util

fun <T> optimizedLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)