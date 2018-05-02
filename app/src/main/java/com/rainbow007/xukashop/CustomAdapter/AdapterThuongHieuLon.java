package com.rainbow007.xukashop.CustomAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rainbow007.xukashop.Model.ObjectClass.ThuongHieu;
import com.rainbow007.xukashop.R;
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

        public ViewHolder(View itemView) {
            super(itemView);

            txtTieuDe = itemView.findViewById(R.id.txtTieuDeDuongDa);
            imgThuongHieu = itemView.findViewById(R.id.imgHinhThuongHieu);

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
    public void onBindViewHolder(AdapterThuongHieuLon.ViewHolder holder, int position) {

        ThuongHieu thuongHieu = new ThuongHieu();
        holder.txtTieuDe.setText(thuongHieu.getTenThuongHieu());
        Picasso.get().load(thuongHieu.getHinhThuongHieu()).into(holder.imgThuongHieu);
    }

    @Override
    public int getItemCount() {
        return thuongHieus.size();
    }


}
