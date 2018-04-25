package com.rainbow007.xukashop.Presenter.TrangChu.XuLyMenu;

import com.facebook.AccessToken;
import com.rainbow007.xukashop.ConnectInternet.DownloadJSON;
import com.rainbow007.xukashop.Model.DangNhap_DangKy.ModelDangNhap;
import com.rainbow007.xukashop.Model.ObjectClass.LoaiSanPham;
import com.rainbow007.xukashop.Model.TrangChu.XuLyMenu.XulyJSONMenu;
import com.rainbow007.xukashop.View.TrangChu.TrangChuActivity;
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
    String username = "";

    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu) {
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void LayDanhSachMenu() {

        List<LoaiSanPham> loaiSanPhamList;
        String dataJson = "";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        //Download by get method
//        String duongdan = "http://xukashop.pe.hu/php/loaisanpham.php?maloaicha=001";
//
//        DownloadJSON downloadJSON = new DownloadJSON(duongdan);

        //POST METHOD
        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "LayDanhSachMenu");

        HashMap<String, String> hashMaLoaiCha = new HashMap<>();
        hashMaLoaiCha.put("maloaicha", "001");

        attrs.add(hashMaLoaiCha);
        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
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

    @Override
    public AccessToken LayTokenNguoiDungFB() {

        ModelDangNhap modelDangNhap = new ModelDangNhap();
        AccessToken accessToken = modelDangNhap.LayTokenFBHientai();


        return accessToken;

    }
}
