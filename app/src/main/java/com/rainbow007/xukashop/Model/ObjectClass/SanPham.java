package com.rainbow007.xukashop.Model.ObjectClass;

import android.os.Parcel;
import android.os.Parcelable;

public class SanPham implements Parcelable{

    private int masp;
    private int giaNhap;
    private int giaBan;
    private int soluong;
    private String tenSp;
    private String anhNho;
    private String anhLon;
    private String thongTin;
    private String maLoaiSp;
    private String maLoaiThuongHieu;
    private byte[] hinhGioHang;

    public SanPham() {
    }

    protected SanPham(Parcel in) {
        masp = in.readInt();
        giaNhap = in.readInt();
        giaBan = in.readInt();
        soluong = in.readInt();
        tenSp = in.readString();
        anhNho = in.readString();
        anhLon = in.readString();
        thongTin = in.readString();
        maLoaiSp = in.readString();
        maLoaiThuongHieu = in.readString();
    }

    public static final Creator<SanPham> CREATOR = new Creator<SanPham>() {
        @Override
        public SanPham createFromParcel(Parcel in) {
            return new SanPham(in);
        }

        @Override
        public SanPham[] newArray(int size) {
            return new SanPham[size];
        }
    };

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getAnhNho() {
        return anhNho;
    }

    public void setAnhNho(String anhNho) {
        this.anhNho = anhNho;
    }

    public String getAnhLon() {
        return anhLon;
    }

    public void setAnhLon(String anhLon) {
        this.anhLon = anhLon;
    }

    public String getThongTin() {
        return thongTin;
    }

    public void setThongTin(String thongTin) {
        this.thongTin = thongTin;
    }

    public String getMaLoaiSp() {
        return maLoaiSp;
    }

    public void setMaLoaiSp(String maLoaiSp) {
        this.maLoaiSp = maLoaiSp;
    }

    public String getMaLoaiThuongHieu() {
        return maLoaiThuongHieu;
    }

    public void setMaLoaiThuongHieu(String maLoaiThuongHieu) {
        this.maLoaiThuongHieu = maLoaiThuongHieu;
    }

    public byte[] getHinhGioHang() {
        return hinhGioHang;
    }

    public void setHinhGioHang(byte[] hinhGioHang) {
        this.hinhGioHang = hinhGioHang;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(masp);
        parcel.writeInt(giaNhap);
        parcel.writeInt(giaBan);
        parcel.writeInt(soluong);
        parcel.writeString(tenSp);
        parcel.writeString(anhNho);
        parcel.writeString(anhLon);
        parcel.writeString(thongTin);
        parcel.writeString(maLoaiSp);
        parcel.writeString(maLoaiThuongHieu);
    }

}
