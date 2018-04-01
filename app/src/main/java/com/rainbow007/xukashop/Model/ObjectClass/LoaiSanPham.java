package com.rainbow007.xukashop.Model.ObjectClass;

import java.util.List;

/**
 * Created by rainbow007 on 3/16/18.
 */

public class LoaiSanPham {

    private String MaLoaiSP;
    private String MaLoaiCha;
    private String TenLoaiSP;
    private List<LoaiSanPham> listCon;

    public String getMaLoaiSP() {
        return MaLoaiSP;
    }

    public void setMaLoaiSP(String maLoaiSP) {
        MaLoaiSP = maLoaiSP;
    }

    public String getMaLoaiCha() {
        return MaLoaiCha;
    }

    public void setMaLoaiCha(String maLoaiCha) {
        MaLoaiCha = maLoaiCha;
    }

    public String getTenLoaiSP() {
        return TenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        TenLoaiSP = tenLoaiSP;
    }

    public List<LoaiSanPham> getListCon() {
        return listCon;
    }

    public void setListCon(List<LoaiSanPham> listCon) {
        this.listCon = listCon;
    }
}
