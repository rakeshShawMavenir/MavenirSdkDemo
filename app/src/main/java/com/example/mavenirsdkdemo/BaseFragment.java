package com.example.mavenirsdkdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class BaseFragment extends Fragment {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private io.reactivex.disposables.CompositeDisposable permissionDisposable = new io.reactivex.disposables.CompositeDisposable();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void showToast(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int msg){
        Toast.makeText(getActivity(), getActivity().getResources().getString(msg), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
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

}
