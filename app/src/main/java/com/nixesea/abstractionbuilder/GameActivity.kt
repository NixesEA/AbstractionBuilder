package com.nixesea.abstractionbuilder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nixesea.someadv.EventSender
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        send_message.setOnClickListener {
            EventSender.sendMessage(users_message.text.toString())
        }
    }
}