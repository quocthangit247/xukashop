package com.rainbow007.xukashop.Model.ThanhToan;

import android.util.Log;

import com.rainbow007.xukashop.ConnectInternet.DownloadJSON;
import com.rainbow007.xukashop.Model.ObjectClass.ChiTietHoaDon;
import com.rainbow007.xukashop.Model.ObjectClass.HoaDon;
import com.rainbow007.xukashop.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelThanhToan {

    public boolean ThemHoaDon(HoaDon hoaDon){

        String duongdan = TrangChuActivity.SERVER_NAME;
        boolean kiemtra = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","ThemHoaDon");

        List<ChiTietHoaDon> chiTietHoaDonList = hoaDon.getChiTietHoaDonList();

        String chuoijson = "{\"DANHSACHSANPHAM\" :[ ";
        for (int i=0; i<chiTietHoaDonList.size();i++){
            chuoijson += "{";
            chuoijson += "\"masp\" : " + chiTietHoaDonList.get(i).getMaSp() + ",";
            chuoijson += "\"soluong\" : " + chiTietHoaDonList.get(i).getSoluong();

            if(i==chiTietHoaDonList.size() -1 ){
                chuoijson += "}";
            }else{
                chuoijson += "},";
            }

        }

        chuoijson += "]}";

        HashMap<String,String> hsDanhSachSanPham = new HashMap<>();
        hsDanhSachSanPham.put("danhsachsanpham",chuoijson);

        HashMap<String,String> hsTenKH = new HashMap<>();
        hsTenKH.put("tenkhachhang",hoaDon.getTenKH());

        HashMap<String,String> hsSoDT = new HashMap<>();
        hsSoDT.put("sodt",String.valueOf(hoaDon.getSdt()));

        HashMap<String,String> hsDiaChi = new HashMap<>();
        hsDiaChi.put("diachi", hoaDon.getDiaChiGiaoHang());

        HashMap<String,String> hsChuyenKhoan = new HashMap<>();
        hsChuyenKhoan.put("chuyenkhoan","0");

        attrs.add(hsHam);
        attrs.add(hsDanhSachSanPham);
        attrs.add(hsTenKH);
        attrs.add(hsSoDT);
        attrs.add(hsDiaChi);
        attrs.add(hsChuyenKhoan);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            Log.d("kiemtra",ketqua);
            if(ketqua.equals("true")){
                kiemtra = true;
            }else{
                kiemtra = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }

}
