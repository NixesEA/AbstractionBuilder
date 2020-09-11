package com.nixesea.facebookdeeplink

import android.util.Log
import com.nixesea.abstractbuilder.AbsBuilder
import java.lang.Math.random
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CheckerFacebook : AbsBuilder() {
    override suspend fun doSomething(): Boolean {
        return suspendCoroutine {
            it.resume(true)
        }
    }

    override suspend fun logSomething(): Int {
        return suspendCoroutine<Int> { continuation ->
            val randomA = (random() * 10).toInt().also {
                Log.e(this::class.simpleName, "logSomething:generate randomA:${it}")
            }
            Thread.sleep(1000L).also {
                Log.e(this::class.simpleName, "logSomething:sleep")
            }

            val randomB = (random() * 1000).toInt().also {
                Log.e(this::class.simpleName, "logSomething:generate randomB:${it}")
            }
            Thread.sleep(1000L).also {
                Log.e(this::class.simpleName, "logSomething:sleep")
            }

            val result = (randomA * randomB).also {
                Log.e(this::class.simpleName, "logSomething:compute result:${it}")
            }
            continuation.resume(result)
        }
    }

    override suspend fun logSomething(message: String): Double {
        return suspendCoroutine<Double> { continuation ->
            val randomA = (random() * -10).also {
                Log.e(this::class.simpleName, "logSomething:generate randomA:${it}")
            }
            Thread.sleep(1000L).also {
                Log.e(this::class.simpleName, "logSomething:sleep")
            }

            val randomB = (random() * -1000).also {
                Log.e(this::class.simpleName, "logSomething:generate randomB:${it}")
            }
            Thread.sleep(1000L).also {
                Log.e(this::class.simpleName, "logSomething:sleep")
            }

            val result = (randomA * randomB).also {
                Log.e(this::class.simpleName, "logSomething:compute result:${it}")
            }
            continuation.resume(result)
        }
    }
}