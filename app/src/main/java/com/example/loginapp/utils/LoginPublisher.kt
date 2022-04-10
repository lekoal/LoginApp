package com.example.loginapp.utils

import android.os.Handler

class Subscriber<T>(
    private val handler: Handler,
    private val callback: (T?) -> Unit
) {
    fun invoke(value: T?) {
        handler.post {
            callback.invoke(value)
        }
    }
}

class LoginPublisher<T>(private val isSingle: Boolean = false) {

    private val subscribers: MutableSet<Subscriber<T?>> = mutableSetOf()
    private var lastValue: T? = null
    private var hasFirstValue = false

    fun subscribe(
        handler: Handler,
        callback: (T?) -> Unit
    ) {
        val subscriber = Subscriber(handler, callback)
        subscribers.add(subscriber)
        if (hasFirstValue) {
            handler.post {
                subscriber.invoke(lastValue)
            }
        }
    }

    fun unsubscribe(
        handler: Handler,
        callback: (T?) -> Unit
    ) {
        val subscriber = Subscriber(handler, callback)
        subscribers.remove(subscriber)
    }

    fun unsubscribeAll() {
        subscribers.clear()
    }

    fun post(
        value: T
    ) {
        if (!isSingle) {
            hasFirstValue = true
            this.lastValue = value
        }
        subscribers.forEach {
            it.invoke(value)
        }
    }
}