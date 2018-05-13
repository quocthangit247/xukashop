package com.rainbow007.xukashop.Presenter.ChiTietSanPham;

import com.rainbow007.xukashop.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.View.ChiTietSanPham.ViewChiTietSanPham;

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham {

    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;

    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();
    }

    @Override
    public void LayChiTietSP(String masp) {
        SanPham sanPham = modelChiTietSanPham.LayChiTietSp(masp);
        if (sanPham.getMasp()>0){
            viewChiTietSanPham.HienThiChiTietSanPham(sanPham);
        }
    }
}
