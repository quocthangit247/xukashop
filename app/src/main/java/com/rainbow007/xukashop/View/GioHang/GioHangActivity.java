package com.rainbow007.xukashop.View.GioHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.rainbow007.xukashop.CustomAdapter.AdapterGioHang;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Presenter.GioHang.PresnterLogicGioHang;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.ThanhToan.ThanhToanActivity;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang, View.OnClickListener {

    RecyclerView recyclerView;
    PresnterLogicGioHang presnterLogicGioHang;
    Toolbar toolbar;
    Button btnMuaNgay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);

        recyclerView = findViewById(R.id.recyclerGioHang);
        toolbar = findViewById(R.id.toolBarGioHang);
        btnMuaNgay = findViewById(R.id.btnMuaNgay);

        setSupportActionBar(toolbar);

        presnterLogicGioHang = new PresnterLogicGioHang(this);
        presnterLogicGioHang.LayDanhSachSanPhamTrongGioHang(this);

        btnMuaNgay.setOnClickListener(this);

    }

    @Override
    public void HienThiDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this, sanPhamList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnMuaNgay:

                Intent intent = new Intent(GioHangActivity.this, ThanhToanActivity.class);
                startActivity(intent);

                break;
        }
    }
}
