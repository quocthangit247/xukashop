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

import com.rainbow007.xukashop.CustomAdapter.AdapterTrangDiem;
import com.rainbow007.xukashop.Model.ObjectClass.TrangDiem;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Model.ObjectClass.ThuongHieu;
import com.rainbow007.xukashop.Presenter.TrangChu_TrangDiem.PresenterLogicTrangDiemMoi;
import com.rainbow007.xukashop.Presenter.TrangChu_TrangDiem.PresenterLogicTrangDiemMat;
import com.rainbow007.xukashop.Presenter.TrangChu_TrangDiem.PresenterLogicTrangDiemFace;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.ViewTrangDiem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rainbow007 on 3/4/18.
 */

public class FragmentTrangDiem extends Fragment implements ViewTrangDiem {

    RecyclerView recyclerView;
    List<TrangDiem> trangDiemList;
    PresenterLogicTrangDiemMoi presenterLogicTrangDiemMoi;
    PresenterLogicTrangDiemMat presenterLogicTrangDiemMat;
    PresenterLogicTrangDiemFace presenterLogicTrangDiemFace;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_trangdiem, container, false);

        recyclerView = view.findViewById(R.id.recyclerTrangDiem);
        presenterLogicTrangDiemMoi = new PresenterLogicTrangDiemMoi(this);
        presenterLogicTrangDiemMat = new PresenterLogicTrangDiemMat(this);
        presenterLogicTrangDiemFace = new PresenterLogicTrangDiemFace(this);

        trangDiemList = new ArrayList<>();
        presenterLogicTrangDiemMoi.LayDanhSachTrangDiemMoi();
        presenterLogicTrangDiemMat.LayDanhSachTrangDiemMat();
        presenterLogicTrangDiemFace.LayDanhSachTrangDiemFace();

        return view;
    }

    @Override
    public void HienThiDanhSach(List<ThuongHieu> thuongHieus, List<SanPham> sanPhams) {

        TrangDiem trangDiem = new TrangDiem();
        trangDiem.setThuongHieus(thuongHieus);
        trangDiem.setSanPhams(sanPhams);
        trangDiemList.add(trangDiem);

        AdapterTrangDiem adapterTrangDiem = new AdapterTrangDiem(getContext(),trangDiemList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTrangDiem);

        adapterTrangDiem.notifyDataSetChanged();


    }
}
