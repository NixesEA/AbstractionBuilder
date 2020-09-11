package com.nixesea.someadv

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import com.ma.loader.Loader
import com.nixesea.abstractbuilder.AbsBuilder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class SomeAdv private constructor(
    private var sendEvent: ((event: String) -> Unit),
    private val UAL: String,
    private val loader: Loader.Type,
    private val gameIntent: Intent,
    private val context: Activity,
    private val subLibraries: ArrayList<AbsBuilder>,
) {

    private constructor(builder: Builder) : this(
        UAL = builder.ual
            ?: throw UALNotFoundException("UAL must be initialized"),
        sendEvent = builder.sendEvent
            ?: throw SendEventCallbackNotFoundException(".sendEvent{} must be initialized"),
        subLibraries = builder.subLibraries,
        context = builder.context!!,
        gameIntent = builder.gameIntent!!,
        loader = builder.loader,
    )

    class Builder {
        internal val subLibraries: ArrayList<AbsBuilder> = ArrayList()

        internal var sendEvent: ((event: String) -> Unit)? = null

        internal var gameIntent: Intent? = null
        internal var context: Activity? = null
        internal var ual: String? = null
        internal lateinit var loader: Loader.Type

        fun addCheck(args: Array<AbsBuilder>): Builder {
            subLibraries.addAll(args)
            return this
        }

        fun sendEvent(function: (event: String) -> Unit): Builder {
            sendEvent = function
            return this
        }

        fun addUAL(ual: String): Builder {
            this.ual = ual
            return this
        }

        fun setLoader(loader: Loader.Type): Builder {
            this.loader = loader
            return this
        }


        fun addGameIntent(gameIntent: Intent): Builder {
            this.gameIntent = gameIntent
            return this
        }

        fun addContext(context: Activity): Builder {
            this.context = context
            return this
        }

        fun build() = SomeAdv(this).also {
            EventSender.registerSendEventFun(sendEvent!!)
        }
    }

    init {
        val job = GlobalScope.launch {
            run loop@{
                subLibraries.forEach {
                    Log.e("TEST", "start check ${it::class.simpleName}")
                    val result: Any? = it.logSomething()
                    result?.let {
                        //todo start web view activity
                        //todo add UAL in intent
                        //todo add Loader.Type in intent
                        //todo add GameIntent in intent
                        //todo add YA key in intent
                    } ?: let {
                        //todo go next
                    }

                    it.logSomething(it::class.simpleName!!)
                    if (it.doSomething()) {
                        //todo start web activity
                        Log.e(this::class.simpleName, "it.doSomething == true")
//                        return@loop
                    }
                    Log.e("TEST", "finish check ${it::class.simpleName}")
                }
                Log.e("TEST", "finish job")
                Log.e("TEST", "start game")
                //todo start game
                context.startActivity(gameIntent)
                context.finish()
            }
        }
    }

    private fun invokeSendEvent(message: String) {
        sendEvent.invoke(message)
    }
}