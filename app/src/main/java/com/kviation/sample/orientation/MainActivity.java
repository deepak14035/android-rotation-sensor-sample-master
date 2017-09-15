package com.kviation.sample.orientation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements Orientation.Listener{
public static boolean startsaving=false;
  private Orientation mOrientation;
  private AttitudeIndicator mAttitudeIndicator;
  //private SensorManager mSensorManager;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    //mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    //Sensor s=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    mOrientation = new Orientation(this,Sensor.TYPE_ACCELEROMETER, Sensor.TYPE_GYROSCOPE);
    mAttitudeIndicator = (AttitudeIndicator) findViewById(R.id.attitude_indicator);
  }

    public void sendMessage(View v){
        startsaving=true;
    }
    public void stopMessage(View v){
        startsaving=false;
    }
  @Override
  protected void onStart() {
    super.onStart();
    mOrientation.startListening(this);
  }

  @Override
  protected void onStop() {
    super.onStop();
    mOrientation.stopListening();
  }

  @Override
  public void onOrientationChanged(float pitch, float roll) {
    mAttitudeIndicator.setAttitude(pitch, roll);
  }
}
