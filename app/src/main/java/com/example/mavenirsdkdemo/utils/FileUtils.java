package com.example.mavenirsdkdemo.utils;

import android.content.Context;
import android.content.ContextWrapper;

import com.example.mavenirsdkdemo.logger.LogUtils;

import java.io.File;

public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();
    private static final String logsFoldername = "logs";


    public static void createLogFolder(Context context){
        ContextWrapper contextWrapper = new ContextWrapper(context);
        File myDir = new File(contextWrapper.getFilesDir(), logsFoldername);
        if(myDir.exists()){
            LogUtils.d(TAG, "creating Internal Log Folder == already exists");
            return;
        }
        boolean isCreated = myDir.mkdir();
        LogUtils.d(TAG, "creating Internal Log Folder == " + isCreated);
    }

    public static File getLogFolder(Context context){
        ContextWrapper contextWrapper = new ContextWrapper(context);
        return new File(contextWrapper.getFilesDir(), logsFoldername);
    }

}
