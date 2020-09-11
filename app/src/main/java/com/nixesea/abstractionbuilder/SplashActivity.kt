package com.nixesea.abstractionbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ma.loader.Loader
import com.nixesea.facebookdeeplink.CheckerFacebook
import com.nixesea.gdpr.CheckerGDPR
import com.nixesea.someadv.SomeAdv

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SomeAdv.Builder()
            .addContext(this)
            .addGameIntent(getGameIntent())
            .addUAL("Some ual")
            .setLoader(Loader.Type.CARDS)
            .sendEvent {
                Log.e(this::class.simpleName, "send event: $it")
            }
            .addCheck(
                arrayOf(
                    CheckerGDPR(),
                    CheckerFacebook(),
                )
            )
            .build()
    }

    private fun getGameIntent() = Intent(this, GameActivity::class.java)
}