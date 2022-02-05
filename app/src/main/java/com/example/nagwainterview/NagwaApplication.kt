package com.example.nagwainterview

import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.nagwainterview.di.ApplicationComponent
import com.example.nagwainterview.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class NagwaApplication : Application(), HasActivityInjector {
    var androidInjector: DispatchingAndroidInjector<Activity>? = null
        @Inject set

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        applicationComponent = DaggerApplicationComponent.create()
        applicationComponent?.inject(this)
        instance = this
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector!!
    }

    companion object {
        var appContext: Context? = null
            private set

        @get:Synchronized
        var instance: NagwaApplication? = null
            private set

        var applicationComponent: ApplicationComponent? = null
            private set
    }
}