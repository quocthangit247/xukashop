package com.rainbow007.xukashop.CustomAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterSanPham extends RecyclerView.Adapter<AdapterSanPham.ViewHolder> {

    Context context;
    List<SanPham> sanPhams;
    int layout;

    public  AdapterSanPham(Context context, int layout, List<SanPham> sanPhams) {
        this.context = context;
        this.sanPhams = sanPhams;
        this.layout = layout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTenSP, txtGiaBan;
        ImageView imgSP;
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTenSP = itemView.findViewById(R.id.txtTieuDeSanPhamDuongDa);
            txtGiaBan = itemView.findViewById(R.id.txtGiaDuongDa);
            imgSP = itemView.findViewById(R.id.imgSanPhamDuongDa);
            progressBar = itemView.findViewById(R.id.progress_bar_download_sp_duongda);
        }
    }

    @Override
    public AdapterSanPham.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AdapterSanPham.ViewHolder holder, int position) {

        SanPham sanPham = sanPhams.get(position);
        holder.txtTenSP.setText(sanPham.getTenSp());

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String giaban = formatter.format(sanPham.getGiaBan());
        holder.txtGiaBan.setText(giaban);

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


    @Override
    public int getItemCount() {
        return sanPhams.size();
    }

}
