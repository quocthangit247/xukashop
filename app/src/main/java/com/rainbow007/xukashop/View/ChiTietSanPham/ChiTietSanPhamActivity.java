package com.rainbow007.xukashop.View.ChiTietSanPham;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.GioHang.GioHangActivity;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, View.OnClickListener {

    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView txtTenSp, txtGiaTien, txtChiTietSp, txtGioHang;
    ImageView imageView;
    ImageButton imageButton;
    Toolbar toolbar;
    SanPham sanPhamGioHang;
    boolean onPause = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);

        txtTenSp = findViewById(R.id.txtTenSp);
        txtGiaTien = findViewById(R.id.txtGiaSp);
        imageView = findViewById(R.id.imgSp);
        txtChiTietSp = findViewById(R.id.txtThongTinChiTiet);
        toolbar = findViewById(R.id.toolBarDetailSP);
        imageButton = findViewById(R.id.imgThemGioHang);

        setSupportActionBar(toolbar);

        SanPham masp = getIntent().getParcelableExtra("masp");
        Log.d("Chitietsp", String.valueOf(masp.getMasp()));
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSP(String.valueOf(masp.getMasp()));

        imageButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);

        MenuItem itemGioHang = menu.findItem(R.id.icCart);
        View giaoDienCustomGioHang = itemGioHang.getActionView();
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);

        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(intent);

            }
        });

        return true;
    }

    @Override
    public void HienThiChiTietSanPham(SanPham sanPham) {

        txtTenSp.setText(sanPham.getTenSp());
        sanPhamGioHang = sanPham;
        Log.d("TenActivity", sanPham.getTenSp());
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String giaban = formatter.format(sanPham.getGiaBan());
        txtGiaTien.setText(giaban + " VND");
        Picasso.get().load(sanPham.getAnhNho()).into(imageView);
        txtChiTietSp.setText(sanPham.getThongTin());


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgThemGioHang:

                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);

                byte[] hinhsanphamgiohang = byteArrayOutputStream.toByteArray();

                sanPhamGioHang.setHinhGioHang(hinhsanphamgiohang);
                sanPhamGioHang.setSoluong(1);
                presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang, this);

                break;
        }
    }

    @Override
    public void ThemGioHangThanhCong() {
        Toast.makeText(this, "Da them san pham vao gio hang", Toast.LENGTH_SHORT).show();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
    }

    @Override
    public void ThemGioHangThatBai() {
        Toast.makeText(this, "SP da co trong gio hang", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (onPause) {
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        onPause = true;
    }
}
