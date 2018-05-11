package com.rainbow007.xukashop.Presenter.TrangChu_NuocHoa;

import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Model.ObjectClass.ThuongHieu;
import com.rainbow007.xukashop.Model.Trangchu_NuocHoa.ModelNuocHoa;
import com.rainbow007.xukashop.View.ViewNuocHoa;

import java.util.List;

public class PresenterLogicNuocHoa implements IPresenterNuocHoa {

    ViewNuocHoa viewNuocHoa;
    ModelNuocHoa modelNuocHoa;

    public PresenterLogicNuocHoa(ViewNuocHoa viewNuocHoa) {
        this.viewNuocHoa = viewNuocHoa;
        modelNuocHoa = new ModelNuocHoa();
    }

    @Override
    public void LayDanhSachNuocHoa() {

        List<ThuongHieu> thuongHieuList = modelNuocHoa.LayDanhSachThuongHieuLon();
        List<SanPham> sanPhamList = modelNuocHoa.LaySanPhamNuocHoa();
        if (thuongHieuList.size() > 0) {
            viewNuocHoa.HienThiDanhSach(thuongHieuList, sanPhamList);
        }

    }


}
