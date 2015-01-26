package bit01.condor.Activities;
/*
 * Created by miguelost on 25-01-15.
 */

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.io.InputStream;

import bit01.condor.R;
import bit01.condor.Utils.LocalStorage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class LoginTwitter extends Activity {

    Twitter twitter;
    RequestToken requestToken = null;
    String oauth_url;
    String oauth_verifier;
    AccessToken accessToken;

    private Dialog auth_dialog;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LocalStorage.initLocalStorage(this);
        readTwitterKeys();

        twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(LocalStorage.getConsumerKey(), LocalStorage.getConsumerSecret());

        Button buttonLoginTwitter = (Button) findViewById(R.id.button_login_twitter);
        buttonLoginTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginProcess();
            }
        });

    }

    private void LoginProcess() {
        new TokenGet().execute();
    }

    private void readTwitterKeys() {
        try {
            Resources res = getResources();
            InputStream inputStream = res.openRawResource(R.raw.twitter_keys);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            String[] twitterKeys = new String(b).split(":");

            LocalStorage.setConsumerKey(twitterKeys[0]);
            LocalStorage.setConsumerSecret(twitterKeys[1]);

            Log.d("Twitter Key", "Consumer Key: " + LocalStorage.getConsumerKey());
            Log.d("Twitter Key", "Consumer Secret: " + LocalStorage.getConsumerSecret());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("LoginTwitter Error", "Cant get Twitter Keys");
        }
    }

    private class TokenGet extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                requestToken = twitter.getOAuthRequestToken();
                oauth_url = requestToken.getAuthorizationURL();
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            return oauth_url;
        }

        @Override
        protected void onPostExecute(String oauth_url) {
            if (oauth_url != null) {
                Log.d("URL Twitter", oauth_url);
                auth_dialog = new Dialog(LoginTwitter.this);
                auth_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                auth_dialog.setContentView(R.layout.login_webview);
                WebView web = (WebView) auth_dialog.findViewById(R.id.webview_LoginTwitter);
                web.loadUrl(oauth_url);
                web.setWebViewClient(new WebViewClient() {
                    boolean authComplete = false;

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        if (url.contains("oauth_verifier") && !authComplete) {
                            authComplete = true;
                            Log.e("Url", url);
                            Uri uri = Uri.parse(url);
                            oauth_verifier = uri.getQueryParameter("oauth_verifier");
                            auth_dialog.dismiss();
                            new AccessTokenGet().execute();
                        } else if (url.contains("denied")) {
                            auth_dialog.dismiss();
                            Toast.makeText(LoginTwitter.this, getString(R.string.access_denied), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                auth_dialog.show();
                auth_dialog.setCancelable(true);
            } else {
                Toast.makeText(LoginTwitter.this, getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class AccessTokenGet extends AsyncTask<String, String, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(LoginTwitter.this);
            progress.setMessage(getString(R.string.fetch_data));
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.show();
        }

        @Override
        protected Boolean doInBackground(String... args) {
            try {
                accessToken = twitter.getOAuthAccessToken(requestToken, oauth_verifier);
                LocalStorage.setOauthToken(accessToken.getToken());
                LocalStorage.setOauthSecret(accessToken.getTokenSecret());

                User user = twitter.showUser(accessToken.getUserId());
                Log.d("Condor", "Twitter user: " + user.getName() + " logued sucess");
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean response) {
            if (response) {
                progress.hide();
                LocalStorage.setLoggedInTwitter();
            }
        }
    }
}
