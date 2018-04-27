package com.rainbow007.xukashop.View.DangNhap_DangKy.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.rainbow007.xukashop.Model.DangNhap_DangKy.ModelDangNhap;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.TrangChu.TrangChuActivity;

import java.util.Arrays;

public class FragmentDangNhap extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    Button btnDangNhapFB, btnDangNhapGG, btnDangNhap;
    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    public static int SIGN_IN_GOOGLE_PLUS = 1;
    ProgressDialog progressDialog;
    ModelDangNhap modelDangNhap;
    EditText edtEmail, edtPassword;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);

        modelDangNhap = new ModelDangNhap();
        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(getContext(), this);

        callbackManager = CallbackManager.Factory.create();
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

        btnDangNhapFB = view.findViewById(R.id.btnDangNhapFB);
        btnDangNhapFB.setOnClickListener(this);

        btnDangNhapGG = view.findViewById(R.id.btnDangNhapGG);
        btnDangNhapGG.setOnClickListener(this);

        btnDangNhap = view.findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(this);

        edtEmail = view.findViewById(R.id.edDiaChiEmailDangNhap);
        edtPassword = view.findViewById(R.id.edMatKhauDangNhap);


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDangNhapFB:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
                break;
            case R.id.btnDangNhapGG:
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(intent, SIGN_IN_GOOGLE_PLUS);
                //goi ham progress
                showProgressDialog();
                break;
            case R.id.btnDangNhap:
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                boolean result = modelDangNhap.KiemTraDangNhap(getActivity(), email, password);
                if (result == true) {
                    Intent intent1 = new Intent(getActivity(), TrangChuActivity.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(getActivity(), "Tên đăng nhập hoặc mật khẩu chưa đúng", Toast.LENGTH_SHORT).show();
                }
                ;
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_GOOGLE_PLUS) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                progressDialog.cancel();//huy progress de nhay sang trang chu
                Intent intent = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(intent);
            }
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // hien thi dialog tron
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            //set cho dialog chay hinh tron khi dang nhap
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
    }
}
