package com.kober.nik.login_mvx.mvvm;

import com.kober.nik.login_mvx.LoginApi;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;


class LoginViewModel {

    private final PublishSubject<ViewState> stateSubject;
    private final LoginApi loginApi;


    LoginViewModel() {
        loginApi = new LoginApi();
        stateSubject = PublishSubject.create();
    }

    void signInInitiated(String email, String password) {
        stateSubject.onNext(ViewState.LOADING);

        loginApi.checkToken(email, password)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (token) -> stateSubject.onNext(ViewState.SUCCESS),
                        (error) -> stateSubject.onNext(ViewState.ERROR));
    }

    Observable<ViewState> observeViewState() {
        return stateSubject;
    }

}
