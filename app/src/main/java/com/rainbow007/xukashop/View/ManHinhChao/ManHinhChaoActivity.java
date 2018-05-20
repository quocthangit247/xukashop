package com.rainbow007.xukashop.View.ManHinhChao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rainbow007.xukashop.R;
import com.rainbow007.xukashop.View.TrangChu.TrangChuActivity;

/**
 * Created by rainbow007 on 2/21/18.
 */

public class ManHinhChaoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchao_layout);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {

                } finally {
                    Intent intent = new Intent(ManHinhChaoActivity.this, TrangChuActivity.class);
                    startActivity(intent);
                }
            }
        });
        thread.start();
    }
}
