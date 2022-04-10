package com.sanmiade.composemealdbapp.domain

import kotlinx.coroutines.CoroutineDispatcher

public interface AsyncDispatcher {
    public val main: CoroutineDispatcher
    public val io: CoroutineDispatcher
    public val default: CoroutineDispatcher
}