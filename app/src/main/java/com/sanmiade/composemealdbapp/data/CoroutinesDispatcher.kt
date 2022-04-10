package com.sanmiade.composemealdbapp.data

import com.sanmiade.composemealdbapp.domain.AsyncDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CoroutinesDispatcher @Inject constructor() : AsyncDispatcher {
    override val main: CoroutineDispatcher get() = Dispatchers.Main
    override val io: CoroutineDispatcher get() = Dispatchers.IO
    override val default: CoroutineDispatcher get() = Dispatchers.Default
}