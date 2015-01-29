package bit01.condor.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;

import bit01.condor.Utils.LocalStorage;
import io.fabric.sdk.android.Fabric;


public class LaunchApp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        LocalStorage.initLocalStorage(this);

        if (LocalStorage.isLoggedInTwitter()) {
            Intent launchMainApplication = new Intent(LaunchApp.this, Condor.class);
            LaunchApp.this.startActivity(launchMainApplication);
            finish();
        } else {
            Intent launchLoginTwitter = new Intent(LaunchApp.this, LoginTwitter.class);
            LaunchApp.this.startActivity(launchLoginTwitter);
            finish();
        }
    }


}
