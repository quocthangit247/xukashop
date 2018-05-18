package com.rainbow007.xukashop.Model.TimKiem;

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

public class ModelTimKiem {

    public List<SanPham> LayDanhSachSanPhamTheoMaThuongHieu(String tensp) {

        List<SanPham> sanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJson = "";

        String duongdan = SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "TimKiemSanPhamTheoTen");

        HashMap<String, String> hsTensp = new HashMap<>();
        hsTensp.put("tensp", tensp);

        attrs.add(hsHam);
        attrs.add(hsTensp);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();
        Log.d("ModelTIMKIEM",downloadJSON.toString());
        try {
            dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            Log.d("ModelDuongDa", dataJson);
            JSONArray jsonArrayDsThuongHieu = jsonObject.getJSONArray("DANHSACHSANPHAMTHEOTEN");
            int dem = jsonArrayDsThuongHieu.length();

            for (int i = 0; i < dem; i++) {
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDsThuongHieu.getJSONObject(i);

                sanPham.setMasp(Integer.parseInt(object.getString("MASP")));
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
