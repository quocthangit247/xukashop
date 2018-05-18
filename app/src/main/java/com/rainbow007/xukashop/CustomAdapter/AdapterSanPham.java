package com.rainbow007.xukashop.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterSanPham extends RecyclerView.Adapter<AdapterSanPham.ViewHolder> {

    Context context;
    List<SanPham> sanPhams;
    int layout;

    public AdapterSanPham(Context context, int layout, List<SanPham> sanPhams) {
        this.context = context;
        this.sanPhams = sanPhams;
        this.layout = layout;
    }


    @Override
    public AdapterSanPham.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(sanPhams.get(position));
    }
/*
    @Override
    public void onBindViewHolder(final AdapterSanPham.ViewHolder holder, final int position) {

        final SanPham sanPham = sanPhams.get(position);
        Log.d("masanpham", String.valueOf(sanPham.getMasp()));
        holder.txtTenSP.setText(sanPham.getTenSp());

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String giaban = formatter.format(sanPham.getGiaBan());
        holder.txtGiaBan.setText(giaban + " VND");

        holder.cardView.setTag(sanPham.getMasp());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                intent.putExtra("masp", (int) view.getTag());
                Log.d("ModelSP111111", String.valueOf(sanPham.getMasp()));
                context.startActivity(intent);
            }
        });


        Picasso.get().load(sanPham.getAnhNho()).resize(150, 150).into(holder.imgSP, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        Log.d("AdapterSPDuongDa", "picaso");
    }
*/

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTenSP, txtGiaBan;
        ImageView imgSP;
        ProgressBar progressBar;
        CardView cardView;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTenSP = itemView.findViewById(R.id.txtTieuDeSanPhamDuongDa);
            txtGiaBan = itemView.findViewById(R.id.txtGiaDuongDa);
            imgSP = itemView.findViewById(R.id.imgSanPhamDuongDa);
            progressBar = itemView.findViewById(R.id.progress_bar_download_sp_duongda);
            cardView = itemView.findViewById(R.id.idCardView);
            linearLayout = itemView.findViewById(R.id.linearSanPham);
        }

        public void bindData(final SanPham sanPham) {
            txtTenSP.setText(sanPham.getTenSp());
            DecimalFormat formatter = new DecimalFormat("#,###,###");
            String giaban = formatter.format(sanPham.getGiaBan());
            txtGiaBan.setText(giaban + " VND");
            Picasso.get().load(sanPham.getAnhNho()).resize(150, 150).into(imgSP, new Callback() {
                @Override
                public void onSuccess() {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {

                }
            });
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                    intent.putExtra("masp", sanPham);
                    context.startActivity(intent);
                }
            });
        }
    }

}
