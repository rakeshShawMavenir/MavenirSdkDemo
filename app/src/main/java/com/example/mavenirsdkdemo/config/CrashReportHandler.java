package com.example.mavenirsdkdemo.config;

import androidx.annotation.NonNull;

import com.example.mavenirsdkdemo.logger.LogUtils;

public class CrashReportHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        printToLogs(thread, throwable);
        System.exit(1);
    }

    private void printToLogs(Thread t, Throwable e){
        LogUtils.dumpCrashReport(t, e);
    }
}
