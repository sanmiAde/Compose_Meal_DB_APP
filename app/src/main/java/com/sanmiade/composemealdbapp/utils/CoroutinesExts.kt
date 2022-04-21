package com.sanmiade.composemealdbapp.utils

import com.sanmiade.composemealdbapp.domain.model.MealModel
import java.util.concurrent.CancellationException

 inline fun <R> runSuspendCatching(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        if (e is CancellationException) {
            throw e
        }
        Result.failure(e)
    }
}