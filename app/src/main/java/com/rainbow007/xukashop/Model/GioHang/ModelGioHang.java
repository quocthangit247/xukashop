package com.rainbow007.xukashop.Model.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rainbow007.xukashop.Model.ObjectClass.SanPham;

import java.util.ArrayList;
import java.util.List;

public class ModelGioHang {

    SQLiteDatabase database;

    public void MoKetNoiSQL(Context context) {

        DataSanPham dataSanPham = new DataSanPham(context);
        database = dataSanPham.getWritableDatabase();

    }

    public boolean ThemGioHang(SanPham sanPham) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_GioHang_MASP, sanPham.getMasp());
        contentValues.put(DataSanPham.TB_GioHang_TENSP, sanPham.getTenSp());
        contentValues.put(DataSanPham.TB_GioHang_GIATIEN, sanPham.getGiaBan());
        contentValues.put(DataSanPham.TB_GioHang_HINHANH, sanPham.getHinhGioHang());

        long id = database.insert(DataSanPham.TB_GioHang, null, contentValues);
        if (id > 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<SanPham> LayDanhSachSanPhamTrongGioHang() {
        List<SanPham> sanPhamList = new ArrayList<>();

        String truyvan = "Select * from " + DataSanPham.TB_GioHang;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            int masp = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GioHang_MASP));
            String tensp = cursor.getString(cursor.getColumnIndex(DataSanPham.TB_GioHang_TENSP));
            int giasp = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GioHang_GIATIEN));
            byte[] hinhanh = cursor.getBlob(cursor.getColumnIndex(DataSanPham.TB_GioHang_HINHANH));

            SanPham sanPham = new SanPham();
            sanPham.setMasp(masp);
            sanPham.setTenSp(tensp);
            sanPham.setGiaBan(giasp);
            sanPham.setHinhGioHang(hinhanh);

            sanPhamList.add(sanPham);
        }


        return sanPhamList;
    }

}
