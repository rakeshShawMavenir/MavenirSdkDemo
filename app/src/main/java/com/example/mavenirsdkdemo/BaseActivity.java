package com.example.mavenirsdkdemo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mavenirsdkdemo.config.CrashReportHandler;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class BaseActivity extends AppCompatActivity {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private io.reactivex.disposables.CompositeDisposable permissionDisposable = new io.reactivex.disposables.CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new CrashReportHandler());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    public void addToCompositeDisposable(Disposable disposable){
        if(compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void addToCompositeDisposable(io.reactivex.disposables.Disposable disposable){
        if(permissionDisposable == null){
            permissionDisposable = new io.reactivex.disposables.CompositeDisposable();
        }
        permissionDisposable.add(disposable);
    }

    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int msg){
        Toast.makeText(this, getResources().getString(msg), Toast.LENGTH_SHORT).show();
    }

}
