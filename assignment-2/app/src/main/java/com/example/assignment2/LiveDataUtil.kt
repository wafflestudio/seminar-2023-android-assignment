package com.example.assignment2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <A, B> combine(liveItem1: LiveData<A>, liveItem2: LiveData<B>): LiveData<Pair<A, B>> {
    val mergedData = MediatorLiveData<Pair<A, B>>()

    mergedData.addSource(liveItem1) { item1 ->
        val item2 = liveItem2.value ?: return@addSource
        mergedData.value = Pair(item1, item2)
    }

    mergedData.addSource(liveItem2) { item2 ->
        val item1 = liveItem1.value ?: return@addSource
        mergedData.value = Pair(item1, item2)
    }

    return mergedData
}
