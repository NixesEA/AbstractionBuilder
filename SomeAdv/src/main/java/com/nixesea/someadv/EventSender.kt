package com.nixesea.someadv

object EventSender {

    lateinit var callback: ((event: String) -> Unit)

    fun registerSendEventFun(sendEvent: ((event: String) -> Unit)) {
        callback = sendEvent
    }

    fun sendMessage(message: String) {
        callback.invoke(message)
    }

}