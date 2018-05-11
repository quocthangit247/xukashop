package com.rainbow007.xukashop.CustomAdapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rainbow007.xukashop.Model.ObjectClass.NuocHoa;
import com.rainbow007.xukashop.R;

import java.util.List;

public class AdapterNuocHoa extends RecyclerView.Adapter<AdapterNuocHoa.ViewHolder> {

    Context context;
    List<NuocHoa> nuocHoaList;

    public AdapterNuocHoa(Context context, List<NuocHoa> nuocHoaList) {

        this.context = context;
        this.nuocHoaList = nuocHoaList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHinhGiamGia;
        RecyclerView recyclerViewThuongHieu, recyclerViewSanPham;

        public ViewHolder(View itemView) {
            super(itemView);

            imgHinhGiamGia = itemView.findViewById(R.id.imgKhuyenMaiDuongDa);
            recyclerViewThuongHieu = itemView.findViewById(R.id.recyclerThuongHieuLon);
            recyclerViewSanPham = itemView.findViewById(R.id.recyclerSanPham);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recyclerview_nuochoa, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        NuocHoa nuocHoa = nuocHoaList.get(position);

        // xu ly thuong hieu lon
        AdapterThuongHieuLon adapterThuongHieuLon = new AdapterThuongHieuLon(context, nuocHoa.getThuongHieus());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false);

        holder.recyclerViewThuongHieu.setLayoutManager(layoutManager);
        holder.recyclerViewThuongHieu.setAdapter(adapterThuongHieuLon);
        adapterThuongHieuLon.notifyDataSetChanged();

        // xu ly san pham nuoc hoa
        AdapterSanPham adapterSanPham = new AdapterSanPham(context,R.layout.custom_layout_recyclerview_sanpham,nuocHoa.getSanPhams());

        RecyclerView.LayoutManager layoutManagerSanPham = new GridLayoutManager(context,3);
        holder.recyclerViewSanPham.setLayoutManager(layoutManagerSanPham);
        holder.recyclerViewSanPham.setAdapter(adapterSanPham);

    }

    @Override
    public int getItemCount() {
        return nuocHoaList.size();
    }

}
