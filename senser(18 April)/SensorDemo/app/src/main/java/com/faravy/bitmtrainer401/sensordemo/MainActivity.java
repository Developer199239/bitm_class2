package com.faravy.bitmtrainer401.sensordemo;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SensorManager manager;
    float accelValue;
    float currentValue;
    float lastValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
       // manager.registerListener(listener,manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);

        accelValue=0.00f;
        currentValue=SensorManager.GRAVITY_EARTH;
        lastValue=SensorManager.GRAVITY_EARTH;

    }


    private SensorEventListener listener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            float x=event.values[0];
            float y=event.values[1];
            float z=event.values[2];

            lastValue=currentValue;

            currentValue= (float) Math.sqrt((double)(x*x+y*y+z*z));

            float delta=currentValue-lastValue;
            accelValue=accelValue*.9f+delta;

           // Toast.makeText(MainActivity.this, String.valueOf(accelValue), Toast.LENGTH_SHORT).show();

          if (accelValue>15){
              Toast.makeText(MainActivity.this, String.valueOf(accelValue), Toast.LENGTH_SHORT).show();
              Intent callIntent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+12345));
              startActivity(callIntent);
          }



        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onPause() {
        manager.unregisterListener(listener);
        super.onPause();
    }

    @Override
    protected void onResume() {

        manager.registerListener(listener,manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
}
