package com.rainbow007.xukashop.Presenter.TrangChu.XuLyMenu;

import com.rainbow007.xukashop.ConnectInternet.DownloadJSON;
import com.rainbow007.xukashop.Model.ObjectClass.LoaiSanPham;
import com.rainbow007.xukashop.Model.TrangChu.XuLyMenu.XulyJSONMenu;
import com.rainbow007.xukashop.View.TrangChu.ViewXuLyMenu;

import java.util.ArrayList;
import java.util.HashMap;
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
        List<HashMap<String,String>> attrs = new ArrayList<>();
        //Download by get method
        //String duongdan = "http://192.168.1.165/xukaweb/loaisanpham.php?maloaicha=FA001";

        //DownloadJSON downloadJSON = new DownloadJSON(duongdan);

        //POST METHOD
        String duongdan = "http://192.168.1.165/xukaweb/loaisanpham.php";
        HashMap<String,String> hashMaLoaiCha = new HashMap<>();
        hashMaLoaiCha.put("maloaicha","FA001");
        attrs.add(hashMaLoaiCha);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);


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
