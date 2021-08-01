package com.example.mavenirsdkdemo.config;

import android.content.Context;

import com.example.mavenirsdkdemo.utils.FileUtils;

import java.io.File;

public class AppConfig {
    private static AppConfig mInstance;
    private static final String TAG = AppConfig.class.getSimpleName();
    private Context context;

    public static synchronized AppConfig getInstance(){
        if(mInstance == null){
            mInstance = new AppConfig();
        }
        return mInstance;
    }

    private AppConfig(){}

    public void initApp(Context context){
        this.context = context;
        FileUtils.createLogFolder(context);
    }


    public File getLogFolder(){
        return FileUtils.getLogFolder(context);
    }

}
