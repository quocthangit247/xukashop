package com.rainbow007.xukashop.Model.DangNhap_DangKy;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

public class ModelDangNhap {

    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;

    public AccessToken LayTokenFBHientai() {

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };
        accessToken = AccessToken.getCurrentAccessToken();
        return accessToken;
    }


    public GoogleApiClient LayGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener) {

        GoogleApiClient mGoogleApiClient;

        //khoi tao cac option cua google va bat ng dung dang nhap email
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //add key cua google
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage((AppCompatActivity) context, failedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        return mGoogleApiClient;
    }

    public GoogleSignInResult LayThongTinDangNhapGG(GoogleApiClient googleApiClient) {

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            return opr.get();
        } else {
            return null;
        }
    }

    public void HuyTokenTracker() {
        accessTokenTracker.stopTracking();
    }
}
