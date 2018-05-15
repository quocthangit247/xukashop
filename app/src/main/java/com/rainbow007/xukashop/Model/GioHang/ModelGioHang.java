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
        contentValues.put(DataSanPham.TB_GioHang_SOLUONG, sanPham.getSoluong());

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
            int soluong = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GioHang_SOLUONG));

            SanPham sanPham = new SanPham();
            sanPham.setMasp(masp);
            sanPham.setTenSp(tensp);
            sanPham.setGiaBan(giasp);
            sanPham.setHinhGioHang(hinhanh);
            sanPham.setSoluong(soluong);

            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }


        return sanPhamList;
    }

    public boolean XoaSanPhamTrongGioHang(int masp) {

        int kiemtra = database.delete(DataSanPham.TB_GioHang, DataSanPham.TB_GioHang_MASP + "=" + masp, null);
        if (kiemtra > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean CapNhatSanPhamTrongGioHang(int masp, int soluong) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_GioHang_SOLUONG, soluong);

        int i = database.update(DataSanPham.TB_GioHang, contentValues, DataSanPham.TB_GioHang_MASP + "=" + masp, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }

    }


}
