package com.rainbow007.xukashop.CustomAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rainbow007.xukashop.View.TrangChu.Fragment.FragmentBestSeller;
import com.rainbow007.xukashop.View.TrangChu.Fragment.FragmentDuongDa;
import com.rainbow007.xukashop.View.TrangChu.Fragment.FragmentNuocHoa;
import com.rainbow007.xukashop.View.TrangChu.Fragment.FragmentPhuKien;
import com.rainbow007.xukashop.View.TrangChu.Fragment.FragmentTrangDiem;
import com.rainbow007.xukashop.View.TrangChu.Fragment.FragmentTrending;
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

        listFragment.add(new FragmentBestSeller());
        listFragment.add(new FragmentTrending());
        listFragment.add(new FragmentDuongDa());
        listFragment.add(new FragmentTrangDiem());
        listFragment.add(new FragmentNuocHoa());
        listFragment.add(new FragmentPhuKien());
        listFragment.add(new FragmentThuongHieu());

        titleFragment.add("Sản phẩm mua nhiều");
        titleFragment.add("Xu thế");
        titleFragment.add("Dưỡng da");
        titleFragment.add("Trang điểm");
        titleFragment.add("Nước hoa");
        titleFragment.add("Phụ kiện");
        titleFragment.add("Thương hiệu");


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
