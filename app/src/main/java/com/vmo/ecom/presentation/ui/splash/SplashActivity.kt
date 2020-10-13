package com.vmo.ecom.presentation.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vmo.ecom.presentation.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch {
            delay(1000)
            startActivity(MainActivity.createIntent(this@SplashActivity))
            finish()
        }
    }
}