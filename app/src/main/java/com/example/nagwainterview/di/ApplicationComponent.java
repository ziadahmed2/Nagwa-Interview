package com.example.nagwainterview.di;

import com.example.nagwainterview.NagwaApplication;
import com.example.nagwainterview.di.main.MainComponent;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, NetworkModule.class, SubComponentsModule.class})
public interface ApplicationComponent {
    void inject(NagwaApplication application);

    MainComponent.Builder mainComponent();
}
