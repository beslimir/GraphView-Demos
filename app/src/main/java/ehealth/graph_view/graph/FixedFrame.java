package ehealth.graph_view.graph;

import android.os.Handler;

import com.example.beslimir.R;
import com.example.beslimir.ehealth.Constants;
import com.example.beslimir.ehealth.MainActivity;
import com.example.beslimir.ehealth.graph_view.FullscreenActivity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class FixedFrame extends BaseExample {
    private final Handler mHandler = new Handler();
    private Runnable mTimer;
    private double graphLastXValue = 5d;
    private LineGraphSeries<DataPoint> mSeries;

    @Override
    public void onCreate(FullscreenActivity activity) {
        GraphView graph = (GraphView) activity.findViewById(R.id.graph);
        initGraph(graph);
    }

    @Override
    public void initGraph(GraphView graph) {
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(40);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-Constants.GRAPH);
        graph.getViewport().setMaxY(Constants.GRAPH);

        // first mSeries is a line
        mSeries = new LineGraphSeries<>();
        graph.addSeries(mSeries);
    }

    public void onResume() {
        //commented for testing purpose
        mTimer = new Runnable() {
            @Override
            public void run() {
                if (graphLastXValue == 40) {
                    graphLastXValue = 0;
                    mSeries.resetData(new DataPoint[]{
                            new DataPoint(graphLastXValue, getRandom())
                    });
                }
                graphLastXValue += 1d;
                mSeries.appendData(new DataPoint(graphLastXValue, getRandom()), false, 40);
                mHandler.postDelayed(this, 50);
            }
        };
        mHandler.postDelayed(mTimer, 700);
    }

    public void onPause() {
        mHandler.removeCallbacks(mTimer);
    }

    private double getRandom() {
        return Double.parseDouble(MainActivity.mySensorData.substring(1));
    }
}
