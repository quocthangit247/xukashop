package com.rainbow007.xukashop.View.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.rainbow007.xukashop.CustomAdapter.ExpandAdapter;
import com.rainbow007.xukashop.CustomAdapter.ViewPagerAdapter;
import com.rainbow007.xukashop.Model.DangNhap.ModelDangNhap;
import com.rainbow007.xukashop.Model.ObjectClass.LoaiSanPham;
import com.rainbow007.xukashop.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.DangNhap.DangNhapActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by rainbow007 on 2/21/18.
 */

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu, AppBarLayout.OnOffsetChangedListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ExpandableListView expandableListView;
    PresenterLogicXuLyMenu logicXuLyMenu;
    String username = "";
    AccessToken accessToken;
    Menu menu;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout
            ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu_layout);

        toolbar = findViewById(R.id.toolBar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpaper);
        drawerLayout = findViewById(R.id.drawserLayout);
        expandableListView = findViewById(R.id.epMenu);
        appBarLayout = findViewById(R.id.appbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        logicXuLyMenu = new PresenterLogicXuLyMenu(this);
        logicXuLyMenu.LayDanhSachMenu();

        appBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);
        this.menu = menu;

        accessToken = logicXuLyMenu.LayTokenNguoiDungFB();
        if (accessToken != null) {
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        username = object.getString("name");
                        MenuItem menuItem = menu.findItem(R.id.icDangnhap);
                        menuItem.setTitle(username);
                        Log.d("token", username);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "name");

            graphRequest.setParameters(parameters);
            graphRequest.executeAsync();

        }

        if (accessToken != null) {

            MenuItem itemDangXuat = menu.findItem(R.id.icDangXuat);
            itemDangXuat.setVisible(true);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        int id = item.getItemId();
        switch (id) {
            case R.id.icDangnhap:
                if (accessToken == null) {
                    Intent intent = new Intent(this, DangNhapActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.icDangXuat:
                if (accessToken != null) {
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(menu);
                }
        }

        return true;
    }

    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
        ExpandAdapter expandAdapter = new ExpandAdapter(this, loaiSanPhamList);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if(collapsingToolbarLayout.getHeight()+verticalOffset <= 1.5 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)){
            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(0).setDuration(200);
        } else {
            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(1).setDuration(200);
        }
    }
}
