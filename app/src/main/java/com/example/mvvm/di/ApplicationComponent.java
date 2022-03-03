package com.example.mvvm.di;

import com.example.mvvm.BaseMvvmApplication;
import com.example.mvvm.di.home.HomeComponent;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, NetworkModule.class, SubComponentsModule.class})
public interface ApplicationComponent {
    void inject(BaseMvvmApplication application);

    HomeComponent.Builder homeComponent();
}
