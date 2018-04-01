package com.rainbow007.xukashop.Model.TrangChu.XuLyMenu;

import android.util.Log;

import com.rainbow007.xukashop.Model.ObjectClass.LoaiSanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rainbow007 on 3/16/18.
 */

public class XulyJSONMenu {

    public List<LoaiSanPham> ParserJSONMenu(String dulieujson) {

        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        try {
            Log.d("XULY",dulieujson);
            JSONObject jsonObject = new JSONObject(dulieujson);
            JSONArray loaisp = jsonObject.getJSONArray("LOAISANPHAM");
            int count = loaisp.length();
            for (int i = 0; i < count; i++) {
                JSONObject value = loaisp.getJSONObject(i);
                LoaiSanPham dataSanpham = new LoaiSanPham();
                dataSanpham.setMaLoaiSP(value.getString("MALOAISP"));
                dataSanpham.setMaLoaiCha(value.getString("MALOAICHA"));
                dataSanpham.setTenLoaiSP(value.getString("TENLOAISP"));

                loaiSanPhamList.add(dataSanpham);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;

    }
}
