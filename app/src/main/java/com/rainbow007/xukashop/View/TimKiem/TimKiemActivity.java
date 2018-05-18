package com.rainbow007.xukashop.View.TimKiem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rainbow007.xukashop.CustomAdapter.AdapterSanPham;
import com.rainbow007.xukashop.Model.ObjectClass.ILoadMore;
import com.rainbow007.xukashop.Model.ObjectClass.LoadMoreScroll;
import com.rainbow007.xukashop.Model.ObjectClass.SanPham;
import com.rainbow007.xukashop.Presenter.TimKiem.PresenterLogicTimKiem;
import com.rainbow007.xukashop.R;

import java.util.List;

public class TimKiemActivity extends AppCompatActivity implements ViewTimKiem,ILoadMore,SearchView.OnQueryTextListener {

    Toolbar toolbar;
    RecyclerView recyclerView;
    PresenterLogicTimKiem presenterLogicTimKiem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timkiem);

        toolbar = findViewById(R.id.toolBarSearch);
        recyclerView = findViewById(R.id.recyclerSearch);

        setSupportActionBar(toolbar);

        presenterLogicTimKiem = new PresenterLogicTimKiem(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem, menu);

        MenuItem itemSearch = menu.findItem(R.id.icSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(itemSearch);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public void TimKiemThanhCong(List<SanPham> sanPhamList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterSanPham adapterSanPham = new AdapterSanPham(this, R.layout.custom_layout_list_sanphamtheodanhmuc, sanPhamList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterSanPham);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager,this));

        adapterSanPham.notifyDataSetChanged();

    }

    @Override
    public void TimKiemThatBai() {

    }

    @Override
    public void LoadMore(int tongItem) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenterLogicTimKiem.TimKiemSanPhamTheoTenSp(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
