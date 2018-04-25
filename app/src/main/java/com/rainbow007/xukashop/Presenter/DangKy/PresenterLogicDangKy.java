package com.rainbow007.xukashop.Presenter.DangKy;

import com.rainbow007.xukashop.Model.DangNhap_DangKy.ModelDangKy;
import com.rainbow007.xukashop.Model.ObjectClass.TaiKhoan;
import com.rainbow007.xukashop.View.DangNhap_DangKy.ViewDangKy;

public class PresenterLogicDangKy implements IPresenterDangKy {

    ModelDangKy modelDangKy;
    ViewDangKy viewDangKy;

    public PresenterLogicDangKy(ViewDangKy viewDangKy) {
        this.viewDangKy = viewDangKy;
        modelDangKy = new ModelDangKy();
    }

    @Override
    public void ThucHienDangKy(TaiKhoan taiKhoan) {
        boolean ktra = modelDangKy.DangKyThanhVien(taiKhoan);
        if (ktra) {
            viewDangKy.DangKyThanhCong();
        } else {
            viewDangKy.DangKyThatBai();
        }
    }
}
