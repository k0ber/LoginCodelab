package com.kober.nik.login_managers;

import android.text.TextUtils;


class ValidationUtils {

    static boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && email.contains("@");
    }

    static boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password) && password.length() > 4;
    }

}
