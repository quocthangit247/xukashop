package com.rainbow007.xukashop.CustomAdapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.rainbow007.xukashop.Model.ObjectClass.LoaiSanPham;
import com.rainbow007.xukashop.R;

import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context context;
    List<LoaiSanPham> loaiSanPhams;

    public ExpandAdapter(Context context, List<LoaiSanPham> loaiSanPhams) {
        this.context = context;
        this.loaiSanPhams = loaiSanPhams;
    }

    @Override
    public int getGroupCount() {
        return loaiSanPhams.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha).getListCon().size();
    }

    @Override
    public Object getGroup(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha);
    }

    @Override
    public Object getChild(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getListCon().get(vitriGroupCon);
    }

    @Override
    public long getGroupId(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha).getMaLoaiSP();
    }

    @Override
    public long getChildId(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getListCon().get(vitriGroupCon).getMaLoaiSP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int vitriGroupCha, boolean isExpanded, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        View viewGroupCha = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_group_cha, parent, false);

            // set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) viewGroupCha.findViewById(R.id.txtLoaiSP);

            // store the holder with the view
            viewGroupCha.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text.setText(loaiSanPhams.get(vitriGroupCha).getTenLoaiSP());

        return viewGroupCha;
    }

    @Override
    public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isLastChild, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        View viewGroupCha = null;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_group_cha, parent, false);

            // set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) viewGroupCha.findViewById(R.id.txtLoaiSP);

            // store the holder with the view
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text.setText(loaiSanPhams.get(vitriGroupCha).getTenLoaiSP());
        return viewGroupCha;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class ViewHolder {
        TextView text;
        TextView timestamp;
    }
}
