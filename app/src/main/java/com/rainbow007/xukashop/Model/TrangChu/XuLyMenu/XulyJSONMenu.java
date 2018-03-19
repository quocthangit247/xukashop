package com.rainbow007.xukashop.Model.TrangChu.XuLyMenu;

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
            JSONObject jsonObject = new JSONObject(dulieujson);
            JSONArray loaisp = jsonObject.getJSONArray("Loaisanpham");
            int count = loaisp.length();
            for (int i = 0; i < count; i++) {
                JSONObject value = loaisp.getJSONObject(i);
                LoaiSanPham dataSanpham = new LoaiSanPham();
                dataSanpham.setMaLoaiSP(Integer.parseInt(value.getString("Maloaisp")));
                dataSanpham.setMaLoaiCha(Integer.parseInt(value.getString("Maloai")));
                dataSanpham.setTenLoaiSP(value.getString("tenloaisp"));

                loaiSanPhamList.add(dataSanpham);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;

    }
}
