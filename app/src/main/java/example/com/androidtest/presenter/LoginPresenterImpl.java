package example.com.androidtest.presenter;

import android.content.Context;

import example.com.androidtest.data.LoginInteractor;
import example.com.androidtest.pojo.NewUser;
import example.com.androidtest.view.LoginView;

public class LoginPresenterImpl implements LoginInteractor.OnLoginSuccessfulListener{

    private final LoginInteractor loginInteractor; // this will act as model
    private LoginView loginView; // this will be the view
    private Context context;

    public LoginPresenterImpl(Context context,LoginView view){
        this.context = context;
        this.loginView = view;
        loginInteractor = new LoginInteractor(context,this);
    }

    public void doLogin(){
        if(loginView != null)
            loginView.showProgress();
        loginInteractor.login(this);
    }

    @Override
    public void onSuccessOfLogin(NewUser user) {
        if(loginView != null){
            loginView.hideProgress();
            loginView.onSuccess();
        }
    }

    @Override
    public void onFailOfLogin(Throwable t) {
        if(loginView != null){
            loginView.hideProgress();
            loginView.onError();
        }
    }
}
