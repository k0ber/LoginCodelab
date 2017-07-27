package com.kober.nik.login_moxy;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    private final LoginApi loginApi;

    LoginPresenter() {
        loginApi = new LoginApi();
    }

    void signInInitiated(String email, String password) {
        getViewState().showProgress(true);
        loginApi.checkToken(email, password)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (token) -> {
                            getViewState().showProgress(false);
                            getViewState().openHomeScreen();
                        },
                        (error) -> {
                            getViewState().showProgress(false);
                            getViewState().showError();
                        });
    }

}
