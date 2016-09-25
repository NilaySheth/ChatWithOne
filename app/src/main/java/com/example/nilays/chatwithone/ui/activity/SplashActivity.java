package com.example.nilays.chatwithone.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nilays.chatwithone.R;
import com.example.nilays.chatwithone.utils.Consts;
import com.example.nilays.chatwithone.utils.SharedPreferencesUtil;
import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.core.helper.StringifyArrayList;
import com.quickblox.sample.core.ui.activity.CoreSplashActivity;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class SplashActivity extends CoreSplashActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SharedPreferencesUtil.hasQbUser()) {
            proceedToTheNextActivityWithDelay();
            return;
        }

        createSession();
    }

    @Override
    protected String getAppName() {
        return getString(R.string.splash_app_title);
    }

    @Override
    protected void proceedToTheNextActivity() {
        if (SharedPreferencesUtil.hasQbUser()) {
            DialogsActivity.start(this);
        } else {
            LoginActivity.start(this);
        }
        finish();
    }

    private void createSession() {
        QBAuth.createSession(new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession result, Bundle params) {
                userSignUp();
            }

            @Override
            public void onError(QBResponseException e) {
                showSnackbarError(null, R.string.splash_create_session_error, e, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createSession();
                    }
                });
            }
        });
    }

    private void userSignUp() {
        final QBUser user = new QBUser(Consts.QB_USERS_NAME, Consts.QB_USERS_PASSWORD);
        StringifyArrayList<String> tagsArray = new StringifyArrayList<String>();
        tagsArray.add(Consts.QB_USERS_TAG);
        user.setPassword(Consts.QB_USERS_PASSWORD);
        user.setFullName(Consts.QB_USERS_FULLNAME);
        user.setPhone(Consts.QB_USERS_PHONE);
        user.setEmail(Consts.QB_USERS_EMAIL);
        user.setCustomData(Consts.QB_USERS_CITY_COMPANY);
        user.setTags(tagsArray);
        QBUsers.signUp(user, new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser user, Bundle args) {
                // success
                userSignIn();
            }

            @Override
            public void onError(QBResponseException error) {
                // error
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                userSignIn();
            }
        });
    }

    private void userSignIn() {
        final QBUser user = new QBUser(Consts.QB_USERS_NAME, Consts.QB_USERS_PASSWORD);
        QBUsers.signIn(user, new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser user, Bundle args) {
                // success
//                proceedToTheNextActivity();
                SharedPreferencesUtil.saveQbUser(user);

                DialogsActivity.start(SplashActivity.this);
                finish();
            }

            @Override
            public void onError(QBResponseException error) {
                // error
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}