package com.example.s3000pro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.SwitchCompat;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.ConsumerIrManager;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    ImageButton volumePlus;
    ImageButton volumeMinus;
    ImageButton next;
    ImageButton previous;
    ImageButton  play_pause;
    ImageButton  bluetooth;
    ImageButton  power;
    ImageButton  usb;
    ImageButton  mute;
    ImageButton  opticalcoaxial;
    ImageButton  line1_2;
    ImageButton  monitor;
    ImageButton  dynamic;
    ImageButton  classic;
    ImageButton  vocal;
    boolean state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        volumePlus = (ImageButton) findViewById(R.id.plus);
        volumeMinus = (ImageButton) findViewById(R.id.minus);
        next = (ImageButton) findViewById(R.id.next);
        usb = (ImageButton) findViewById(R.id.usb);
        mute = (ImageButton) findViewById(R.id.mute);
        opticalcoaxial = (ImageButton) findViewById(R.id.optcoax);
        previous = (ImageButton) findViewById(R.id.previous);
        play_pause = (ImageButton) findViewById(R.id.playpause);
        line1_2 = (ImageButton) findViewById(R.id.line12);
        bluetooth = (ImageButton) findViewById(R.id.bluetooth);
        power = (ImageButton) findViewById(R.id.power);
        monitor = (ImageButton) findViewById(R.id.monitor);
        dynamic = (ImageButton) findViewById(R.id.dynamic);
        classic = (ImageButton) findViewById(R.id.classic);
        vocal = (ImageButton) findViewById(R.id.vocal);
        final Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        final String errIr = "The device is not equipped with an IR port";
        final ConsumerIrManager consumerIrManager = (ConsumerIrManager) this.getSystemService(Context.CONSUMER_IR_SERVICE);
        final int frequency = 36000;
        final int vibratemilsec = 50;
        final int delaysender = 150;
        final int[] powerCode = new int[]{8950,4450, 600,500, 600,550, 600,500, 600,550, 600,1650, 600,500, 600,550, 600,500, 650,1600, 650,1600, 600,1650, 650,500, 600,500, 600,1650, 600,1650, 600,1650, 600,1650, 600,1650, 600,550, 550,550, 600,550, 600,500, 600,1650, 600,550, 550,550, 600,550, 550,1700, 550,1700, 550,1700, 550,1700, 550,550, 600,1700, 550};  // NEC 8E7C23D
        final int[] vplusCode = new int[]{8950,4450, 600,550, 550,550, 600,550, 550,550, 600,1650, 600,500, 650,500, 600,500, 600,1650, 600,1650, 600,1650, 650,500, 600,500, 600,1650, 650,1600, 600,1650, 600,550, 550,550, 600,1650, 600,1650, 600,550, 550,550, 600,1650, 600,550, 550,1700, 550,1650, 650,500, 600,550, 550,1650, 600,1650, 650,500, 600,1650, 600};  // NEC 8E732CD
        final int[] vminusCode = new int[]{9000,4400, 650,500, 650,450, 650,500, 650,450, 650,1600, 600,550, 650,450, 600,550, 600,1650, 600,1650, 600,1650, 600,500, 650,500, 600,1650, 600,1650, 600,1600, 650,1600, 650,1600, 650,1600, 650,500, 650,1600, 600,500, 650,500, 650,450, 650,500, 600,500, 650,500, 600,1650, 600,500, 600,1650, 650,1600, 650,1600, 600};  // NEC 8E7E817
        final int[] usbCode = new int[]{8950,4450, 600,500, 650,500, 600,500, 650,500, 600,1650, 550,550, 650,500, 600,500, 600,1650, 600,1650, 600,1650, 600,550, 600,500, 600,1650, 600,1650, 600,1650, 600,1650, 600,550, 600,1650, 600,1600, 650,500, 600,550, 600,500, 600,1650, 600,500, 650,1600, 650,500, 600,500, 650,1600, 650,1600, 650,1600, 650,500, 600};  // NEC 8E7B14E
        final int[] muteCode = new int[]{8950,4450, 650,450, 650,500, 650,450, 650,500, 650,1600, 650,450, 650,500, 650,450, 650,1600, 650,1600, 650,1600, 650,500, 650,450, 650,1600, 650,1600, 650,1600, 650,500, 650,450, 650,500, 600,500, 650,500, 600,500, 650,1600, 650,500, 600,1650, 650,1600, 600,1650, 600,1650, 600,1650, 600,1650, 600,500, 650,1600, 650};  // NEC 8E702FD
        final int[] opticalcoaxialCode = new int[]{8950,4450, 600,500, 650,500, 600,500, 650,500, 600,1650, 600,500, 650,500, 600,500, 650,1600, 650,1600, 650,1600, 650,500, 600,500, 650,1600, 650,1600, 600,1650, 650,500, 600,1650, 550,550, 600,550, 600,1650, 550,550, 600,550, 600,500, 600,1650, 600,550, 550,1700, 600,1600, 600,550, 600,1650, 600,1650, 600,1650, 600};  // NEC 8E748B7
        final int[] line12Code = new int[]{9000,4400, 600,500, 650,500, 650,450, 650,500, 650,1600, 600,500, 650,500, 650,450, 650,1600, 650,1600, 650,1600, 650,500, 650,450, 650,1600, 650,1600, 650,1600, 650,1600, 650,500, 600,500, 650,500, 650,1600, 650,450, 650,1600, 650,500, 600,500, 650,1600, 650,1600, 650,1600, 650,500, 600,1650, 650,450, 650,1600, 600};  // NEC 8E78A75
        final int[] nextCode = new int[]{8950,4450, 600,500, 600,500, 650,500, 600,500, 650,1650, 600,500, 650,500, 600,500, 600,1650, 600,1650, 600,1650, 600,500, 650,500, 600,1650, 600,1650, 650,1600, 600,1650, 650,1600, 650,450, 650,500, 600,1650, 650,450, 650,500, 600,500, 650,500, 650,450, 650,1600, 650,1600, 650,500, 650,1600, 600,1650, 650,1600, 650};  // NEC 8E7C837
        final int[] previousCode = new int[]{9000,4400, 650,450, 650,500, 600,500, 650,500, 650,1600, 650,450, 650,500, 650,450, 650,1600, 650,1600, 650,1600, 650,500, 600,500, 650,1600, 650,1600, 650,1600, 650,500, 650,450, 650,1600, 650,500, 650,1600, 650,450, 650,1600, 650,500, 650,1600, 600,1650, 650,450, 650,1600, 600,550, 650,1600, 600,500, 650,1600, 650};  // NEC 8E72AD5
        final int[] playPauseCode = new int[]{9000,4400, 600,500, 650,500, 600,500, 600,550, 600,1650, 600,500, 600,550, 600,500, 600,1650, 650,1600, 650,1600, 650,450, 650,500, 600,1650, 650,1600, 600,1650, 600,500, 650,500, 650,450, 650,500, 650,1600, 650,450, 650,1600, 650,500, 600,1650, 600,1650, 600,1650, 600,1650, 600,500, 650,1600, 650,500, 600,1650, 600};  // NEC 8E70AF5
        final int[] bluetoothCode = new int[]{8950,4450, 550,550, 600,550, 600,500, 600,550, 600,1650, 600,500, 600,550, 550,550, 600,1650, 600,1650, 600,1650, 600,500, 650,500, 600,1650, 600,1650, 600,1650, 600,500, 650,500, 600,1650, 600,1650, 600,500, 650,500, 600,500, 600,550, 600,1650, 600,1650, 600,500, 650,500, 600,1650, 600,1650, 600,1650, 600,1650, 550};  // NEC 8E730CF
        final int[] monitorCode = new int[]{8950,4450, 600,550, 600,500, 600,500, 600,550, 600,1650, 600,550, 600,500, 600,550, 550,1650, 650,1600, 600,1650, 600,550, 600,500, 650,1600, 650,1600, 600,1650, 600,550, 600,1650, 600,1600, 650,500, 600,1650, 650,500, 600,500, 600,550, 550,1700, 600,500, 600,550, 550,1700, 550,550, 600,1650, 600,1650, 600,1650, 600};  // NEC 8E76897
        final int[] dynamicCode = new int[]{9000,4400, 600,500, 650,500, 600,500, 650,500, 600,1650, 600,500, 600,550, 600,500, 600,1650, 650,1600, 650,1600, 650,500, 600,500, 600,1650, 600,1650, 600,1650, 650,1650, 550,550, 600,1650, 600,500, 600,1650, 650,450, 650,1600, 650,500, 650,450, 650,1600, 650,500, 650,1600, 600,500, 650,1600, 650,500, 600,1650, 650};  // NEC 8E7AA55
        final int[] classicCode = new int[]{8950,4400, 650,500, 650,450, 650,500, 600,500, 650,1600, 650,500, 600,500, 600,550, 600,1650, 600,1650, 600,1650, 600,500, 650,500, 600,1650, 600,1650, 600,1650, 600,500, 600,1650, 650,1600, 650,1600, 650,450, 650,500, 600,500, 650,500, 650,1600, 600,500, 650,500, 600,500, 650,1600, 650,1600, 650,1600, 650,1600, 650};  // NEC 8E7708F
        final int[] vocalCode = new int[]{8950,4450, 550,550, 600,550, 600,500, 650,500, 600,1650, 600,500, 600,550, 550,550, 600,1650, 600,1650, 600,1650, 600,550, 600,500, 600,1650, 600,1650, 600,1650, 600,1650, 600,550, 550,1700, 600,1600, 600,550, 600,500, 650,1650, 600,500, 600,550, 550,1650, 650,500, 600,550, 600,1600, 600,1650, 650,500, 600,1650, 600};  // NEC 8E7B24D
        SwitchCompat onOffSwitch = (SwitchCompat)  findViewById(R.id.on_off_switch);
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Switch State=", ""+isChecked);
                state = isChecked;
            }

        });

        vocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, vocalCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        classic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, classicCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        dynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, dynamicCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        monitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, monitorCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, bluetoothCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, playPauseCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, previousCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, nextCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        line1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, line12Code);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        opticalcoaxial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, opticalcoaxialCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });


        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, muteCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        usb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, usbCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, powerCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });



        volumePlus.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        vibro.run();
                        mHandler.postDelayed(mAction, 500);
                        Log.e("DOWN","DOWN");
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        Log.e("UP","UP");
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    System.out.println("Performing action...");
                    try {
                        consumerIrManager.transmit(frequency, vplusCode);
                    }
                    catch(UnsupportedOperationException e)
                    {

                    }
                    mHandler.postDelayed(this, delaysender);
                }
            };

            Runnable vibro = new Runnable() {
                @Override
                public void run() {
                    Log.e("Vibrate","VIBRO");
                    Log.e("BUT","WORK");
                    try {
                        if(state == true) {
                            v.vibrate(vibratemilsec);
                        }
                        consumerIrManager.transmit(frequency, vplusCode);
                    }
                    catch(UnsupportedOperationException e)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                errIr, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            };

        });

        volumeMinus.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        vibro.run();
                        mHandler.postDelayed(mAction, 500);
                        Log.e("DOWN","DOWN");
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        Log.e("UP","UP");
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    System.out.println("Performing action...");
                    try {
                        consumerIrManager.transmit(frequency, vminusCode);
                    }
                    catch(UnsupportedOperationException e)
                    {

                    }
                    mHandler.postDelayed(this, delaysender);
                }
            };

            Runnable vibro = new Runnable() {
                @Override
                public void run() {
                    Log.e("Vibrate","VIBRO");
                    Log.e("BUT","WORK");
                    try {
                        if(state == true) {
                            v.vibrate(vibratemilsec);
                        }
                        consumerIrManager.transmit(frequency, vminusCode);
                    }
                    catch(UnsupportedOperationException e)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                errIr, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            };

        });



    }
}
