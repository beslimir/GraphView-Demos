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

public class FragmentECG extends Fragment {

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

        tvFirstSensorValue.setText("0 μV");
        tvFirstSensorValue.setVisibility(View.INVISIBLE);
        tvFirstSensor.setText("EKG vrijednost: " + tvFirstSensorValue.getText().toString());
        if (!handleMessage(mySensorData).equals("")) {
            description = "";
            tvFirstSensorDesc.setVisibility(View.INVISIBLE);
        } else {
            description = "Učitavanje...";
            tvFirstSensorDesc.setVisibility(View.VISIBLE);
            tvFirstSensorDesc.setText(description);
        }
        tvSecondSensor.setVisibility(GONE);
        tvSecondSensorValue.setVisibility(GONE);
        tvSecondSensorDesc.setVisibility(GONE);

        return view;
    }

    private final Runnable mUpdateUI = new Runnable() {
        public void run() {
            Log.i("ecg", Constants.ECG + ": " + handleMessage(mySensorData));
            tvFirstSensor.setText("EKG vrijednost: " + handleMessage(mySensorData) + " μV");

            if (!handleMessage(mySensorData).equals("")) {
                description = "";
                tvFirstSensorDesc.setVisibility(View.INVISIBLE);
            } else {
                description = "Učitavanje...";
                tvFirstSensorDesc.setVisibility(View.VISIBLE);
                tvFirstSensorDesc.setText(description);
            }

            mHandler.postDelayed(mUpdateUI, 55); // 55 milliseconds
        }
    };

    private String handleMessage(String mySensorData) {
        if (mySensorData.contains(Constants.ECG)) {
            mySensorData = mySensorData.substring(mySensorData.indexOf(Constants.ECG) + 1);
            Constants.GRAPH = 5;
        } else {
            mySensorData = "";
        }

        return mySensorData;
    }
}
