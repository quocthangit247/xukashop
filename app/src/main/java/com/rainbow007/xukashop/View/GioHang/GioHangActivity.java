package com.rainbow007.xukashop.View.GioHang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.rainbow007.xukashop.CustomAdapter.AdapterGioHang;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Presenter.GioHang.PresnterLogicGioHang;
import com.rainbow007.xukashop.R;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang {

    RecyclerView recyclerView;
    PresnterLogicGioHang presnterLogicGioHang;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);

        recyclerView = findViewById(R.id.recyclerGioHang);
        toolbar = findViewById(R.id.toolBarGioHang);

        setSupportActionBar(toolbar);

        presnterLogicGioHang = new PresnterLogicGioHang(this);
        presnterLogicGioHang.LayDanhSachSanPhamTrongGioHang(this);

    }

    @Override
    public void HienThiDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this, sanPhamList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);

    }
}
