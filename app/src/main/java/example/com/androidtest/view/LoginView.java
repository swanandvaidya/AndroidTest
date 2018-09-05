package example.com.androidtest.view;


/**
 * declaration of all login related UI functions
 *
 * @author SwanandVaidya
 * @version 1.0
 * @since 29 August 2018
 */

public interface LoginView {

    void showNoInternetError();

    void onSuccess();

    void onError();

    void showProgress();
    void hideProgress();
}
