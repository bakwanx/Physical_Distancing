package com.example.physicaldistancing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Setting extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView tv_Value;
    private Button btn_save;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setTitle("Setting");

        seekBar = findViewById(R.id.rssi_seekbar);
        tv_Value = findViewById(R.id.tv_threeshold);
        btn_save = findViewById(R.id.btn_save);
        seekBar.setMax(100);
        final Intent intent = new Intent(Setting.this, MainActivity.class);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                seekBar.setProgress(progress);
                tv_Value.setText(""+progress);
                intent.putExtra("kirim",progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }


}