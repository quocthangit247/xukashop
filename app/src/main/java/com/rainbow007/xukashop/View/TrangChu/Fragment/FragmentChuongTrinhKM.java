package com.rainbow007.xukashop.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rainbow007.xukashop.CustomAdapter.AdapterCTKM;
import com.rainbow007.xukashop.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rainbow007 on 3/4/18.
 */

public class FragmentChuongTrinhKM extends Fragment {

    RecyclerView recyclerView;
    AdapterCTKM adapterCTKM;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_chuongtrinhkm, container, false);

        recyclerView = view.findViewById(R.id.recycleCTKM);
        List<String> data = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            String ten = "Chuong trinh khuyen mai " + i;
            data.add(ten);
        }

        //khoi tao cac instance
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        adapterCTKM = new AdapterCTKM(getActivity(), data);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterCTKM);
        adapterCTKM.notifyDataSetChanged();
        return view;
    }
}
