package com.rainbow007.xukashop.Model.ChiTietSanPham;

import android.util.Log;

import com.rainbow007.xukashop.ConnectInternet.DownloadJSON;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.rainbow007.xukashop.View.TrangChu.TrangChuActivity.SERVER_NAME;

public class ModelChiTietSanPham {

    public SanPham LayChiTietSp(String masp) {

        SanPham sanPham = new SanPham();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJson = "";

        String duongdan = SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "LaySanPhamTheoMaSp");

        HashMap<String,String> hsMasp = new HashMap<>();
        hsMasp.put("masp",masp);

        attrs.add(hsHam);
        attrs.add(hsMasp);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();

        try {
            dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            Log.d("ModelChitietSP", dataJson);
            JSONArray jsonArrayDsThuongHieu = jsonObject.getJSONArray("CHITIETSANPHAM");
            int dem = jsonArrayDsThuongHieu.length();

            for (int i = 0; i < dem; i++) {

                JSONObject object = jsonArrayDsThuongHieu.getJSONObject(i);

                sanPham.setTenSp(object.getString("TENSP"));
                sanPham.setGiaBan(Integer.parseInt(object.getString("GIABAN")));
                sanPham.setAnhNho(object.getString("ANHNHO"));
                sanPham.setThongTin(object.getString("THONGTIN"));

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return sanPham;
    }
}
