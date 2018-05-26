package com.rainbow007.xukashop.Presenter.TrangChu_BanChay;

import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Model.ObjectClass.ThuongHieu;
import com.rainbow007.xukashop.Model.Trangchu_BanChay.ModelBanChay;
import com.rainbow007.xukashop.Presenter.TrangChu_BanChay.IPresenterBanChay;
import com.rainbow007.xukashop.View.ViewBanChay;

import java.util.List;

public class PresenterLogicBanChay implements IPresenterBanChay {

    ViewBanChay viewBanChay;
    ModelBanChay modelBanChay;

    public PresenterLogicBanChay(ViewBanChay viewBanChay) {
        this.viewBanChay = viewBanChay;
        modelBanChay = new ModelBanChay();
    }

    @Override
    public void LayDanhSachBanChay() {

        List<ThuongHieu> thuongHieuList = modelBanChay.LayDanhSachThuongHieuLon();
        List<SanPham> sanPhamList = modelBanChay.LayDanhSachSanPhamBanChay();
        if (thuongHieuList.size() > 0) {
            viewBanChay.HienThiDanhSach(thuongHieuList, sanPhamList);
        }

    }


}
