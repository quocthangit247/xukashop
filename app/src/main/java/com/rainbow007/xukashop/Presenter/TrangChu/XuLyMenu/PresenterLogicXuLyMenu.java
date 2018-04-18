package com.rainbow007.xukashop.Presenter.TrangChu.XuLyMenu;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.rainbow007.xukashop.ConnectInternet.DownloadJSON;
import com.rainbow007.xukashop.Model.DangNhap.ModelDangNhap;
import com.rainbow007.xukashop.Model.ObjectClass.LoaiSanPham;
import com.rainbow007.xukashop.Model.TrangChu.XuLyMenu.XulyJSONMenu;
import com.rainbow007.xukashop.View.TrangChu.ViewXuLyMenu;

import org.json.JSONException;
import org.json.JSONObject;

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
        String duongdan = "http://xukashop.pe.hu/php/loaisanpham.php";
        HashMap<String, String> hashMaLoaiCha = new HashMap<>();
        hashMaLoaiCha.put("maloaicha", "001");
        attrs.add(hashMaLoaiCha);
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
