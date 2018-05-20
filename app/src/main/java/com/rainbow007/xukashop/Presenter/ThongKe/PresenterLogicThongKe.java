package com.rainbow007.xukashop.Presenter.ThongKe;

import com.rainbow007.xukashop.Model.ObjectClass.HoaDon;
import com.rainbow007.xukashop.Model.ObjectClass.ThongKeHoaDon;
import com.rainbow007.xukashop.Model.ThongKe.ModelThongKe;
import com.rainbow007.xukashop.View.ThongKe.ViewThongKe;

import java.util.List;

public class PresenterLogicThongKe implements IPresenterThongKe {

    ViewThongKe viewThongKe;
    ModelThongKe modelThongKe;

    public PresenterLogicThongKe(ViewThongKe viewThongKe) {
        this.viewThongKe = viewThongKe;
        modelThongKe = new ModelThongKe();
    }

    @Override
    public void ThongKeTheoThang(String thang) {

        List<ThongKeHoaDon> thongKeHoaDonList = modelThongKe.LayHoaDon(thang);
        if (thongKeHoaDonList.size() > 0) {
            viewThongKe.ThongKeThanhCong(thongKeHoaDonList);
        } else {
            viewThongKe.ThongKeThatBai();
        }

    }
}
