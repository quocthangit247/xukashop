package com.rainbow007.xukashop.Model.HienThiSanPhamTheoDanhMuc;

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

public class ModelHienThiSanPhamTheoDanhMuc {

    public List<SanPham> LayDanhSachSanPhamTheoMaThuongHieu(String maloai, int limit) {

        List<SanPham> sanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJson = "";

        String duongdan = SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "LayDanhSachSanPhamDuongDa");

        HashMap<String, String> hsMaLoai = new HashMap<>();
        hsMaLoai.put("maloaiThuongHieu", maloai);

        HashMap<String, String> hsLimit = new HashMap<>();
        hsMaLoai.put("limit", String.valueOf(limit));

        attrs.add(hsHam);
        attrs.add(hsMaLoai);
        attrs.add(hsLimit);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();
        Log.d("ModelDuongDa", "111111");

        try {
            dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            Log.d("ModelDuongDa", dataJson);
            JSONArray jsonArrayDsThuongHieu = jsonObject.getJSONArray("DANHSACHSANPHAMTHEOTHUONGHIEU");
            int dem = jsonArrayDsThuongHieu.length();

            for (int i = 0; i < dem; i++) {
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDsThuongHieu.getJSONObject(i);

                sanPham.setTenSp(object.getString("TENSP"));
                sanPham.setGiaBan(Integer.parseInt(object.getString("GIABAN")));
                sanPham.setAnhNho(object.getString("ANHNHO"));

                sanPhamList.add(sanPham);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return sanPhamList;
    }

}
