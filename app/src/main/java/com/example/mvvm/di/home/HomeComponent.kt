package com.example.mvvm.di.home

import com.example.mvvm.view.home.HomeActivity
import dagger.Subcomponent

@Subcomponent
@HomeScope
interface HomeComponent {

    @Subcomponent.Builder
    interface Builder {
        fun create(): HomeComponent?
    }

    fun inject(activity: HomeActivity?)
}