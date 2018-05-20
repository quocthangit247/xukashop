package com.rainbow007.xukashop.Model.ThongKe;

import android.util.Log;

import com.rainbow007.xukashop.ConnectInternet.DownloadJSON;
import com.rainbow007.xukashop.Model.ObjectClass.ChiTietHoaDon;
import com.rainbow007.xukashop.Model.ObjectClass.HoaDon;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Model.ObjectClass.ThongKeHoaDon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.rainbow007.xukashop.View.TrangChu.TrangChuActivity.SERVER_NAME;

public class ModelThongKe {

    public List<ThongKeHoaDon> LayHoaDon(String month) {

        List<ThongKeHoaDon> thongKeHoaDonList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJson = "";

        String duongdan = SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "LayHoaDon");

        HashMap<String, String> hsMonth = new HashMap<>();
        hsMonth.put("thang", month);

        attrs.add(hsHam);
        attrs.add(hsMonth);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();
        Log.d("ModelThongke", "111111");

        try {
            dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            Log.d("ModelThongke", dataJson);
            JSONArray jsonArrayDsThuongHieu = jsonObject.getJSONArray("DANHSACHHOADON");
            int dem = jsonArrayDsThuongHieu.length();



            for (int i = 0; i < dem; i++) {
                ThongKeHoaDon thongKeHoaDon = new ThongKeHoaDon();
                JSONObject object = jsonArrayDsThuongHieu.getJSONObject(i);

                thongKeHoaDon.setThang(object.getString("THANG"));
                thongKeHoaDon.setTuan(object.getString("TUAN"));
                thongKeHoaDon.setSoluong(Integer.parseInt(object.getString("SOLUONG")));
                thongKeHoaDonList.add(thongKeHoaDon);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return thongKeHoaDonList;
    }

}
