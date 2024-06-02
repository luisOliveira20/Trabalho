package pt.ipp.estg.dwdn.pdm1.notificaao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.TextView;

public class Sensor extends AppCompatActivity implements SensorEventListener {

    private static int TYPE_PROXIMITY;
    private TextView textView3;
    private SensorManager sensorManager;
    private android.hardware.Sensor proximitysensor;
    private Boolean isProximitySensorAvailable;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        textView3 = findViewById(R.id.textView3);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null){
            proximitysensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            isProximitySensorAvailable = true;
        } else {
            textView3.setText("");
            isProximitySensorAvailable = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        textView3.setText(sensorEvent.values[0] + "");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(500);
        }
    }

    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isProximitySensorAvailable){
            sensorManager.registerListener(this, proximitysensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isProximitySensorAvailable){
            sensorManager.unregisterListener(this);
        }
    }
}