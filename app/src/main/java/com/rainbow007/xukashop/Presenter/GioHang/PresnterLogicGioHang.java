package com.rainbow007.xukashop.Presenter.GioHang;

import android.content.Context;

import com.rainbow007.xukashop.Model.GioHang.ModelGioHang;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.View.GioHang.ViewGioHang;

import java.util.List;

public class PresnterLogicGioHang implements IPresenterGioHang {

    ModelGioHang modelGioHang;
    ViewGioHang viewGioHang;

    public PresnterLogicGioHang(ViewGioHang viewGioHang) {
        modelGioHang = new ModelGioHang();
        this.viewGioHang = viewGioHang;
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        if (sanPhamList.size() > 0) {
            viewGioHang.HienThiDanhSachSanPhamTrongGioHang(sanPhamList);
        }
    }
}
