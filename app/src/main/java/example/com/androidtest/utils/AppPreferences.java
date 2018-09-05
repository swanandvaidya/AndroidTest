package example.com.androidtest.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Class which responsible for storing the primitive data to {@link android.content.SharedPreferences}
 * All the app local data which wants to store into app {@link android.content.SharedPreferences}
 */
public class AppPreferences {

    public final static String PREF_NAME = "app_pref";
    private Context context;
    private static SharedPreferences sharedPref;
    private static AppPreferences appPreferences;

    private AppPreferences(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Used to get the {@link String} value from {@link SharedPreferences}
     *
     * @param key - {@link String} key
     * @return - {@link String} value
     */
    public String getPrefString(String key) {
        return sharedPref.getString(key, "");
    }

    /**
     * Use this to set the value for a particular key into {@link SharedPreferences}
     *
     * @param key   - {@link String} key
     * @param value - {@link String} value
     */
    public void putPrefString(String key, String value) {
        sharedPref.edit().putString(key, value).apply();
    }


    /**
     * Used to get the int value from {@link SharedPreferences}
     *
     * @param key - {@link String} key
     * @return - int value
     */
    public int getPrefInt(String key) {
        return sharedPref.getInt("key", 0);
    }

    /**
     * Use this to set the int value for a particular key into {@link SharedPreferences}
     *
     * @param key   - {@link String} key
     * @param value - int value
     */
    public void putPrefString(String key, int value) {
        sharedPref.edit().putInt(key, value).apply();
    }

    /**
     * Used to get the boolean value from {@link SharedPreferences}
     *
     * @param key - {@link String} key
     * @return - boolean value
     */
    public boolean getPrefBoolean(String key) {
        return sharedPref.getBoolean("key", false);
    }

    /**
     * Use this to set the boolean value for a particular key into {@link SharedPreferences}
     *
     * @param key   - {@link String} key
     * @param value - boolean value
     */
    public void putPrefBoolean(String key, boolean value) {
        sharedPref.edit().putBoolean(key, value).apply();
    }

    public static AppPreferences getInstance(Context context) {
        if (appPreferences == null) {
            appPreferences = new AppPreferences(context);
        }
        return appPreferences;
    }
}
