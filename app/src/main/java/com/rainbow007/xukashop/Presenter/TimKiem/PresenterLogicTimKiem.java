package com.rainbow007.xukashop.Presenter.TimKiem;

import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Model.TimKiem.ModelTimKiem;
import com.rainbow007.xukashop.View.TimKiem.ViewTimKiem;

import java.util.List;

public class PresenterLogicTimKiem implements IPresenterTimKiem {

    ViewTimKiem viewTimKiem;
    ModelTimKiem modelTimKiem;

    public PresenterLogicTimKiem(ViewTimKiem viewTimKiem) {
        this.viewTimKiem = viewTimKiem;
        modelTimKiem = new ModelTimKiem();

    }


    @Override
    public void TimKiemSanPhamTheoTenSp(String tensp) {
        List<SanPham> sanPhamList = modelTimKiem.LayDanhSachSanPhamTheoMaThuongHieu(tensp);

        if (sanPhamList.size() > 0) {
            viewTimKiem.TimKiemThanhCong(sanPhamList);
        } else {
            viewTimKiem.TimKiemThatBai();
        }
    }


}
