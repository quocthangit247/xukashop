package com.rainbow007.xukashop.View.ThanhToan;

import com.rainbow007.xukashop.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewThanhToan {

    void DatHangThanhCong();
    void DatHangThatBai();
    void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList);
}
