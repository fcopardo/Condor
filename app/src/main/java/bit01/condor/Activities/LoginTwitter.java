package bit01.condor.Activities;
/*
 * Created by miguelost on 25-01-15.
 */

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;

import bit01.condor.R;
import twitter4j.Twitter;
import twitter4j.auth.RequestToken;


public class LoginTwitter extends Activity {

    static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
    static final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
    static final String TWITTER_CALLBACK_URL = "oauth://bit01.bitbucket.org";
    // / Twitter oauth urls
    static final String URL_TWITTER_AUTH = "auth_url";
    static final String URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier";
    static final String URL_TWITTER_OAUTH_TOKEN = "oauth_token";
    /**
     * Register your here app https://dev.twitter.com/apps/new and get your
     * consumer key and secret
     * To load this, put in raw folder a file called "twitter_keys.txt" and write the
     * keys in this format:
     * CONSUMER_KEY:CONSUMER_SECRET
     */
    static String TWITTER_CONSUMER_KEY = "";
    static String TWITTER_CONSUMER_SECRET = "";
    private static Twitter twitter;
    private static RequestToken requestToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        readTwitterKeys();
    }

    private void readTwitterKeys() {
        try {
            Resources res = getResources();
            InputStream inputStream = res.openRawResource(R.raw.twitter_keys);

            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            String[] twitterKeys = new String(b).split(":");
            TWITTER_CONSUMER_KEY = twitterKeys[0];
            TWITTER_CONSUMER_SECRET = twitterKeys[1];
            Log.d("Twitter Key", "Consumer Key: " + TWITTER_CONSUMER_KEY);
            Log.d("Twitter Key", "Consumer Secret: " + TWITTER_CONSUMER_SECRET);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("LoginTwitter Error", "Cant get Twitter Keys");
        }
    }
}
