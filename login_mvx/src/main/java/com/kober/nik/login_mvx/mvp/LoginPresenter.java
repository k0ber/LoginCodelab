package com.kober.nik.login_mvx.mvp;


import com.kober.nik.login_mvx.LoginApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class LoginPresenter {

    private final LoginView loginView;
    private final LoginApi loginApi;
    private Disposable disposable;

    LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginApi = new LoginApi();
    }

    void signInInitiated(String email, String password) {
        loginView.showProgress(true);
        disposable = loginApi.checkToken(email, password)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (token) -> {
                            loginView.showProgress(false);
                            loginView.openHomeScreen();
                        },
                        (error) -> {
                            loginView.showProgress(false);
                            loginView.showError();
                        });
    }

    void onDestroy() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

}
