package com.rainbow007.xukashop.View;

import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Model.ObjectClass.ThuongHieu;

import java.util.List;

public interface ViewDuongDa {

    void HienThiDanhSach(List<ThuongHieu> thuongHieus, List<SanPham> sanPhams);
}
