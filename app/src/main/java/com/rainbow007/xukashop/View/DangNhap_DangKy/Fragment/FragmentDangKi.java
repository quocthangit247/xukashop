package com.rainbow007.xukashop.View.DangNhap_DangKy.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.rainbow007.xukashop.Model.ObjectClass.TaiKhoan;
import com.rainbow007.xukashop.Presenter.DangKy.PresenterLogicDangKy;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.DangNhap_DangKy.ViewDangKy;

public class FragmentDangKi extends Fragment implements ViewDangKy, View.OnClickListener, View.OnFocusChangeListener {

    PresenterLogicDangKy presenterLogicDangKy;
    Button btnDangKy;
    EditText edtHoten, edtMatKhau, edtNhapLaiMk, edtEmail;
    SwitchCompat EmailDocQuyen;
    TextInputLayout input_edtHoten, input_edtMatKhau, input_edtNhapLaiMK, input_edtDiaChiEmail;
    String emailDocQuyen = "";
    Boolean ktrathongtin = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangky, container, false);

        btnDangKy = view.findViewById(R.id.btnDangKy);
        edtHoten = view.findViewById(R.id.edHoTenDK);
        edtMatKhau = view.findViewById(R.id.edMatKhauDK);
        edtEmail = view.findViewById(R.id.edDiaChiEmailDK);
        edtNhapLaiMk = view.findViewById(R.id.edNhapLaiMatKhauDK);
        EmailDocQuyen = view.findViewById(R.id.EmailDocQuyen);
        input_edtHoten = view.findViewById(R.id.input_edHoTenDK);
        input_edtDiaChiEmail = view.findViewById(R.id.input_edDiaChiEmailDK);
        input_edtMatKhau = view.findViewById(R.id.input_edMatKhauDK);
        input_edtNhapLaiMK = view.findViewById(R.id.input_edNhapLaiMatKhauDK);

        presenterLogicDangKy = new PresenterLogicDangKy(this);

        btnDangKy.setOnClickListener(this);
        edtHoten.setOnFocusChangeListener(this);
        edtEmail.setOnFocusChangeListener(this);
        edtNhapLaiMk.setOnFocusChangeListener(this);


        return view;
    }

    @Override
    public void DangKyThanhCong() {
        Toast.makeText(getActivity(),"Đăng kí thành công",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getActivity(),"Đăng kí thất bại",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDangKy:
                btnDangKy();
                ;
                break;
        }
    }

    private void btnDangKy() {
        String hoten = edtHoten.getText().toString();
        String email = edtEmail.getText().toString();
        String matkhau = edtMatKhau.getText().toString();
        String nhaplaiMK = edtNhapLaiMk.getText().toString();

        EmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                emailDocQuyen = isChecked + "";
            }
        });
        if (ktrathongtin) {

            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setHoten(hoten);
            taiKhoan.setEmail(email);
            taiKhoan.setPassword(matkhau);
            taiKhoan.setEmailDocQuyen(emailDocQuyen);

            presenterLogicDangKy.ThucHienDangKy(taiKhoan);
        } else {
            Log.d("kiemtra", "thatbai");
        }


    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edHoTenDK:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_edtHoten.setErrorEnabled(true);
                        input_edtHoten.setError("Bạn chưa nhập họ tên");
                        ktrathongtin = false;
                    } else {
                        input_edtHoten.setErrorEnabled(false);
                        input_edtHoten.setError("");
                        ktrathongtin = true;
                    }
                }
                ;
                break;

            case R.id.edDiaChiEmailDK:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();

                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_edtDiaChiEmail.setErrorEnabled(true);
                        input_edtDiaChiEmail.setError("Bạn chưa nhập Email !!!");
                        ktrathongtin = false;
                    } else {
                        //kiem tra dinh dang Email
                        boolean ktraEmail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if (!ktraEmail) {
                            input_edtDiaChiEmail.setErrorEnabled(true);
                            input_edtDiaChiEmail.setError("Đây không phải địa chỉ Email !!!");
                            ktrathongtin = false;
                        } else {
                            input_edtDiaChiEmail.setErrorEnabled(false);
                            input_edtDiaChiEmail.setError("");
                            ktrathongtin = true;
                        }
                    }
                }
                ;
                break;

            case R.id.edNhapLaiMatKhauDK:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    String matkhau = edtMatKhau.getText().toString();

                    if (!chuoi.equals(matkhau)) {
                        input_edtNhapLaiMK.setErrorEnabled(true);
                        input_edtNhapLaiMK.setError("Không trùng khớp với mật khẩu !!!");
                        ktrathongtin = false;
                    } else {
                        input_edtNhapLaiMK.setErrorEnabled(false);
                        input_edtNhapLaiMK.setError("");
                        ktrathongtin = true;
                    }
                }
                ;
                break;
        }
    }
}
