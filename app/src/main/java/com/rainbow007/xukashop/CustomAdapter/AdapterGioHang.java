package com.rainbow007.xukashop.CustomAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.rainbow007.xukashop.Model.GioHang.ModelGioHang;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.R;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.MyViewHolder> {

    Context context;
    List<SanPham> sanPhamList;
    ModelGioHang modelGioHang;

    public AdapterGioHang(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSp, txtGiatien, txtSoLuongSp;
        ImageView imgHinhSp, imgXoaGioHang;
        ImageButton imgTangSoluongSp, imgGiamSoluongSp;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtTenSp = itemView.findViewById(R.id.txtTieuDeSanPhamGioHang);
            txtGiatien = itemView.findViewById(R.id.txtGiaGioHang);
            imgHinhSp = itemView.findViewById(R.id.imgSanPhamGioHang);
            imgXoaGioHang = itemView.findViewById(R.id.imgXoaSanPham);
            txtSoLuongSp = itemView.findViewById(R.id.txtSoLuongSanPham);
            imgTangSoluongSp = itemView.findViewById(R.id.imgTangSoLuong);
            imgGiamSoluongSp = itemView.findViewById(R.id.imgGiamSoLuong);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_giohang, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final SanPham sanPham = sanPhamList.get(position);

        holder.txtTenSp.setText(sanPham.getTenSp());

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String giaban = formatter.format(sanPham.getGiaBan());
        holder.txtGiatien.setText(giaban + " VND");

        byte[] hinhsanpham = sanPham.getHinhGioHang();
        Bitmap HinhGioHang = BitmapFactory.decodeByteArray(hinhsanpham, 0, hinhsanpham.length);
        holder.imgHinhSp.setImageBitmap(HinhGioHang);

        holder.imgXoaGioHang.setTag(sanPham.getMasp());
        holder.imgXoaGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ModelGioHang modelGioHang = new ModelGioHang();
                modelGioHang.MoKetNoiSQL(context);
                modelGioHang.XoaSanPhamTrongGioHang((int) view.getTag());
                sanPhamList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.txtSoLuongSp.setText(String.valueOf(sanPham.getSoluong()));

        holder.imgTangSoluongSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int soluong = Integer.parseInt(holder.txtSoLuongSp.getText().toString());
                soluong++;
                modelGioHang.CapNhatSanPhamTrongGioHang(sanPham.getMasp(), soluong);
                holder.txtSoLuongSp.setText(String.valueOf(soluong));

            }
        });

        holder.imgGiamSoluongSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int soluong = Integer.parseInt(holder.txtSoLuongSp.getText().toString());
                if (soluong > 1) {
                    soluong--;
                }
                holder.txtSoLuongSp.setText(String.valueOf(soluong));

            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
