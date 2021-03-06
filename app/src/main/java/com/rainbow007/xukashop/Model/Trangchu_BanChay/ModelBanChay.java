package com.rainbow007.xukashop.Model.Trangchu_BanChay;

import android.util.Log;

import com.rainbow007.xukashop.ConnectInternet.DownloadJSON;
import com.rainbow007.xukashop.Model.ObjectClass.LoaiSanPham;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
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

public class ModelBanChay {

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
        Log.d("ModelNuocHoa", "111111");

        try {
            dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            Log.d("ModelNuocHoa", dataJson);
            JSONArray jsonArrayDsThuongHieu = jsonObject.getJSONArray("DANHSACHTHUONGHIEU");
            int dem = jsonArrayDsThuongHieu.length();

            for (int i = 0; i < dem; i++) {
                ThuongHieu thuongHieu = new ThuongHieu();
                JSONObject object = jsonArrayDsThuongHieu.getJSONObject(i);

                thuongHieu.setMaThuongHieu(object.getString("MATHUONGHIEU"));
                thuongHieu.setTenThuongHieu(object.getString("TENTHUONGHIEU"));
                thuongHieu.setHinhThuongHieu(object.getString("HINHTHUONGHIEU"));
                Log.d("ModelBanChay", thuongHieu.getHinhThuongHieu());
                Log.d("ModelBanChay", String.valueOf(thuongHieu.getLuotMua()));

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

    public List<SanPham> LayDanhSachSanPhamBanChay() {

        List<SanPham> sanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJson = "";

        String duongdan = SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "LayDanhSachSanPhamBanChay");

        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();
        Log.d("ModelBanChay", "111111");

        try {
            dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            Log.d("ModelBanChay", dataJson);
            JSONArray jsonArrayDsThuongHieu = jsonObject.getJSONArray("DANHSACHSANPHAM");
            int dem = jsonArrayDsThuongHieu.length();

            for (int i = 0; i < dem; i++) {
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDsThuongHieu.getJSONObject(i);

                sanPham.setTenSp(object.getString("TENSP"));
                sanPham.setGiaBan(Integer.parseInt(object.getString("GIABAN")));
                sanPham.setAnhNho(object.getString("ANHNHO"));
                sanPham.setMasp(Integer.parseInt(object.getString("MASP")));

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


