package com.zarinraim.dogsearch

import android.app.Application
import com.zarinraim.dogsearch.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DogSearchApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@DogSearchApp)
            modules(appModule)
        }
    }
}
