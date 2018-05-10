package com.rainbow007.xukashop.View.HienThiSanPhamTheoDanhMuc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rainbow007.xukashop.CustomAdapter.AdapterSanPham;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Presenter.HienThiSanPhamTheoDanhMuc.PresenterLogicHienThiSanPhamTheoDanhMuc;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.TrangChu.ViewHienThiSanPhamTheoDanhMuc;

import java.util.List;

public class HienThiSanPhamTheoDanhMucAcitivity extends AppCompatActivity implements ViewHienThiSanPhamTheoDanhMuc, View.OnClickListener {

    RecyclerView recyclerView;
    Button btnThayDoi;
    String mathuonghieu, tenthuonghieu;
    boolean gridview = true;
    RecyclerView.LayoutManager layoutManager;
    PresenterLogicHienThiSanPhamTheoDanhMuc sanPhamTheoDanhMuc;
    AdapterSanPham adapterSanPham;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthisanphamtheodanhmuc);

        recyclerView = findViewById(R.id.recyclerHienThiSPtheoThuongHieu);
        btnThayDoi = findViewById(R.id.btnThayDoiTrangThai);

        Intent intent = getIntent();
        mathuonghieu = intent.getStringExtra("MaThuongHieu");
        tenthuonghieu = intent.getStringExtra("TenThuongHieu");
        Log.d("HIENTHISP", mathuonghieu + "    " + tenthuonghieu);

        sanPhamTheoDanhMuc = new PresenterLogicHienThiSanPhamTheoDanhMuc(this);
        sanPhamTheoDanhMuc.LayDanhSachSanPham(mathuonghieu);

        btnThayDoi.setOnClickListener(this);

    }

    @Override
    public void HienThiDanhSachSanPham(List<SanPham> sanPhamList) {

        if (gridview) {
            layoutManager = new GridLayoutManager(HienThiSanPhamTheoDanhMucAcitivity.this, 3);
            adapterSanPham = new AdapterSanPham(HienThiSanPhamTheoDanhMucAcitivity.this, R.layout.custom_layout_recyclerview_sanpham, sanPhamList);

        } else {
            layoutManager = new LinearLayoutManager(HienThiSanPhamTheoDanhMucAcitivity.this);
            adapterSanPham = new AdapterSanPham(HienThiSanPhamTheoDanhMucAcitivity.this, R.layout.custom_layout_list_sanphamtheodanhmuc, sanPhamList);

        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterSanPham);
        adapterSanPham.notifyDataSetChanged();

    }

    @Override
    public void LoiHienThi() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnThayDoiTrangThai:
                gridview = !gridview;
                sanPhamTheoDanhMuc.LayDanhSachSanPham(mathuonghieu);
                break;
        }
    }
}
