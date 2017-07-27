package com.kober.nik.login_moxy;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


interface LoginView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress(boolean show);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showError();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openHomeScreen();

}
