package bit01.condor.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import bit01.condor.Utils.LocalStorage;


public class Condor extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalStorage.initLocalStorage(this);

        if (LocalStorage.isLoggedInTwitter()) {
            Intent launchMainApplication = new Intent(Condor.this, MainApplication.class);
            Condor.this.startActivity(launchMainApplication);
            finish();
        } else {
            Intent launchLoginTwitter = new Intent(Condor.this, LoginTwitter.class);
            Condor.this.startActivity(launchLoginTwitter);
            finish();
        }
    }


}
