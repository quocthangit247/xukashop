package com.rainbow007.xukashop.CustomAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rainbow007.xukashop.R;

import java.util.List;

public class AdapterCTKM extends RecyclerView.Adapter<AdapterCTKM.ViewHolder> {

    Context context;
    List<String> stringList;

    public AdapterCTKM(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    //tiep theo se la ham nay chay (2)
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;


        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtTieuDeCTKM);
        }
    }

    //ham nay chay dau tien
    @NonNull
    @Override
    public AdapterCTKM.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_recycleview_ctkm, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //chay thu 3
    @Override
    public void onBindViewHolder(@NonNull AdapterCTKM.ViewHolder holder, int position) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }


}
