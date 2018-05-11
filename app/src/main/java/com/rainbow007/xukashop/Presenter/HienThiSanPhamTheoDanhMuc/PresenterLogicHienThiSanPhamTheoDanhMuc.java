package com.rainbow007.xukashop.Presenter.HienThiSanPhamTheoDanhMuc;

import android.view.View;
import android.widget.ProgressBar;

import com.rainbow007.xukashop.Model.HienThiSanPhamTheoDanhMuc.ModelHienThiSanPhamTheoDanhMuc;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.View.TrangChu.ViewHienThiSanPhamTheoDanhMuc;

import java.util.List;

public class PresenterLogicHienThiSanPhamTheoDanhMuc implements IPresenterHienThiSanPhamTheoDanhMuc {

    ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc;
    ModelHienThiSanPhamTheoDanhMuc modelHienThiSanPhamTheoDanhMuc;

    public PresenterLogicHienThiSanPhamTheoDanhMuc(ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc) {
        this.viewHienThiSanPhamTheoDanhMuc = viewHienThiSanPhamTheoDanhMuc;
        modelHienThiSanPhamTheoDanhMuc = new ModelHienThiSanPhamTheoDanhMuc();
    }

    @Override
    public void LayDanhSachSanPham(String maloai) {
        List<SanPham> sanPhamList = modelHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaThuongHieu(maloai, 0);
        if (sanPhamList.size() > 0) {
            viewHienThiSanPhamTheoDanhMuc.HienThiDanhSachSanPham(sanPhamList);
        } else {
            viewHienThiSanPhamTheoDanhMuc.LoiHienThi();
        }
    }

    public List<SanPham> LayDanhSachSanPhamLoadMore(String maloai, int limit, ProgressBar progressBar) {

        progressBar.setVisibility(View.VISIBLE);
        List<SanPham> sanPhamList = modelHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaThuongHieu(maloai, limit);

        if (sanPhamList.size() != 0) {
            progressBar.setVisibility(View.GONE);
        }

        return sanPhamList;
    }
}
