package ehealth.sensor_fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beslimir.R;
import com.example.beslimir.ehealth.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static com.example.beslimir.ehealth.MainActivity.mySensorData;

/**
 * Created by beslimir on 16.01.18..
 */

public class FragmentTemperature extends Fragment {

    @BindView(R.id.tvFirstSensor)
    TextView tvFirstSensor;
    @BindView(R.id.tvFirstSensorValue)
    TextView tvFirstSensorValue;
    @BindView(R.id.tvFirstSensorDesc)
    TextView tvFirstSensorDesc;
    @BindView(R.id.tvSecondSensor)
    TextView tvSecondSensor;
    @BindView(R.id.tvSecondSensorValue)
    TextView tvSecondSensorValue;
    @BindView(R.id.tvSecondSensorDesc)
    TextView tvSecondSensorDesc;

    final Handler mHandler = new Handler();
    String description = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor, container, false);

        ButterKnife.bind(this, view);

        mHandler.post(mUpdateUI);

        tvFirstSensorValue.setText("36 °C");
        tvFirstSensorValue.setVisibility(View.INVISIBLE);
        tvFirstSensor.setText("Temperatura: " + tvFirstSensorValue.getText().toString());
        tvSecondSensor.setVisibility(GONE);
        tvSecondSensorValue.setVisibility(GONE);
        tvSecondSensorDesc.setVisibility(GONE);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!handleMessage(mySensorData).equals("")) {
            if (Float.parseFloat(handleMessage(mySensorData)) > 36 && Float.parseFloat(handleMessage(mySensorData)) < 38) {
                description = "Normalna temperatura";
            } else if (Float.parseFloat(handleMessage(mySensorData)) > 39 && Float.parseFloat(handleMessage(mySensorData)) < 40) {
                description = "Umjereno povišena";
            } else if (Float.parseFloat(handleMessage(mySensorData)) > 40) {
                description = "Visoka temperatura";
            } else {
                description = "Snižena temperatura";
            }
        } else {
            description = "Učitavanje...";
        }
        tvFirstSensorDesc.setText(description);
    }

    private final Runnable mUpdateUI = new Runnable() {
        public void run() {
            Log.i("temperature", Constants.TEMPERATURE + ": " + handleMessage(mySensorData));
            tvFirstSensor.setText("Temperatura: " + handleMessage(mySensorData) + " °C");

            if (!handleMessage(mySensorData).equals("")) {
                if (Float.parseFloat(handleMessage(mySensorData)) > 36 && Float.parseFloat(handleMessage(mySensorData)) < 38) {
                    description = "Normalna temperatura";
                } else if (Float.parseFloat(handleMessage(mySensorData)) > 39 && Float.parseFloat(handleMessage(mySensorData)) < 40) {
                    description = "Umjereno povišena";
                } else if (Float.parseFloat(handleMessage(mySensorData)) > 40) {
                    description = "Visoka temperatura";
                } else {
                    description = "Snižena temperatura";
                }
            } else {
                description = "Učitavanje...";
            }
            tvFirstSensorDesc.setText(description);

            mHandler.postDelayed(mUpdateUI, 1000); // 1 second
        }
    };

    private String handleMessage(String mySensorData) {
        if (mySensorData.contains(Constants.TEMPERATURE)) {
            mySensorData = mySensorData.substring(mySensorData.indexOf(Constants.TEMPERATURE) + 1);
            Constants.GRAPH = 60;
        } else {
            mySensorData = "";
        }

        return mySensorData;
    }

}