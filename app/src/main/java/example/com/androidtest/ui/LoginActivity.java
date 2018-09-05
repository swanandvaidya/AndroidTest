package example.com.androidtest.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import example.com.androidtest.R;
import example.com.androidtest.presenter.LoginPresenterImpl;
import example.com.androidtest.view.LoginView;


/**
 * @author SwanandVaidya
 * @version 1.0.0
 * @since 29 August 2018
 *
 * The login activity used to create new user from the api
 * Currently used hardcoded values
 * This Project uses the mvp architecture with an effective way
 * */

public class LoginActivity extends AppCompatActivity implements LoginView {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private LoginPresenterImpl loginPresenter;
    private AppCompatEditText etUsername;
    private AppCompatEditText etPassword;
    private AppCompatButton btnLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // init all views with their ids
        initElementsWithIds();

        // initialize the progress bar
        initProgressBar();

        // handle all click events
        initElementsWithListeners();

        // init the login presenter
        initLoginPresenter();

        // set user credentials temporary
        setUserCredentials();
    }

    private void initProgressBar() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    private void setUserCredentials() {
        etUsername.setText("TestUser");
        etPassword.setText("Test@1234");
    }

    // initializes the login presenter with context and view
    private void initLoginPresenter() {
        // initialize the presenter with context of the current activity and the view
        loginPresenter = new LoginPresenterImpl(this,this);
    }

    // handle all on click events
    private void initElementsWithListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check credentials i.e username and password first from the server or any other mechanism
                // currently we are keeping as hard coded for testing purpose
                // if username and password are correct then do login
                // else show respective errors
                loginPresenter.doLogin();
            }
        });
    }

    // initialize the views
    private void initElementsWithIds() {
        etUsername =  findViewById(R.id.et_username);
        etPassword =  findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
    }

    /**
     *  When there is no internet show appropriate toast,message or dialog to user
     *  perform appropriate action
     * */
    @Override
    public void showNoInternetError() {
        Toast.makeText(this, getString(R.string.error_no_internet),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        startActivity(new Intent(LoginActivity.this,SearchActivity.class));
    }

    @Override
    public void onError() {
        // show something if api got failed or some error occurs
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
