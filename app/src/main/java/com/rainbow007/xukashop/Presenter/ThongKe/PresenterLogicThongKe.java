package com.rainbow007.xukashop.Presenter.ThongKe;

import com.rainbow007.xukashop.Model.ObjectClass.HoaDon;
import com.rainbow007.xukashop.Model.ObjectClass.ThongKeHoaDon;
import com.rainbow007.xukashop.Model.ThongKe.ModelThongKe;
import com.rainbow007.xukashop.View.ThongKe.ViewThongKe;

import java.util.List;

public class PresenterLogicThongKe implements IPresenterThongKe {

    ViewThongKe viewThongKe;

    private static PresenterLogicThongKe INSTANCE = null;

    private PresenterLogicThongKe() {
    }

    ;

    public static synchronized PresenterLogicThongKe getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PresenterLogicThongKe();

        }
        return (INSTANCE);
    }


//    public PresenterLogicThongKe(ViewThongKe viewThongKe) {
//        this.viewThongKe = viewThongKe;
//    }

    @Override
    public void ThongKeTheoThang(ViewThongKe viewThongKe, String thang) {
        this.viewThongKe = viewThongKe;
        List<ThongKeHoaDon> thongKeHoaDonList = ModelThongKe.getInstance().LayHoaDon(thang);
        if (thongKeHoaDonList.size() > 0) {
            viewThongKe.ThongKeThanhCong(thongKeHoaDonList);
        } else {
            viewThongKe.ThongKeThatBai();
        }

    }
}
