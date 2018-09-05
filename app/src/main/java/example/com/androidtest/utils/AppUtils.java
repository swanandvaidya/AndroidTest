package example.com.androidtest.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;


/**
 * This class used for the common functionality through out your app
 * Add the common methods which are used in the classes.
 *
 * @author SwanandVaidya
 * @version 1.0
 * @since 29 August 2018
 */
public class AppUtils {

    /**
     * Method returns the unique device id
     * @return - unique device id
     * */
    public static final String getAndroidDeviceId(Context context){
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    /** Used to check the internet is available and is connected or not
     *  returns true if available and connected
     *  return false if not
     *  @param con context of the activity
     *  */
    public static final boolean isNetworkAvailable(Context con){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /** Return the subscription key
     * @return - subscription key */
    public static String getSubscriptionKey() {

        return AppConstants.SUBSCRIPTION_KEY;
    }
}
