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
import ehealth.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.INVISIBLE;
import static ehealth.MainActivity.mySensorData;

/**
 * Created by beslimir on 16.01.18..
 */

public class FragmentGSR extends Fragment {

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

        tvFirstSensorValue.setText("0 Ω");
        tvFirstSensorValue.setVisibility(INVISIBLE);
        tvFirstSensor.setText("Otpor kože: " + tvFirstSensorValue.getText().toString());
        if (!handleMessage(mySensorData).equals("")) {
            description = "";
            tvFirstSensorDesc.setVisibility(INVISIBLE);
        } else {
            description = "Učitavanje...";
            tvFirstSensorDesc.setVisibility(View.VISIBLE);
            tvFirstSensorDesc.setText(description);
        }
        tvSecondSensorValue.setText("0 μV");
        tvSecondSensorValue.setVisibility(INVISIBLE);
        tvSecondSensor.setText("Provodljivost kože: " + tvSecondSensorValue.getText().toString());
        if (!handleMessage(mySensorData).equals("")) {
            description = "";
            tvSecondSensorDesc.setVisibility(INVISIBLE);
        } else {
            description = "Učitavanje...";
            tvSecondSensorDesc.setVisibility(View.VISIBLE);
            tvSecondSensorDesc.setText(description);
        }

        return view;
    }

    private final Runnable mUpdateUI = new Runnable() {
        public void run() {
            Log.i("gsr", Constants.GSR + ": " + handleMessage(mySensorData));
            tvFirstSensor.setText("Otpor kože: " + handleMessageR(mySensorData) + " Ω");
            tvSecondSensor.setText("Provodljivost kože: " + handleMessage(mySensorData) + " μV");

            //first sensor
            if (!handleMessage(mySensorData).equals("")) {
                description = "";
                tvFirstSensorDesc.setVisibility(View.INVISIBLE);
            } else {
                description = "Učitavanje...";
                tvFirstSensorDesc.setVisibility(View.VISIBLE);
                tvFirstSensorDesc.setText(description);
            }

            //second sensor
            if (!handleMessage(mySensorData).equals("")) {
                description = "";
                tvSecondSensorDesc.setVisibility(INVISIBLE);
            } else {
                description = "Učitavanje...";
                tvSecondSensorDesc.setVisibility(View.VISIBLE);
                tvSecondSensorDesc.setText(description);
            }

            mHandler.postDelayed(mUpdateUI, 1000); // 1 second
        }
    };

    private String handleMessage(String mySensorData) {
        if (mySensorData.contains(Constants.GSR)) {
            mySensorData = mySensorData.substring(mySensorData.indexOf(Constants.GSR) + 1, mySensorData.indexOf(Constants.GSR2));
        } else {
            mySensorData = "";
        }

        return mySensorData;
    }

    private String handleMessageR(String mySensorData) {
        if (mySensorData.contains(Constants.GSR2)) {
            mySensorData = mySensorData.substring(mySensorData.indexOf(Constants.GSR2) + 1);
        } else {
            mySensorData = "";
        }

        return mySensorData;
    }
}