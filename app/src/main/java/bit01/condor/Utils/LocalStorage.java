package bit01.condor.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/*
 * Created by miguelost on 25-01-15.
 */

public class LocalStorage {
    private static final String _SHARED_PREFERENCE_FILE = "cl.bit01.condor/LocalStorage";
    private static SharedPreferences _SHARED_PREFERENCE;
    private static SharedPreferences.Editor _PREFERENCE_EDITOR;

    private static String KEY_USER_REGISTERED = "user_registered";

    public static void initLocalStorage(Context context) {
        _SHARED_PREFERENCE = context.getSharedPreferences(_SHARED_PREFERENCE_FILE, Activity.MODE_PRIVATE);
    }

    public static Boolean isUserRegistered() {
        return _SHARED_PREFERENCE.getBoolean(KEY_USER_REGISTERED, false);
    }

    public static void setUserRegistered() {
        _PREFERENCE_EDITOR = _SHARED_PREFERENCE.edit();
        _PREFERENCE_EDITOR.putBoolean(KEY_USER_REGISTERED, true);
        _PREFERENCE_EDITOR.apply();
    }
}
