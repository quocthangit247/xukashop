package com.rainbow007.xukashop.View.ChiTietSanPham;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.rainbow007.xukashop.R;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham {

    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView txtTenSp, txtGiaTien;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);

        String masp = String.valueOf(getIntent().getIntExtra("masp", 0));
        Log.d("Chitietsp",masp);
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSP(masp);


    }

    @Override
    public void HienThiChiTietSanPham(SanPham sanPham) {

    }
}
