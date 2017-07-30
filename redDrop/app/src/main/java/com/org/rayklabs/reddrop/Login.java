package com.org.rayklabs.reddrop;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.org.rayklabs.reddrop.Helper.SQLiteHandler;
import com.org.rayklabs.reddrop.Helper.SessionManager;
import com.org.rayklabs.reddrop.application.Controller;
import com.org.rayklabs.reddrop.config.AppConfig;
import com.twitter.sdk.android.Twitter;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Login  extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private final String TAG = getClass().getSimpleName();
    private static final int RC_SIGN_IN = 007;

    public static int APP_REQUEST_CODE = 99;


    private RelativeLayout GoogleSignIn;
    private GoogleApiClient mGoogleApiClient;

    private CallbackManager callbackManager;
    private RelativeLayout FacebookSignIn;
    private RelativeLayout TwitterSignIn;
    private static twitter4j.Twitter twitter;
    GoogleSignInAccount acct;

    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    private Controller mController;

    protected static final String AUTHENTICATION_URL_KEY = "AUTHENTICATION_URL_KEY";
    protected static final int LOGIN_TO_TWITTER_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeVariable();
        initView();
        checkSession();
    }
    private void initializeVariable() {
        mController = (Controller) getApplicationContext();
    }

    private void initView() {

        //RelativeView
        GoogleSignIn = (RelativeLayout) findViewById(R.id.google_signin);
        FacebookSignIn = (RelativeLayout) findViewById(R.id.facebook_signin);
        TwitterSignIn = (RelativeLayout) findViewById(R.id.twitter_signin);



        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());


        // handling button click events

        GoogleSignIn.setOnClickListener(this);
        FacebookSignIn.setOnClickListener(this);

        TwitterSignIn.setOnClickListener(this);

        // Google Login code starts here...
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(this.getResources().getString(R.string.google_server_client_id))
                .requestEmail()
                .requestId()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .addScope(new Scope(Scopes.EMAIL))
                .build();

        mGoogleApiClient.connect();
        mController.setmGoogleApiClient(mGoogleApiClient);




        // Facebook Login code starts here...
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));

        //setReadPermissions(Arrays.asList("public_profile", "email", "user_friends","user_photos","user_phone"));
        //name
    }

    private void loginToTwitter() {
        GetRequestTokenTask getRequestTokenTask = new GetRequestTokenTask();
        getRequestTokenTask.execute();
    }

    private void checkSession() {
        // Session manager
        session = new SessionManager(getApplicationContext());
        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(Login.this, Main.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.google_signin:
                performGoogleLoginEvent();
                onLoginPhone(v);
                break;

            case R.id.facebook_signin:
                performFacebookLoginEvent();
                onLoginPhone(v);
                break;

            case R.id.twitter_signin:
                loginToTwitter();
                onLoginPhone(v);
                break;

        }
    }

  /*  private void performNormalLoginEvent() {

        String email = input_username.getText().toString().trim();
        String password = input_password.getText().toString().trim();

        // Check for empty data in the form
        if (!email.isEmpty() && !password.isEmpty()) {
            // login user
            checkLogin(email, password);
        } else {
            // Prompt user to enter credentials
            Toast.makeText(getApplicationContext(),
                    "Please enter the credentials!", Toast.LENGTH_LONG)
                    .show();
        }
    }*/

    private void performGoogleLoginEvent() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void performFacebookLoginEvent() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                pDialog.setMessage("Logging in ...");
                showDialog();
                String accessToken = loginResult.getAccessToken().getToken();
                Log.i("accessToken", accessToken);

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        // Get facebook data from login
                        Bundle bFacebookData = getFacebookData(object);

                        String lastname;
                        String firstname = bFacebookData.getString("first_name");
                        lastname = bFacebookData.getString("last_name");
                        String email = bFacebookData.getString("email");
                        String image = bFacebookData.getString("profile_pic");
                        String uid = bFacebookData.getString("idFacebook");
                        String created_at = "";

                        if (lastname.contains(firstname) || lastname.contains("")) {
                            lastname = "";
                        }

                        Log.d(TAG, "FB Data : " + firstname + " " + lastname + " " +
                                email + " " + image);

                        session.setLogin(true);
                        // Inserting row in users table
                        db.addUser(firstname, lastname, email, uid, image, created_at);
                        goToHomePage();
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parameters
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

                Toast.makeText(Login.this, "Cancel Login", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(Login.this, "Error Login Facebook", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onLoginPhone(final View view) {
        final Intent intent = new Intent(Login.this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.CODE); // or .ResponseType.TOKEN
        // ... perform additional configuration ...
       intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
      //  startActivityForResult(intent, APP_REQUEST_CODE);
//        SessionManager session = new SessionManager(Login.this);
//        session.numberLoginSession();
    }


    private void goToHomePage() {
        Intent intent = new Intent(this, Main.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void googleSignOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void googleRevokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }


    private void updateUI(boolean b) {
        if (b) {
            Log.e("Sucessfull", "Login");
            Log.i("google login result",acct.toString());
            String nam = acct.getDisplayName();
            String em = acct.getEmail();
//            String google_id=acct.getPhotoUrl().toString();
//            String userUrl=acct.getPhotoUrl().toString();
            String google_id=acct.getFamilyName();
            Log.e("googleId",google_id);

            SessionManager session = new SessionManager(this);
            session.googleLoginSession(nam, em,google_id);

            startActivity(new Intent(this, SignUp.class));
            finish();
        } else {
            Log.e(TAG, " Google SignIn error");
        }
    }
    /**
     * method to verify login details in mysql db
     */
    private void checkLogin(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        session.setLogin(true);

                        // Now store the user in SQLite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String firstname = user.getString("firstname");
                        String lastname = user.getString("lastname");
                        String email = user.getString("email");
                        String created_at = user
                                .getString("created_at");

                        String imageURL = null;

                        // Inserting row in users table
                        db.addUser(firstname, lastname, email, uid, imageURL, created_at);

                        // Launch main activity
                        Intent intent = new Intent(Login.this,
                                Main.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        Controller.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void handleGoogleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handle Google SignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String uid = acct.getId();
            String created_at = "";
            String personName = acct.getDisplayName();

            String[] nameParts = personName.split("\\s+");
            String firstname = nameParts[0];
            String lastname = nameParts[1];
            String personPhotoUrl = null;

            if (acct.getPhotoUrl() != null) {
                personPhotoUrl = acct.getPhotoUrl().toString();
            }
            String email = acct.getEmail();

            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);

            session.setLogin(true);
            // Inserting row in users table
            db.addUser(firstname, lastname, email, uid, personPhotoUrl, created_at);
            updateUI(true);
            // Launch main activity
            Intent intent = new Intent(Login.this,
                    Main.class);
            startActivity(intent);
            finish();

        } else {
            // Signed out, show unauthenticated UI.
            Log.e(TAG, "");
            updateUI(false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleGoogleSignInResult(result);
        }
        else if (requestCode == LOGIN_TO_TWITTER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                getAccessToken(data
                        .getStringExtra(LoginToTwitter.CALLBACK_URL_KEY));
            }
        }
            //
        /*if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
               // showErrorActivity(loginResult.getError());
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0,10));
                }

                // If you have an authorization code, retrieve it from
                // loginResult.getAuthorizationCode()
                // and pass it to your server and exchange it for an access token.

                // Success! Start your next activity...
                goToHomePage();
            }

            // Surface the result to your user in an appropriate way.
            Toast.makeText(
                    this,
                    toastMessage,
                    Toast.LENGTH_LONG)
                    .show();
        }

*/
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleGoogleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideDialog();
                    handleGoogleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            Person currentPerson = null;
            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            }

            //app().getUserInfo().setGooglePerson(currentPerson);
        } catch (Exception e) {
            Log.e(TAG, "error in google api current person information " + e.getMessage());
        }
        updateUI(true);
    }

    @Override
    public void onConnectionSuspended(int i) {

        mGoogleApiClient.connect();
        updateUI(false);
    }

    private Bundle getFacebookData(JSONObject object) {

        Bundle bundle = null;
        try {
            bundle = new Bundle();
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));
        } catch (JSONException e) {
            Log.d(TAG, "Error parsing JSON");
        }
        return bundle;
    }

    private void showDialog() {
        if (pDialog == null) {
            pDialog = new ProgressDialog(this);
            pDialog.setMessage(getString(R.string.loading));
            pDialog.setIndeterminate(true);
        }
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private class GetRequestTokenTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.setOAuthConsumerKey(getString(R.string.TWITTER_CONSUMER_KEY));
            builder.setOAuthConsumerSecret(getString(R.string.TWITTER_CONSUMER_SECRET));
            Configuration configuration = builder.build();
            TwitterFactory factory = new TwitterFactory(configuration);
            twitter = factory.getInstance();

            //twitter = TwitterFactory.getSingleton();
            System.out.println("TWITTER_CONSUMER_KEY: "
                    + getString(R.string.TWITTER_CONSUMER_KEY)
                    + ", TWITTER_CONSUMER_SECRET: "
                    + getString(R.string.TWITTER_CONSUMER_SECRET));
         /*   twitter.setOAuthConsumer(getString(R.string.TWITTER_CONSUMER_KEY),
                    getString(R.string.TWITTER_CONSUMER_SECRET));*/

            try {
                RequestToken requestToken = twitter
                        .getOAuthRequestToken(getString(R.string.TWITTER_CALLBACK_URL));
                launchLoginWebView(requestToken);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void launchLoginWebView(RequestToken requestToken) {
        Intent intent = new Intent(this, LoginToTwitter.class);
        intent.putExtra(AUTHENTICATION_URL_KEY,
                requestToken.getAuthenticationURL());
        startActivityForResult(intent, LOGIN_TO_TWITTER_REQUEST);
    }


    private void getAccessToken(String callbackUrl) {
        Uri uri = Uri.parse(callbackUrl);
        String verifier = uri.getQueryParameter("oauth_verifier");

        GetAccessTokenTask getAccessTokenTask = new GetAccessTokenTask();
        getAccessTokenTask.execute(verifier);
    }

    private class GetAccessTokenTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String verifier = strings[0];
            try {
                AccessToken accessToken = twitter.getOAuthAccessToken(verifier);
                Log.d(Login.class.getSimpleName(),
                        accessToken.getToken());

                long userID = accessToken.getUserId();

                User user = twitter.showUser(userID);

                String userName = user.getName();
                String userId = user.getScreenName();

                System.out.println(userName);
                System.out.println(userId);

            } catch (Exception e) {

            }
            return null;
        }
    }

}
////////////////
///for logout
///AccountKit.logOut();