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

import com.rainbow007.xukashop.CustomAdapter.AdapterBanChay;
import com.rainbow007.xukashop.Model.ObjectClass.BanChay;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Model.ObjectClass.ThuongHieu;
import com.rainbow007.xukashop.Presenter.TrangChu_BanChay.PresenterLogicBanChay;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.ViewBanChay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rainbow007 on 3/4/18.
 */

public class FragmentBestSeller extends Fragment implements ViewBanChay {

    RecyclerView recyclerView;
    List<BanChay> banChayList;
    PresenterLogicBanChay presenterLogicBanChay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_banchay, container, false);

        recyclerView = view.findViewById(R.id.recyclerBanChay);
        presenterLogicBanChay = new PresenterLogicBanChay(this);

        banChayList = new ArrayList<>();
        presenterLogicBanChay.LayDanhSachBanChay();

        return view;
    }

    @Override
    public void HienThiDanhSach(List<ThuongHieu> thuongHieus, List<SanPham> sanPhams) {

        BanChay banChay = new BanChay();
        banChay.setThuongHieus(thuongHieus);
        banChay.setSanPhams(sanPhams);
        banChayList.add(banChay);

        AdapterBanChay adapterBanChay = new AdapterBanChay(getContext(),banChayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterBanChay);

        adapterBanChay.notifyDataSetChanged();


    }
}

