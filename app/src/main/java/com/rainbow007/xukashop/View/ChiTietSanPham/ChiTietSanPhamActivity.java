package com.rainbow007.xukashop.View.ChiTietSanPham;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.rainbow007.xukashop.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham {

    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView txtTenSp, txtGiaTien,txtChiTietSp;
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);

        txtTenSp = findViewById(R.id.txtTenSp);
        txtGiaTien = findViewById(R.id.txtGiaSp);
        imageView = findViewById(R.id.imgSp);
        txtChiTietSp = findViewById(R.id.txtThongTinChiTiet);

        SanPham masp = getIntent().getParcelableExtra("masp");
        Log.d("Chitietsp", String.valueOf(masp.getMasp()));
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSP(String.valueOf(masp.getMasp()));//laiq, đên đây mới dc khởi tạo , đại khái là thế, làm nhiềug nè
    }

    @Override
    public void HienThiChiTietSanPham(SanPham sanPham) {

        txtTenSp.setText(sanPham.getTenSp());
        Log.d("TenActivity", sanPham.getTenSp());
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String giaban = formatter.format(sanPham.getGiaBan());
        txtGiaTien.setText(giaban + " VND");
        Picasso.get().load(sanPham.getAnhNho()).into(imageView);
        txtChiTietSp.setText(sanPham.getThongTin());


    }
}
