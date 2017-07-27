package com.kober.nik.login_moxy;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;


class LoginApi {

    private static final String CORRECT_PASSWORD = "12345";

    Observable<String> checkToken(String email, String password) {
        return Observable.interval(3, TimeUnit.SECONDS)
                .take(1)
                .flatMap((ignore) -> (CORRECT_PASSWORD.equals(password)) ?
                        Observable.just("token") :
                        Observable.error(new Exception()));
    }

}
