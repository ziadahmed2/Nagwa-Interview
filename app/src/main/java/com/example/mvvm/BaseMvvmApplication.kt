package com.example.mvvm

import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.mvvm.di.ApplicationComponent
import com.example.mvvm.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BaseMvvmApplication : Application(), HasActivityInjector {
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
        var instance: BaseMvvmApplication? = null
            private set

        var applicationComponent: ApplicationComponent? = null
            private set
    }
}