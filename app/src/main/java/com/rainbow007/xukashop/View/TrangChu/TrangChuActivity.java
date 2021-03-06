package com.rainbow007.xukashop.View.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.rainbow007.xukashop.CustomAdapter.ViewPagerAdapter;
import com.rainbow007.xukashop.Model.DangNhap_DangKy.ModelDangNhap;
import com.rainbow007.xukashop.Model.ObjectClass.LoaiSanPham;
import com.rainbow007.xukashop.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.rainbow007.xukashop.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.DangNhap_DangKy.DangNhapActivity;
import com.rainbow007.xukashop.View.GioHang.GioHangActivity;
import com.rainbow007.xukashop.View.ThongKe.ThongKeActivity;
import com.rainbow007.xukashop.View.TimKiem.TimKiemActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by rainbow007 on 2/21/18.
 */

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu, GoogleApiClient.OnConnectionFailedListener, AppBarLayout.OnOffsetChangedListener {

    public static final String SERVER_NAME = "http://xukashop.pe.hu/php/loaisanpham.php";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ExpandableListView expandableListView;
    PresenterLogicXuLyMenu logicXuLyMenu;
    String username = "";
    AccessToken accessToken;
    Button btnSearch;
    Menu menu;
    ModelDangNhap modelDangNhap;
    MenuItem itemDangNhap, itemDangXuat,itemThongKe;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInResult googleSignInResult;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    TextView txtGioHang;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    boolean onPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu_layout);

        toolbar = findViewById(R.id.toolBar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpaper);
        drawerLayout = findViewById(R.id.drawserLayout);
//        expandableListView = findViewById(R.id.epMenu);
        appBarLayout = findViewById(R.id.appbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        btnSearch = findViewById(R.id.btnSearch);


        toolbar.setTitle("");
        setSupportActionBar(toolbar);

//        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
////        drawerLayout.addDrawerListener(drawerToggle);
////
////        getSupportActionBar().setHomeButtonEnabled(true);
////        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
////        drawerToggle.syncState();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        logicXuLyMenu = new PresenterLogicXuLyMenu(this);
        modelDangNhap = new ModelDangNhap();

        logicXuLyMenu.LayDanhSachMenu();
        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(this, this);

        appBarLayout.addOnOffsetChangedListener(this);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChuActivity.this, TimKiemActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);
        this.menu = menu;

        MenuItem itemGioHang = menu.findItem(R.id.icCart);
        View giaoDienCustomGioHang = itemGioHang.getActionView();
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);

        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TrangChuActivity.this, GioHangActivity.class);
                startActivity(intent);

            }
        });

        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));

        itemDangNhap = menu.findItem(R.id.icDangnhap);
        itemDangXuat = menu.findItem(R.id.icDangXuat);
        itemThongKe = menu.findItem(R.id.icThongKe);

        accessToken = logicXuLyMenu.LayTokenNguoiDungFB();
        googleSignInResult = modelDangNhap.LayThongTinDangNhapGG(mGoogleApiClient);

        if (accessToken != null) {
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        username = object.getString("name");
                        itemDangNhap.setTitle(username);
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

        if (googleSignInResult != null) {
            itemDangNhap.setTitle(googleSignInResult.getSignInAccount().getDisplayName());
        }

        String hotenUser = modelDangNhap.LayCacheDangNhap(this);
        if (!hotenUser.equals("")) {
            itemDangNhap.setTitle(hotenUser);
        }

        if (accessToken != null || googleSignInResult != null || !hotenUser.equals("")) {
            itemDangXuat.setVisible(true);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        if (drawerToggle.onOptionsItemSelected(item))
//            return true;

        int id = item.getItemId();
        switch (id) {
            case R.id.icDangnhap:
                if (accessToken == null && googleSignInResult == null && modelDangNhap.LayCacheDangNhap(this).equals("")) {
                    Intent intent = new Intent(this, DangNhapActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.icDangXuat:

                //this is logout FB
                if (accessToken != null) {
                    LoginManager.getInstance().logOut();

                    //Log out thi refresh lai menu
                    this.menu.clear();
                    this.onCreateOptionsMenu(menu);
                }

                // this is logout GG
                if (googleSignInResult != null) {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                    //Log out thi refresh lai menu
                    this.menu.clear();
                    this.onCreateOptionsMenu(menu);
                }

                if (!modelDangNhap.LayCacheDangNhap(this).equals("")) {
                    modelDangNhap.UpdateCacheDangNhap(this, "");
                    this.menu.clear();
                    this.onCreateOptionsMenu(menu);
                }
                break;
            case R.id.icSearch:
                Intent intent = new Intent(TrangChuActivity.this, TimKiemActivity.class);
                startActivity(intent);
                break;
            case R.id.icThongKe:
                Intent intentThongKe = new Intent(TrangChuActivity.this, ThongKeActivity.class);
                startActivity(intentThongKe);
                break;
        }

        return true;
    }

    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
//        ExpandAdapter expandAdapter = new ExpandAdapter(this, loaiSanPhamList);
//        expandableListView.setAdapter(expandAdapter);
//        expandAdapter.notifyDataSetChanged();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (collapsingToolbarLayout.getHeight() + verticalOffset <= 1.5 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)) {
            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(0).setDuration(200);

            MenuItem itemSearch = menu.findItem(R.id.icSearch);
            itemSearch.setVisible(true);

        } else {
            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(1).setDuration(200);

            try {

                MenuItem itemSearch = menu.findItem(R.id.icSearch);
                itemSearch.setVisible(true);

            } catch (Exception e) {

            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (onPause) {
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        onPause = true;
    }
}
