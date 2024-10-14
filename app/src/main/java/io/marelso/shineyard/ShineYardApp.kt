package io.marelso.shineyard

import android.app.Application
import com.google.firebase.FirebaseApp
import io.marelso.shineyard.data.Session
import io.marelso.shineyard.di.shineYardModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class ShineYardApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Session.init(this)
        GlobalContext.startKoin {
            androidContext(this@ShineYardApp)
            modules(shineYardModule)
        }
    }
}