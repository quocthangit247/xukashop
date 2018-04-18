package com.rainbow007.xukashop.Model.TrangChu.XuLyMenu;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.rainbow007.xukashop.ConnectInternet.DownloadJSON;
import com.rainbow007.xukashop.Model.ObjectClass.LoaiSanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by rainbow007 on 3/16/18.
 */

public class XulyJSONMenu {

    String username = "";

    public List<LoaiSanPham> ParserJSONMenu(String dulieujson) {

        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        try {
            Log.d("XULY", dulieujson);
            JSONObject jsonObject = new JSONObject(dulieujson);
            JSONArray loaisp = jsonObject.getJSONArray("LOAISANPHAM");
            int count = loaisp.length();
            for (int i = 0; i < count; i++) {
                JSONObject value = loaisp.getJSONObject(i);
                LoaiSanPham dataSanpham = new LoaiSanPham();
                dataSanpham.setMaLoaiSP(Integer.parseInt(value.getString("MALOAISP")));
                dataSanpham.setMaLoaiCha(Integer.parseInt(value.getString("MALOAICHA")));
                dataSanpham.setTenLoaiSP(value.getString("TENLOAISP"));

                loaiSanPhamList.add(dataSanpham);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;

    }

    public List<LoaiSanPham> LayLoaiSanPhamTheoMaLoai(int maloaisp) {

        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJson = "";

        String duongdan = "http://xukashop.pe.hu/php/loaisanpham.php";
        HashMap<String, String> hashMaLoaiCha = new HashMap<>();
        hashMaLoaiCha.put("maloaicha", String.valueOf(maloaisp));
        attrs.add(hashMaLoaiCha);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();
        try {
            dataJson = downloadJSON.get();
            XulyJSONMenu xulyJSONMenu = new XulyJSONMenu();
            loaiSanPhamList = xulyJSONMenu.ParserJSONMenu(dataJson);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;
    }

}
