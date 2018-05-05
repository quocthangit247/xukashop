package com.rainbow007.xukashop.Presenter.TrangChu_DuongDa;

import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Model.ObjectClass.ThuongHieu;
import com.rainbow007.xukashop.Model.Trangchu_DuongDa.ModelDuongDa;
import com.rainbow007.xukashop.View.ViewDuongDa;

import java.util.List;

public class PresenterLogicDuongDa implements IPresenterDuongDa {

    ViewDuongDa viewDuongDa;
    ModelDuongDa modelDuongDa;

    public PresenterLogicDuongDa(ViewDuongDa viewDuongDa) {
        this.viewDuongDa = viewDuongDa;
        modelDuongDa = new ModelDuongDa();
    }

    @Override
    public void LayDanhSachDuongDa() {

        List<ThuongHieu> thuongHieuList = modelDuongDa.LayDanhSachThuongHieuLon();
        List<SanPham> sanPhamList = modelDuongDa.LaySanPhamDuongDa();
        if (thuongHieuList.size() > 0) {
            viewDuongDa.HienThiDanhSach(thuongHieuList, sanPhamList);
        }

    }


}
