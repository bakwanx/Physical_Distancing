package com.example.physicaldistancing.fragment;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.physicaldistancing.KalmanFilter;
import com.example.physicaldistancing.R;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkButtonBuilder;
import com.varunest.sparkbutton.SparkEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ScannerFragment extends Fragment {

    private SparkButton btn_active;
    private BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();
    ListView scanListView;
    private Handler handler = new Handler();
    ArrayList<String> stringArrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    private boolean btn = true;
    private KalmanFilter kalmanFilter;
    private static final double KALMAN_R = 0.125d;
    private static final double KALMAN_Q = 0.5d;



    public ScannerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scanner, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_active = getActivity().findViewById(R.id.btn_active);
        scanListView = getActivity().findViewById(R.id.list);
        kalmanFilter = new KalmanFilter(KALMAN_R,KALMAN_Q);

        final Timer timer = new Timer();

        btn_active.setEventListener(new SparkEventListener() {
            @Override
            public void onEvent(ImageView button, final boolean buttonState) {
                if (buttonState){
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            while (true){
                                BTAdapter.startDiscovery();
                            }
                        }
                    },0,4000);
                    //BluetoothScann.run();
                    btn_active.setChecked(true);
                    Toast.makeText(getContext(),"Active",Toast.LENGTH_SHORT).show();
                    btn_active.playAnimation();
                }else{
                    btn_active.setChecked(false);
                    btn_active.playAnimation();
                    Toast.makeText(getContext(),"Diactivate",Toast.LENGTH_SHORT).show();
                    handler.removeCallbacks(vibrate);
                }
            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {

            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }

        });


        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)){
                    double rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    kalmanFilter.applyFilter(rssi);
                    String rrsiString = Double.toString(rssi);
                    if (rssi>-75){
                        //vibrate.run();
                    }
                    stringArrayList.add(rrsiString);
                    arrayAdapter.notifyDataSetChanged();
                }

            }
        };

        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        getActivity().registerReceiver(receiver,intentFilter);
        arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,stringArrayList);
        scanListView.setAdapter(arrayAdapter);


    }



    private Runnable vibrate = new Runnable() {
        @Override
        public void run() {
            Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(1000);

            handler.postDelayed(this,5000);
        }
    };



}