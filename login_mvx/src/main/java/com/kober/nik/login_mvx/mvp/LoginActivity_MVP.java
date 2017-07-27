package com.kober.nik.login_mvx.mvp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.kober.nik.login_mvx.HomeActivity;
import com.kober.nik.login_mvx.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity_MVP extends AppCompatActivity implements LoginView {

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

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this);

        ButterKnife.bind(this);
        emailSignInButton.setOnClickListener(view -> loginPresenter.signInInitiated(
                emailView.getText().toString(), passwordView.getText().toString()));
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress(boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        loginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        progressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void showError() {
        showProgress(false);
        passwordView.setError(getString(R.string.error_incorrect_password));
        passwordView.requestFocus();
    }

    @Override
    public void openHomeScreen() {
        showProgress(false);
        startActivity(new Intent(this, HomeActivity.class));
    }

}

