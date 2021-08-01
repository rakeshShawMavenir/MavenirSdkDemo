package com.example.mavenirsdkdemo;

import android.app.Application;

import com.example.mavenirsdkdemo.config.AppConfig;
import com.example.mavenirsdkdemo.config.PreferenceManager;
import com.example.mavenirsdkdemo.config.CrashReportHandler;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MavSdkApplication extends Application {
    Disposable disposable;
    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new CrashReportHandler());
        disposable = Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                AppConfig.getInstance().initApp(getApplicationContext());
                PreferenceManager.initialise(getApplicationContext());
            }
        }).observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe();


    }

    @Override
    public void onTerminate() {
        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
        super.onTerminate();

    }
}
