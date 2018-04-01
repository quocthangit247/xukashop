package com.rainbow007.xukashop.Presenter.TrangChu.XuLyMenu;

import com.rainbow007.xukashop.ConnectInternet.DownloadJSON;
import com.rainbow007.xukashop.Model.ObjectClass.LoaiSanPham;
import com.rainbow007.xukashop.Model.TrangChu.XuLyMenu.XulyJSONMenu;
import com.rainbow007.xukashop.View.TrangChu.ViewXuLyMenu;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by rainbow007 on 3/19/18.
 */

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {

    ViewXuLyMenu viewXuLyMenu;

    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu) {
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void LayDanhSachMenu() {

        List<LoaiSanPham> loaiSanPhamList;
        String dataJson = "";
        String duongdan = "http://192.168.1.165/xukaweb/loaisanpham.php?maloaicha=DU001";

        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        downloadJSON.execute();

        try {
            dataJson = downloadJSON.get();
            XulyJSONMenu xulyJSONMenu = new XulyJSONMenu();
            loaiSanPhamList = xulyJSONMenu.ParserJSONMenu(dataJson);
            viewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
