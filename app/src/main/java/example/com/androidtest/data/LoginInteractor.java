package example.com.androidtest.data;

import android.content.Context;

import example.com.androidtest.pojo.NewUser;
import example.com.androidtest.rest.WarehouseAPI;
import example.com.androidtest.rest.WarehouseService;
import example.com.androidtest.utils.AppConstants;
import example.com.androidtest.utils.AppPreferences;
import example.com.androidtest.utils.AppUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author SwanandVaidya
 * @version 1.0.0
 * @since 29 August 2018
 *
 * This class will act as a model and handles all business logic for login
 * Process the data from the API and pass to presenter
 * */

public class LoginInteractor {

    private WarehouseAPI warehouseAPI;
    private OnLoginSuccessfulListener listener;
    private Context context;

    // CTOR
    public LoginInteractor(Context context,OnLoginSuccessfulListener listener){
        this.listener = listener;
        this.context = context;
        warehouseAPI =  WarehouseService.getClient().create(WarehouseAPI.class);
    }

    /* perform login using the API */
    public void login(final OnLoginSuccessfulListener listener) {
        Call<NewUser> call =  warehouseAPI.getNewUser(AppUtils.getSubscriptionKey());
        call.enqueue(new Callback<NewUser>() {
            @Override
            public void onResponse(Call<NewUser> call, Response<NewUser> response) {
                NewUser user = response.body();
                // store user id as it needed for further use
                // currently just storing into shared preferences
                AppPreferences.getInstance(context).putPrefString(AppConstants.PREF_USER_ID,user.getUserID());
                listener.onSuccessOfLogin(user);
            }

            @Override
            public void onFailure(Call<NewUser> call, Throwable t) {
                listener.onFailOfLogin(t);
            }
        });
    }

    public interface OnLoginSuccessfulListener {
        void onSuccessOfLogin(NewUser user);
        void onFailOfLogin(Throwable t);
    }
}
