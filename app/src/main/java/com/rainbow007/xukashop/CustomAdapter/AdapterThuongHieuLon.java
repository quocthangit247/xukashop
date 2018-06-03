package com.rainbow007.xukashop.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rainbow007.xukashop.Model.ObjectClass.ThuongHieu;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucAcitivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterThuongHieuLon extends RecyclerView.Adapter<AdapterThuongHieuLon.ViewHolder> {

    Context context;
    List<ThuongHieu> thuongHieus;


    public AdapterThuongHieuLon(Context context, List<ThuongHieu> thuongHieus) {
        this.context = context;
        this.thuongHieus = thuongHieus;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTieuDe;
        ImageView imgThuongHieu;
        ProgressBar progressBar;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTieuDe = itemView.findViewById(R.id.txtTieuDeDuongDa);
            imgThuongHieu = itemView.findViewById(R.id.imgHinhThuongHieu);
            progressBar = itemView.findViewById(R.id.progress_bar_download);
            linearLayout = itemView.findViewById(R.id.linearThuongHieuLon);
        }
    }

    @Override
    public AdapterThuongHieuLon.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recyclerview_thuonghieulon, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AdapterThuongHieuLon.ViewHolder holder, int position) {

        final ThuongHieu thuongHieu = thuongHieus.get(position);

        holder.txtTieuDe.setText(thuongHieu.getTenThuongHieu());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, HienThiSanPhamTheoDanhMucAcitivity.class);
                intent.putExtra("MaThuongHieu", thuongHieu.getMaThuongHieu());
                intent.putExtra("TenThuongHieu", thuongHieu.getTenThuongHieu());
                context.startActivity(intent);
            }
        });


        Picasso.get().load(thuongHieu.getHinhThuongHieu()).resize(150, 150).into(holder.imgThuongHieu, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        Log.d("Adapter", "picaso");
    }

    @Override
    public int getItemCount() {
        return thuongHieus.size();
    }


}
