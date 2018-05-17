package com.rainbow007.xukashop.Presenter.ThanhToan;

import android.content.Context;

import com.rainbow007.xukashop.Model.GioHang.ModelGioHang;
import com.rainbow007.xukashop.Model.ObjectClass.HoaDon;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Model.ThanhToan.ModelThanhToan;
import com.rainbow007.xukashop.View.ThanhToan.ViewThanhToan;

import java.util.List;

public class PresenterLogicThanhToan implements IPresenterThanhToan {

    ViewThanhToan viewThanhToan;
    Context context;
    ModelThanhToan modelThanhToan;
    ModelGioHang modelGioHang;

    public PresenterLogicThanhToan(ViewThanhToan viewThanhToan) {
        this.viewThanhToan = viewThanhToan;
        modelThanhToan = new ModelThanhToan();
        modelGioHang = new ModelGioHang();
    }


    @Override
    public void ThemHoaDon(HoaDon hoaDon) {
        boolean kiemtra = modelThanhToan.ThemHoaDon(hoaDon);
        if (kiemtra) {
            viewThanhToan.DatHangThanhCong();
        } else {
            viewThanhToan.DatHangThatBai();
        }
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        viewThanhToan.LayDanhSachSanPhamTrongGioHang(sanPhamList);
    }
}
