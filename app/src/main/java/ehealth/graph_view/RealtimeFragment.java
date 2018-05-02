package ehealth.graph_view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beslimir.R;
import ehealth.graph_view.graph.BaseExample;
import ehealth.graph_view.graph.FixedFrame;
import com.jjoe64.graphview.GraphView;

/**
 * Created by jonas on 07.09.16.
 */
public class RealtimeFragment extends ItemDetailFragment {
    private BaseExample mLogicFixedFrame;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLogicFixedFrame = new FixedFrame();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.realtime_content, container, false);

        GraphView graph = (GraphView) rootView.findViewById(R.id.graph);
        mLogicFixedFrame.initGraph(graph);
        rootView.findViewById(R.id.cardFixedFrame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFullscreen(FullscreenExample.FIXED_FRAME);
            }
        });
        rootView.findViewById(R.id.imgFullscreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFullscreen(FullscreenExample.FIXED_FRAME);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mLogicFixedFrame.onResume();
    }

    @Override
    public void onPause() {
        mLogicFixedFrame.onPause();
        super.onPause();
    }
}
