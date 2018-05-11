package com.rainbow007.xukashop.CustomAdapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rainbow007.xukashop.Model.ObjectClass.TrangDiem;
import com.rainbow007.xukashop.R;

import java.util.List;

public class AdapterTrangDiem extends RecyclerView.Adapter<AdapterTrangDiem.ViewHolder> {

    Context context;
    List<TrangDiem> trangDiemList;

    public AdapterTrangDiem(Context context, List<TrangDiem> trangDiemList) {

        this.context = context;
        this.trangDiemList = trangDiemList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHinhGiamGia;
        RecyclerView recyclerViewThuongHieu, recyclerViewSanPhamMoi, recyclerViewSanPhamMat, recyclerViewSanPhamFace;

        public ViewHolder(View itemView) {
            super(itemView);

            imgHinhGiamGia = itemView.findViewById(R.id.imgKhuyenMaiDuongDa);
            recyclerViewThuongHieu = itemView.findViewById(R.id.recyclerThuongHieuLon);
            recyclerViewSanPhamMoi = itemView.findViewById(R.id.recyclerSanPhamMoi);
            recyclerViewSanPhamMat = itemView.findViewById(R.id.recyclerSanPhamMat);
            recyclerViewSanPhamFace = itemView.findViewById(R.id.recyclerSanPhamFace);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recyclerview_trangdiem, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TrangDiem trangDiem = trangDiemList.get(position);

        // xu ly thuong hieu lon
        AdapterThuongHieuLon adapterThuongHieuLon = new AdapterThuongHieuLon(context, trangDiem.getThuongHieus());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false);

        holder.recyclerViewThuongHieu.setLayoutManager(layoutManager);
        holder.recyclerViewThuongHieu.setAdapter(adapterThuongHieuLon);
        adapterThuongHieuLon.notifyDataSetChanged();

        // xu ly san pham trang diem
        AdapterSanPham adapterSanPhamMoi = new AdapterSanPham(context,R.layout.custom_layout_recyclerview_sanpham,trangDiem.getSanPhams());

        RecyclerView.LayoutManager layoutManagerSanPham = new GridLayoutManager(context,3);
        holder.recyclerViewSanPhamMoi.setLayoutManager(layoutManagerSanPham);
        holder.recyclerViewSanPhamMoi.setAdapter(adapterSanPhamMoi);

        //
        AdapterSanPham adapterSanPhamMat = new AdapterSanPham(context,R.layout.custom_layout_recyclerview_sanpham,trangDiem.getSanPhams());

        RecyclerView.LayoutManager layoutManagerSanPhamMat = new GridLayoutManager(context,3);
        holder.recyclerViewSanPhamMat.setLayoutManager(layoutManagerSanPhamMat);
        holder.recyclerViewSanPhamMat.setAdapter(adapterSanPhamMat);

        //
        AdapterSanPham adapterSanPhamFace = new AdapterSanPham(context,R.layout.custom_layout_recyclerview_sanpham,trangDiem.getSanPhams());

        RecyclerView.LayoutManager layoutManagerSanPhamFace = new GridLayoutManager(context,3);
        holder.recyclerViewSanPhamFace.setLayoutManager(layoutManagerSanPhamFace);
        holder.recyclerViewSanPhamFace.setAdapter(adapterSanPhamFace);

    }

    @Override
    public int getItemCount() {
        return trangDiemList.size();
    }

}
