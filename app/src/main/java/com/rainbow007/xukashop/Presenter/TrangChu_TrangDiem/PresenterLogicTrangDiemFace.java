package com.rainbow007.xukashop.Presenter.TrangChu_TrangDiem;

import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Model.ObjectClass.ThuongHieu;
import com.rainbow007.xukashop.Model.Trangchu_TrangDiem.ModelTrangDiem;
import com.rainbow007.xukashop.View.ViewTrangDiem;

import java.util.List;

public class PresenterLogicTrangDiemFace implements IPresenterTrangDiemFace {

    ViewTrangDiem viewTrangDiem;
    ModelTrangDiem modelTrangDiem;

    public PresenterLogicTrangDiemFace (ViewTrangDiem viewTrangDiem) {
        this.viewTrangDiem = viewTrangDiem;
        modelTrangDiem = new ModelTrangDiem();
    }

    @Override
    public void LayDanhSachTrangDiemFace () {

        List<ThuongHieu> thuongHieuList = modelTrangDiem.LayDanhSachThuongHieuLon();
        List<SanPham> sanPhamList = modelTrangDiem.LaySanPhamTrangDiemFace();
        if (thuongHieuList.size() > 0) {
            viewTrangDiem.HienThiDanhSach(thuongHieuList, sanPhamList);
        }
    }

}