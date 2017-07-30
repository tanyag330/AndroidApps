package com.org.rayklabs.reddrop;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.org.rayklabs.reddrop.Helper.SQLiteHandler;
import com.org.rayklabs.reddrop.Helper.SessionManager;
import com.org.rayklabs.reddrop.application.Controller;
import com.org.rayklabs.reddrop.model.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class Main extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;
    private ImageView profileImage;
    private Controller mController;
    private SQLiteHandler db;
    private SessionManager session;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariable();
        initView();
        checkSession();
        fetchUserDetails();
        logOut();

    }

    private void initializeVariable() {
        mController = (Controller) getApplicationContext();
        mGoogleApiClient = mController.getmGoogleApiClient();
    }

    private void initView() {

        txtName = (TextView) findViewById(R.id.txtName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        profileImage = (ImageView) findViewById(R.id.imageProfile);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // This line of code must be used in case of Facebook Login only
        if (AccessToken.getCurrentAccessToken() == null) {
            goToLoginActivity();
        }
    }


    private void checkSession() {

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logOut();
        }
    }

    private void fetchUserDetails() {

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("firstname") + " " + user.get("lastname");
        String email = user.get("email");
        String image = user.get("image_URL");

        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);

        Picasso.with(this)
                .load(image)
                .transform(new CircleTransform())
                .placeholder(R.drawable.ic_profile) //this is optional the image to display while the url image is downloading
                .error(R.drawable.ic_profile)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                .into(profileImage);
    }

    private void logOut() {

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutFromFacebook();
            }
        });
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        goToLoginActivity();
    }


    private void logoutFromGoogle() {

        if (mGoogleApiClient.isConnected()) {
            Log.d(TAG, "CONNECTED");
            new AlertDialog.Builder(this)
                    .setTitle("SignOut")
                    .setMessage("Are you sure you want to SignOut ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                                    new ResultCallback<Status>() {
                                        @Override
                                        public void onResult(Status status) {
                                            goToLoginActivity();
                                        }
                                    });

                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            Log.d(TAG, "NOT CONNECTED");
        }

        session.setLogin(false);
        db.deleteUsers();
    }

    private void logoutFromFacebook() {

        LoginManager.getInstance().logOut();
        session.setLogin(false);
        db.deleteUsers();
        goToLoginActivity();
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(Main.this, Login.class);
        startActivity(intent);
        finish();
    }
}
