package com.rainbow007.xukashop.Presenter.TrangChu_TrangDiem;

import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Model.ObjectClass.ThuongHieu;
import com.rainbow007.xukashop.Model.Trangchu_TrangDiem.ModelTrangDiem;
import com.rainbow007.xukashop.View.ViewTrangDiem;

import java.util.List;

public class PresenterLogicTrangDiemMoi implements IPresenterTrangDiemMoi {

    ViewTrangDiem viewTrangDiem;
    ModelTrangDiem modelTrangDiem;

    public PresenterLogicTrangDiemMoi (ViewTrangDiem viewTrangDiem) {
        this.viewTrangDiem = viewTrangDiem;
        modelTrangDiem = new ModelTrangDiem();
    }

    @Override
    public void LayDanhSachTrangDiemMoi () {

        List<ThuongHieu> thuongHieuList = modelTrangDiem.LayDanhSachThuongHieuLon();
        List<SanPham> sanPhamList = modelTrangDiem.LaySanPhamTrangDiemMoi();
        if (thuongHieuList.size() > 0) {
            viewTrangDiem.HienThiDanhSach(thuongHieuList, sanPhamList);
        }
    }

}

