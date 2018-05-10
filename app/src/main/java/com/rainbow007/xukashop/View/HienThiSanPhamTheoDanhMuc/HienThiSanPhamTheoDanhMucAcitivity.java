package com.rainbow007.xukashop.View.HienThiSanPhamTheoDanhMuc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.rainbow007.xukashop.R;

public class HienThiSanPhamTheoDanhMucAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthisanphamtheodanhmuc);

        Intent intent = getIntent();
        String mathuonghieu = intent.getStringExtra("MaThuongHieu");
        String tenthuonghieu = intent.getStringExtra("TenThuongHieu");
        Log.d("HIENTHISP",mathuonghieu+"    "+tenthuonghieu);
        Toast.makeText(this,mathuonghieu+"     "+tenthuonghieu,Toast.LENGTH_SHORT).show();

    }
}
