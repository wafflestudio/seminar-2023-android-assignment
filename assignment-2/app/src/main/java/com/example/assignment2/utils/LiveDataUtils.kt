package com.example.assignment2.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T, R> combine(liveData1: LiveData<T>, liveData2: LiveData<R>): LiveData<Pair<T, R>> {
    return MediatorLiveData<Pair<T, R>>().apply {
        addSource(liveData1) {
            value = Pair(it, liveData2.value ?: return@addSource)
        }
        addSource(liveData2) {
            value = Pair(liveData1.value ?: return@addSource, it)
        }
    }
}