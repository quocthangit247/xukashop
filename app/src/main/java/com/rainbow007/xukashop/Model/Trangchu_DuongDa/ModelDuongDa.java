package com.rainbow007.xukashop.Model.Trangchu_DuongDa;

import android.util.Log;

import com.rainbow007.xukashop.ConnectInternet.DownloadJSON;
import com.rainbow007.xukashop.Model.ObjectClass.LoaiSanPham;
import com.rainbow007.xukashop.Model.ObjectClass.ThuongHieu;
import com.rainbow007.xukashop.Model.TrangChu.XuLyMenu.XulyJSONMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.rainbow007.xukashop.View.TrangChu.TrangChuActivity.SERVER_NAME;

public class ModelDuongDa {

    public List<ThuongHieu> LayDanhSachThuongHieuLon() {

        List<ThuongHieu> thuongHieuList = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJson = "";

        String duongdan = SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "LayDanhSachThuongHieuLon");

        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();
        Log.d("ModelDuongDa","111111");

        try {
            dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            Log.d("ModelDuongDa",dataJson);
            JSONArray jsonArrayDsThuongHieu = jsonObject.getJSONArray("DANHSACHTHUONGHIEU");
            int dem = jsonArrayDsThuongHieu.length();

            for (int i = 0; i < dem; i++) {
                ThuongHieu thuongHieu = new ThuongHieu();
                JSONObject object = jsonArrayDsThuongHieu.getJSONObject(i);

                thuongHieu.setMaThuongHieu(object.getString("MATHUONGHIEU"));
                thuongHieu.setTenThuongHieu(object.getString("TENTHUONGHIEU"));
                thuongHieu.setLuotMua(Integer.parseInt(object.getString("LUOTMUA")));
                thuongHieu.setHinhThuongHieu(object.getString("HINHTHUONGHIEU"));
                Log.d("ModelDuongDa",thuongHieu.getHinhThuongHieu());
                Log.d("ModelDuongDa",String.valueOf(thuongHieu.getLuotMua()));

                thuongHieuList.add(thuongHieu);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return thuongHieuList;
    }
}
