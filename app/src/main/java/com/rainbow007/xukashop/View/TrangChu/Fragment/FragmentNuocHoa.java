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

import com.rainbow007.xukashop.CustomAdapter.AdapterNuocHoa;
import com.rainbow007.xukashop.Model.ObjectClass.NuocHoa;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Model.ObjectClass.ThuongHieu;
import com.rainbow007.xukashop.Presenter.TrangChu_NuocHoa.PresenterLogicNuocHoa;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.ViewNuocHoa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rainbow007 on 3/4/18.
 */

public class FragmentNuocHoa extends Fragment implements ViewNuocHoa {

    RecyclerView recyclerView;
    List<NuocHoa> nuocHoaList;
    PresenterLogicNuocHoa presenterLogicNuocHoa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_nuochoa, container, false);

        recyclerView = view.findViewById(R.id.recyclerNuocHoa);
        presenterLogicNuocHoa = new PresenterLogicNuocHoa(this);

        nuocHoaList = new ArrayList<>();
        presenterLogicNuocHoa.LayDanhSachNuocHoa();

        return view;
    }

    @Override
    public void HienThiDanhSach(List<ThuongHieu> thuongHieus, List<SanPham> sanPhams) {

        NuocHoa nuocHoa = new NuocHoa();
        nuocHoa.setThuongHieus(thuongHieus);
        nuocHoa.setSanPhams(sanPhams);
        nuocHoaList.add(nuocHoa);

        AdapterNuocHoa adapterNuocHoa = new AdapterNuocHoa(getContext(),nuocHoaList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterNuocHoa);

        adapterNuocHoa.notifyDataSetChanged();


    }
}
