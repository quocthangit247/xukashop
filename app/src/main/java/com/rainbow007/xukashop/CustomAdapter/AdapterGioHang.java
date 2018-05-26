package com.rainbow007.xukashop.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.rainbow007.xukashop.Model.GioHang.ModelGioHang;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.GioHang.GioHangActivity;

import java.text.DecimalFormat;
import java.util.List;

import static android.content.Intent.getIntent;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.MyViewHolder> {

    Context context;
    List<SanPham> sanPhamList;
    ModelGioHang modelGioHang;
    int tong = 0;
    String tiensp;
    int remove = 0;

    public int tongHoaDon = 0;

    public AdapterGioHang(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSp, txtGiatien, txtSoLuongSp, txtTienSp;
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
            txtTienSp = itemView.findViewById(R.id.txtTienSp);

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

        final DecimalFormat formatter = new DecimalFormat("#,###,###");
        String giaban = formatter.format(sanPham.getGiaBan());
        holder.txtGiatien.setText(giaban + " VND");

        tong = sanPham.getGiaBan() * modelGioHang.LaySoLuong(sanPham.getMasp());
        if (remove == 0) {
            tongHoaDon += tong;
            Log.d("Tong1:",String.valueOf(tongHoaDon));
            modelGioHang.CapNhatTong(1, tongHoaDon);
            Log.d("Sum1:",String.valueOf( modelGioHang.LayTong(1)));
            GioHangActivity.txtTongTien.setText(String.valueOf( modelGioHang.LayTong(1)) + " VND");
        }
        tiensp = formatter.format(tong);
        holder.txtTienSp.setText(tiensp + " VND");


        byte[] hinhsanpham = sanPham.getHinhGioHang();
        Bitmap HinhGioHang = BitmapFactory.decodeByteArray(hinhsanpham, 0, hinhsanpham.length);
        holder.imgHinhSp.setImageBitmap(HinhGioHang);

        holder.imgXoaGioHang.setTag(sanPham.getMasp());
        holder.imgXoaGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove = 1;
                int count = modelGioHang.LaySoLuong((int)view.getTag());
                Log.d("CountSoLuong:", String.valueOf(count));
                tongHoaDon = tongHoaDon - sanPham.getGiaBan() * count;
                modelGioHang.CapNhatTong(1, tongHoaDon);
                Log.d("Tong1:",String.valueOf(tongHoaDon));
                GioHangActivity.txtTongTien.setText(String.valueOf( modelGioHang.LayTong(1))+ " VND");
                Log.d("Sum2:",String.valueOf( modelGioHang.LayTong(1)));
                Log.d("TongHoaDon:", String.valueOf(tongHoaDon));
               // ModelGioHang modelGioHang = new ModelGioHang();
                modelGioHang.MoKetNoiSQL(context);
                modelGioHang.XoaSanPhamTrongGioHang((int) view.getTag());
                sanPhamList.remove(position);
                notifyDataSetChanged();
                Log.d("Count list:", String.valueOf(sanPhamList.size()));
                if (sanPhamList.size()== 0){
                    remove = 0;
                }

            }
        });

        final int gia = sanPham.getGiaBan();


        holder.txtSoLuongSp.setText(String.valueOf(modelGioHang.LaySoLuong(sanPham.getMasp())));

        holder.imgTangSoluongSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int soluong = Integer.parseInt(holder.txtSoLuongSp.getText().toString());
                soluong++;
                modelGioHang.CapNhatSanPhamTrongGioHang(sanPham.getMasp(), soluong);
                holder.txtSoLuongSp.setText(String.valueOf(soluong));

                tong = gia * soluong;
                tongHoaDon = tongHoaDon + gia;
                modelGioHang.CapNhatTong(1, tongHoaDon);
                Log.d("Tong1:",String.valueOf(tongHoaDon));
                GioHangActivity.txtTongTien.setText(String.valueOf( modelGioHang.LayTong(1))+ " VND");
                Log.d("Sum3:",String.valueOf( modelGioHang.LayTong(1)));
                Log.d("TongHoaDon11111", String.valueOf(tongHoaDon));
                Log.d("So luong:", String.valueOf(sanPham.getSoluong()));
                tiensp = formatter.format(tong);
                holder.txtTienSp.setText(tiensp + " VND");

            }
        });

        holder.imgGiamSoluongSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soluong = Integer.parseInt(holder.txtSoLuongSp.getText().toString());
                if (soluong > 1) {
                    soluong--;
                    tongHoaDon = tongHoaDon - gia;
                    modelGioHang.CapNhatTong(1, tongHoaDon);
                    Log.d("Tong1:",String.valueOf(tongHoaDon));
                    GioHangActivity.txtTongTien.setText(String.valueOf( modelGioHang.LayTong(1))+ " VND");
                    Log.d("Sum4:",String.valueOf( modelGioHang.LayTong(1)));
                    Log.d("TongHoaDon22222X", String.valueOf(tongHoaDon));
                    Log.d("So luong:", String.valueOf(sanPham.getSoluong()));
                }
                modelGioHang.CapNhatSanPhamTrongGioHang(sanPham.getMasp(), soluong);
                holder.txtSoLuongSp.setText(String.valueOf(soluong));

                tong = gia * soluong;
                tiensp = formatter.format(tong);
                holder.txtTienSp.setText(tiensp + " VND");

            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
