package com.example.physicaldistancing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.physicaldistancing.model.CoronaModel;
import com.example.physicaldistancing.model.NewsModel;

import org.json.JSONArray;
import org.json.JSONException;

public class Setting extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView positif, meninggal, sembuh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        seekBar = findViewById(R.id.rssi_seekbar);
        positif = findViewById(R.id.positif_view);
        meninggal = findViewById(R.id.meninggal_view);
        sembuh = findViewById(R.id.sembuh_view);



        String result = "";

        try {
            JSONArray jsonArray = new JSONArray(result);
            
        } catch (JSONException e) {
            e.printStackTrace();
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}