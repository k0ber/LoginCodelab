package com.kober.nik.login_managers;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


class LoginManager {

    private static final String CORRECT_PASSWORD = "12345";

    private final Action successLoginAction;
    private final Action failedLoginAction;


    LoginManager(Action successLoginAction, Action failedLoginAction) {
        this.successLoginAction = successLoginAction;
        this.failedLoginAction = failedLoginAction;
    }

    void tryToLogin(String email, String password) {
        Observable.interval(3, TimeUnit.SECONDS)
                .take(1)
                .flatMap((ignore) -> (CORRECT_PASSWORD.equals(password)) ?
                        Observable.just("token") :
                        Observable.error(new Exception()))

                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (ignore) -> {
                            Log.w("NIX", "success!");
                            successLoginAction.run();
                        },
                        (error) -> failedLoginAction.run());
    }

}
