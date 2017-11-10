package com.cosmo.arquitecturamvpbase.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.model.CustomSharedPreferences;
import com.cosmo.arquitecturamvpbase.model.User;
import com.cosmo.arquitecturamvpbase.presenter.LoginPresenter;
import com.cosmo.arquitecturamvpbase.repository.ProductRepository;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView, TextWatcher{

    private TextView login_etUser;
    private TextView login_etPassword;
    private Button login_btnLogin;
    private CustomSharedPreferences customSharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new LoginPresenter(new ProductRepository()));
        getPresenter().inject(this, getValidateInternet());
        customSharedPreferences =  new CustomSharedPreferences(this);
        setContentView(R.layout.activity_login);
        createProgressDialog();
        loadViews();
        verifyAutoLogin();
    }

    private void verifyAutoLogin() {
        if(customSharedPreferences.getObjectUser(Constants.USER) != null){
            User user = customSharedPreferences.getObjectUser(Constants.USER);
            getPresenter().autoLogin(user.getToken());
        }
    }

    private void loadViews() {
        login_etUser = (TextView) findViewById(R.id.login_etUser);
        login_etUser.addTextChangedListener(this);
        login_etPassword = (TextView) findViewById(R.id.login_etPassword);
        login_etPassword.addTextChangedListener(this);
        login_btnLogin = (Button) findViewById(R.id.login_btnLogin);
        login_btnLogin.addTextChangedListener(this);
    }

    public void login (View view){

        getPresenter().login(login_etUser.getText().toString().trim(),
                login_etPassword.getText().toString().trim());

    }

    @Override
    public void showDashBoard(User userLogin) {
        customSharedPreferences.saveObjectUser(Constants.USER, userLogin);
        Intent intent = new Intent(this, DashBoardActivity.class);
        startActivity(intent);
    }

    @Override
    public void showDashBoard() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        startActivity(intent);
    }

    @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if(login_etUser.getText().toString().trim() != Constants.EMPTY
                    && login_etPassword.getText().toString().trim() != Constants.EMPTY){
                login_btnLogin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                login_btnLogin.setEnabled(true);
            }else{
                login_btnLogin.setBackgroundColor(getResources().getColor(R.color.colorGray));
                login_btnLogin.setEnabled(false);
            }

        }

}

