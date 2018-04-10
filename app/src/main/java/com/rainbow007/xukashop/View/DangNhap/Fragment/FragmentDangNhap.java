package com.rainbow007.xukashop.View.DangNhap.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rainbow007.xukashop.R;

import static android.content.ContentValues.TAG;

public class FragmentDangNhap extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);
    }
}
