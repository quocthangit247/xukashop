package com.rainbow007.xukashop.Model.GioHang;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataSanPham extends SQLiteOpenHelper {

    public static String TB_GioHang = "GIOHANG";
    public static String TB_GioHang_MASP = "MASP";
    public static String TB_GioHang_TENSP = "TENSP";
    public static String TB_GioHang_GIATIEN = "GIATIEN";
    public static String TB_GioHang_HINHANH = "HINHANH";
    public static String TB_GioHang_SOLUONG = "SOLUONG";

    public static String TB_YeuThich = "YEUTHICH";
    public static String TB_YeuThich_MASP = "MASP";
    public static String TB_YeuThich_TENSP = "TENSP";
    public static String TB_YeuThich_GIATIEN = "GIATIEN";
    public static String TB_YeuThich_HINHANH = "HINHANH";

    public DataSanPham(Context context) {
        super(context, "SQLSANPHAM", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbGioHang = "CREATE TABLE " + TB_GioHang + " (" + TB_GioHang_MASP + " INTEGER PRIMARY KEY , "
                + TB_GioHang_TENSP + " TEXT, " + TB_GioHang_GIATIEN + " REAL, " +TB_GioHang_HINHANH + "  BLOB, "
                + TB_GioHang_SOLUONG + " INTEGER);";
        String tbYeuThich = "CREATE TABLE " + TB_YeuThich + "(" + TB_YeuThich_MASP + " INTEGER PRIMARY KEY" +
                ", " + TB_YeuThich_TENSP + " TEXT, " + TB_YeuThich_GIATIEN + " REAL, " + TB_YeuThich_HINHANH + " BLOB);";

        db.execSQL(tbGioHang);
        db.execSQL(tbYeuThich);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
