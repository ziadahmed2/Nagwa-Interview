package com.example.nagwainterview.di;

import com.example.nagwainterview.NagwaApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, NetworkModule.class, SubComponentsModule.class})
public interface ApplicationComponent {
    void inject(NagwaApplication application);
}
