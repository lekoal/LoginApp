package com.example.loginapp.utils

private typealias Subscriber<T> = (T) -> Unit

class LoginPublisher<T> {

    private val subscribers: MutableSet<Subscriber<T>> = mutableSetOf()
    private var lastValue: T? = null

    fun subscribe(
        subscriber: Subscriber<T>
    ) {
        subscribers.add(subscriber)
        lastValue?.let {
            subscriber.invoke(it)
        }
    }

    fun unsubscribe(
        subscriber: Subscriber<T>
    ) {
        subscribers.remove(subscriber)
    }

    fun unsubscribeAll() {
        subscribers.clear()
    }

    fun post(
        value: T
    ) {
        this.lastValue = value
        subscribers.forEach { it.invoke(value) }
    }
}