package com.rainbow007.xukashop.Model.DangNhap_DangKy;

import android.util.Log;

import com.rainbow007.xukashop.ConnectInternet.DownloadJSON;
import com.rainbow007.xukashop.Model.ObjectClass.TaiKhoan;
import com.rainbow007.xukashop.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangKy {

    public Boolean DangKyThanhVien(TaiKhoan taiKhoan) {
        String duongdan = TrangChuActivity.SERVER_NAME;
        boolean ktra = false;

        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsham = new HashMap<>();
        hsham.put("ham", "DangKyThanhVien");

        HashMap<String, String> hsHoten = new HashMap<>();
        hsHoten.put("hoten", taiKhoan.getHoten());

        HashMap<String, String> hsEmail = new HashMap<>();
        hsEmail.put("email", taiKhoan.getEmail());

        HashMap<String, String> hsPassword = new HashMap<>();
        hsPassword.put("password", taiKhoan.getEmail());

        HashMap<String, String> hsEmailDocQuyen = new HashMap<>();
        hsEmailDocQuyen.put("emaildocquyen", taiKhoan.getEmailDocQuyen());

        attrs.add(hsham);
        attrs.add(hsHoten);
        attrs.add(hsEmail);
        attrs.add(hsPassword);
        attrs.add(hsEmailDocQuyen);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String kq = jsonObject.getString("ketqua");
            if (kq.equals("true")) {
                ktra = true;
            } else {
                ktra = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ktra;
    }
}
