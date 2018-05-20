package com.rainbow007.xukashop.View.ThongKe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.rainbow007.xukashop.Model.ObjectClass.ThongKeHoaDon;
import com.rainbow007.xukashop.Presenter.ThongKe.PresenterLogicThongKe;
import com.rainbow007.xukashop.R;

import java.util.ArrayList;
import java.util.List;


public class ThongKeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ViewThongKe {

    String month;
    String months[] = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};
    AppCompatSpinner spinner;
    TextView textView;
    PresenterLogicThongKe presenterLogicThongKe;
    PieChart pieChart;
    PieData data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongke);

        spinner = findViewById(R.id.spinnerThongKe);
        textView = findViewById(R.id.txtThang);
        pieChart = findViewById(R.id.piechart);


        //Gán Data source (arr) vào Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, months);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spinner.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        spinner.setOnItemSelectedListener(this);


        pieChart.setUsePercentValues(true);


        presenterLogicThongKe = new PresenterLogicThongKe(this);


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        textView.setText(months[i]);
        switch (months[i]) {
            case "Tháng 1":
                month = "1";
                break;
            case "Tháng 2":
                month = "2";
                break;
            case "Tháng 3":
                month = "3";
                break;
            case "Tháng 4":
                month = "4";
                break;
            case "Tháng 5":
                month = "5";
                break;
            case "Tháng 6":
                month = "6";
                break;
            case "Tháng 7":
                month = "7";
                break;
            case "Tháng 8":
                month = "8";
                break;
            case "Tháng 9":
                month = "9";
                break;
            case "Tháng 10":
                month = "10";
                break;
            case "Tháng 11":
                month = "11";
                break;
            case "Tháng 12":
                month = "12";
                break;
        }
        presenterLogicThongKe.ThongKeTheoThang(month);
        Toast.makeText(this, month, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void ThongKeThanhCong(List<ThongKeHoaDon> thongKeHoaDonList) {

        float tong = 0;
        int dem = thongKeHoaDonList.size();
        for (int i = 0; i < dem; i++) {
            tong += thongKeHoaDonList.get(i).getSoluong();
        }
        Log.d("ThongKeAct", String.valueOf(tong));

        float tuan1 = thongKeHoaDonList.get(0).getSoluong();
        float tuan2 = thongKeHoaDonList.get(1).getSoluong();
        float tuan3 = thongKeHoaDonList.get(2).getSoluong();
        float tuan4 = thongKeHoaDonList.get(3).getSoluong();

         ArrayList<String> week = new ArrayList<String>();

         week.add("Tuần 1");
         week.add("Tuần 2");
         week.add("Tuần 3");
         week.add("Tuần 4");

        ArrayList<PieEntry> values = new ArrayList<>();
        values.add(new PieEntry(tuan1 / tong, week.get(0)));
        values.add(new PieEntry(tuan2 / tong, week.get(1)));
        values.add(new PieEntry(tuan3 / tong, week.get(2)));
        values.add(new PieEntry(tuan4 / tong, week.get(3)));


        PieDataSet dataSet = new PieDataSet(values, "Hoá đơn");

        data = new PieData(dataSet);

        data.setValueFormatter(new PercentFormatter());
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        data.setValueTextSize(20f);

        pieChart.setData(data);
        pieChart.invalidate();
    }

    @Override
    public void ThongKeThatBai() {

    }
}
