package com.rainbow007.xukashop.CustomAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.rainbow007.xukashop.View.DangNhap.Fragment.FragmentDangKi;
import com.rainbow007.xukashop.View.DangNhap.Fragment.FragmentDangNhap;
import static android.content.ContentValues.TAG;


public class ViewPagerAdapterDangNhap extends FragmentPagerAdapter {

    public ViewPagerAdapterDangNhap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Log.d(TAG, "getItem: ");
                FragmentDangNhap fragmentDangNhap = new FragmentDangNhap();
                return fragmentDangNhap;
            case 1:
                FragmentDangKi fragmentDangKi = new FragmentDangKi();
                return fragmentDangKi;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Đăng nhập";
            case 1:
                return "Đăng kí";
            default:
                return null;
        }
    }
}
