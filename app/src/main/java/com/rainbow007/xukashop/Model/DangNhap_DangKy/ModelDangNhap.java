package com.rainbow007.xukashop.Model.DangNhap_DangKy;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.rainbow007.xukashop.ConnectInternet.DownloadJSON;
import com.rainbow007.xukashop.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public String LayCacheDangNhap(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("DangNhap", Context.MODE_PRIVATE);
        String hoten = sharedPreferences.getString("hotenUser", "");

        return hoten;
    }

    public void UpdateCacheDangNhap(Context context, String hoten) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("DangNhap", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("hotenUser", hoten);

        editor.commit();
    }

    public boolean KiemTraDangNhap(Context context, String email, String password) {
        boolean ktra = false;
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "KtraDangNhap");

        HashMap<String, String> hsEmail = new HashMap<>();
        hsEmail.put("email", email);

        HashMap<String, String> hsPassword = new HashMap<>();
        hsPassword.put("password", password);

        attrs.add(hsHam);
        attrs.add(hsEmail);
        attrs.add(hsPassword);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();

        try {
            String dulieu = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieu);
            String jsonKqua = jsonObject.getString("ketqua");
            Log.d("ModelDangNhap", jsonKqua);
            if (jsonKqua.equals("true")) {
                ktra = true;
                // luu dang nhap vao shareprefernces
                String hoten = jsonObject.getString("hoten");
                UpdateCacheDangNhap(context, hoten);

            } else {
                ktra = false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ktra;
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
