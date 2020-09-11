package com.nixesea.abstractbuilder

abstract class AbsBuilder {
    abstract suspend fun doSomething(): Boolean
    abstract suspend fun logSomething(): Int
    abstract suspend fun logSomething(message: String): Double
}