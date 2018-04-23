package com.rainbow007.xukashop.View.DangNhap.Fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.TrangChu.TrangChuActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class FragmentDangNhap extends Fragment implements View.OnClickListener {

    Button btnDangNhap;
    CallbackManager callbackManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);

        callbackManager =  CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        btnDangNhap = view.findViewById(R.id.btnDangNhapFB);
        btnDangNhap.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {

        LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
}
