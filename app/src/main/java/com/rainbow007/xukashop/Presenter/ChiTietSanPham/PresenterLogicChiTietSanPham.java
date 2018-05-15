package com.rainbow007.xukashop.Presenter.ChiTietSanPham;

import android.content.Context;
import android.text.TextUtils;

import com.rainbow007.xukashop.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.rainbow007.xukashop.Model.GioHang.ModelGioHang;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham {

    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;
    ModelGioHang modelGioHang;

    public PresenterLogicChiTietSanPham() {
        modelGioHang = new ModelGioHang();
    }

    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();
        modelGioHang = new ModelGioHang();
    }

    @Override
    public void LayChiTietSP(String masp) {
        SanPham sanPham = modelChiTietSanPham.LayChiTietSp(masp);//lai di
        //ok
        if (!TextUtils.isEmpty(sanPham.getTenSp())) {
            viewChiTietSanPham.HienThiChiTietSanPham(sanPham);
        }
    }

    @Override
    public void ThemGioHang(SanPham sanPham, Context context) {
        modelGioHang.MoKetNoiSQL(context);
        boolean kiemtra = modelGioHang.ThemGioHang(sanPham);
        if (kiemtra) {
            viewChiTietSanPham.ThemGioHangThanhCong();
        } else {
            viewChiTietSanPham.ThemGioHangThatBai();
        }
    }

    public int DemSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        return sanPhamList.size();
    }
}
