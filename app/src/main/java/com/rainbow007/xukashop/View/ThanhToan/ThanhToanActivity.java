package com.rainbow007.xukashop.View.ThanhToan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.rainbow007.xukashop.Model.ObjectClass.ChiTietHoaDon;
import com.rainbow007.xukashop.Model.ObjectClass.HoaDon;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Presenter.ThanhToan.PresenterLogicThanhToan;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.TrangChu.TrangChuActivity;

import java.util.ArrayList;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener, ViewThanhToan {

    Toolbar toolbar;
    EditText edtTenNguoiNhan, edtDiaChi, edtSdt;
    ImageButton imgNhanTienKhiNhanHang, imgChuyenKhoan;
    Button btnThanghToan;
    CheckBox cb;
    PresenterLogicThanhToan presenterLogicThanhToan;
    List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);

        edtTenNguoiNhan = findViewById(R.id.edtTenNguoiDung);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtSdt = findViewById(R.id.edtSodt);
        imgNhanTienKhiNhanHang = findViewById(R.id.imgNhanTienKhiGiaoHang);
        imgChuyenKhoan = findViewById(R.id.imgChuyenKhoan);
        btnThanghToan = findViewById(R.id.btnThanhToan);
        cb = findViewById(R.id.cbThoaThuan);

        btnThanghToan.setOnClickListener(this);

        toolbar = findViewById(R.id.toolbarThanhToan);
        setSupportActionBar(toolbar);

        presenterLogicThanhToan = new PresenterLogicThanhToan(this);
        presenterLogicThanhToan.LayDanhSachSanPhamTrongGioHang(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnThanhToan:

                String tenKh = edtTenNguoiNhan.getText().toString();
                String diachi = edtDiaChi.getText().toString();
                String sdt = edtSdt.getText().toString();

                if (tenKh.trim().length() > 0 && diachi.trim().length() > 0 && sdt.trim().length() > 0) {
                    if (cb.isChecked()) {

                        HoaDon hoaDon = new HoaDon();
                        hoaDon.setTenKH(tenKh);
                        hoaDon.setDiaChiGiaoHang(diachi);
                        hoaDon.setSdt(sdt);
                        hoaDon.setChiTietHoaDonList(chiTietHoaDons);

                        presenterLogicThanhToan.ThemHoaDon(hoaDon);

                    } else {
                        Toast.makeText(this, "Bạn chưa đồng ý cam kết !!! ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Bạn chưa nhập đầy đủ thông tin !!!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void DatHangThanhCong() {
        Intent intent = new Intent(ThanhToanActivity.this, TrangChuActivity.class);
        startActivity(intent);
    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(this, "Đặt hàng thất bại !!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {

        int size = sanPhamList.size();

        for (int i = 0; i < size; i++) {
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setMaSp(String.valueOf(sanPhamList.get(i).getMasp()));
            chiTietHoaDon.setSoluong(sanPhamList.get(i).getSoluong());

            chiTietHoaDons.add(chiTietHoaDon);
        }
    }
}
