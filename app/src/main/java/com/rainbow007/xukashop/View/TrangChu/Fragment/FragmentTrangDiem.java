package com.rainbow007.xukashop.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rainbow007.xukashop.R;

/**
 * Created by rainbow007 on 3/4/18.
 */

public class FragmentTrangDiem extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_trangdiem, container, false);
        return view;    }
}
