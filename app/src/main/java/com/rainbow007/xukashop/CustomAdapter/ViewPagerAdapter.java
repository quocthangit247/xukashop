package com.rainbow007.xukashop.CustomAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rainbow007.xukashop.View.TrangChu.Fragment.FragmentChuongTrinhKhuyenMai;
import com.rainbow007.xukashop.View.TrangChu.Fragment.FragmentDienTu;
import com.rainbow007.xukashop.View.TrangChu.Fragment.FragmentLamdep;
import com.rainbow007.xukashop.View.TrangChu.Fragment.FragmentMevaBe;
import com.rainbow007.xukashop.View.TrangChu.Fragment.FragmentNhaCua;
import com.rainbow007.xukashop.View.TrangChu.Fragment.FragmentNoiBat;
import com.rainbow007.xukashop.View.TrangChu.Fragment.FragmentTheThaoDulich;
import com.rainbow007.xukashop.View.TrangChu.Fragment.FragmentThoiTrang;
import com.rainbow007.xukashop.View.TrangChu.Fragment.FragmentThuongHieu;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rainbow007 on 3/4/18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> listFragment = new ArrayList<Fragment>();
    List<String> titleFragment = new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        listFragment.add(new FragmentNoiBat());
        listFragment.add(new FragmentChuongTrinhKhuyenMai());
        listFragment.add(new FragmentDienTu());
        listFragment.add(new FragmentLamdep());
        listFragment.add(new FragmentMevaBe());
        listFragment.add(new FragmentNhaCua());
        listFragment.add(new FragmentTheThaoDulich());
        listFragment.add(new FragmentThoiTrang());
        listFragment.add(new FragmentThuongHieu());

        titleFragment.add("Noi bat");
        titleFragment.add("CTKM");
        titleFragment.add("Dien tu");
        titleFragment.add("Lam dep");
        titleFragment.add("Me va be");
        titleFragment.add("Nha cua");
        titleFragment.add("The thao va du lich");
        titleFragment.add("Thoi trang");
        titleFragment.add("Thuong hieu");


    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}
