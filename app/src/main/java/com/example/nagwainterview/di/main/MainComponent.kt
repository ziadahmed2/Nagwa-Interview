package com.example.nagwainterview.di.main

import com.example.nagwainterview.view.main.MainActivity
import dagger.Subcomponent

@Subcomponent
@MainScope
interface MainComponent {

    @Subcomponent.Builder
    interface Builder {
        fun create(): MainComponent?
    }

    fun inject(activity: MainActivity?)
}