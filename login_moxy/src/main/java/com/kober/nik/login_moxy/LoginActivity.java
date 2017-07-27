package com.kober.nik.login_moxy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends MvpAppCompatActivity implements LoginView {

    public static final String TAG = "LoginActivity";

    @BindView(R.id.email)
    AutoCompleteTextView emailView;
    @BindView(R.id.password)
    EditText passwordView;
    @BindView(R.id.login_progress)
    View progressView;
    @BindView(R.id.login_form)
    View loginFormView;
    @BindView(R.id.email_sign_in_button)
    View emailSignInButton;

    @InjectPresenter
    LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        emailSignInButton.setOnClickListener(view -> presenter.signInInitiated(
                emailView.getText().toString(), passwordView.getText().toString()));
    }

    @Override
    public void showProgress(boolean show) {
        Log.d(TAG, "showProgress ? -> " + show);
        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        Log.d(TAG, "visible ? -> " + (progressView.getVisibility() == View.VISIBLE));
    }

    @Override
    public void showError() {
        Log.d(TAG, "showError");
        passwordView.setError(getString(R.string.error_incorrect_password));
        passwordView.requestFocus();
    }

    @Override
    public void openHomeScreen() {
        Log.d(TAG, "openHomeScreen");
        startActivity(new Intent(this, HomeActivity.class));
    }
}