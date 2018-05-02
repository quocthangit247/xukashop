package com.rainbow007.xukashop.View.TrangChu.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rainbow007.xukashop.R;

/**
 * Created by rainbow007 on 3/4/18.
 */

public class FragmentDuongDa extends Fragment {

    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_duongda, container, false);

        recyclerView = view.findViewById(R.id.recyclerDuongDa);


        return view;
    }
}
