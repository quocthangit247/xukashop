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

import com.rainbow007.xukashop.CustomAdapter.AdapterDuongDa;
import com.rainbow007.xukashop.Model.ObjectClass.DuongDa;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Model.ObjectClass.ThuongHieu;
import com.rainbow007.xukashop.Presenter.TrangChu_DuongDa.PresenterLogicDuongDa;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.ViewDuongDa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rainbow007 on 3/4/18.
 */

public class FragmentDuongDa extends Fragment implements ViewDuongDa {

    RecyclerView recyclerView;
    List<DuongDa> duongDaList;
    PresenterLogicDuongDa presenterLogicDuongDa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_duongda, container, false);

        recyclerView = view.findViewById(R.id.recyclerDuongDa);
        presenterLogicDuongDa = new PresenterLogicDuongDa(this);

        duongDaList = new ArrayList<>();
        presenterLogicDuongDa.LayDanhSachDuongDa();

        return view;
    }

    @Override
    public void HienThiDanhSach(List<ThuongHieu> thuongHieus, List<SanPham> sanPhams) {

        DuongDa duongDa = new DuongDa();
        duongDa.setThuongHieus(thuongHieus);
        duongDa.setSanPhams(sanPhams);
        duongDaList.add(duongDa);

        AdapterDuongDa adapterDuongDa = new AdapterDuongDa(getContext(),duongDaList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDuongDa);

        adapterDuongDa.notifyDataSetChanged();

    }
}
