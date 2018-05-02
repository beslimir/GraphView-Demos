package ehealth.graph_view;

import android.support.annotation.LayoutRes;

import com.example.beslimir.R;
import com.example.beslimir.ehealth.graph_view.graph.AddSeriesAtRuntime;
import com.example.beslimir.ehealth.graph_view.graph.BaseExample;
import com.example.beslimir.ehealth.graph_view.graph.FixedFrame;
import com.example.beslimir.ehealth.graph_view.graph.RealtimeScrolling;

public enum FullscreenExample {
    FIXED_FRAME(R.layout.fullscreen_example_simple, FixedFrame.class),
    REALTIME_SCROLLING(R.layout.fullscreen_example_simple, RealtimeScrolling.class),
    ADD_SERIES(R.layout.fullscreen_example_add_series, AddSeriesAtRuntime.class);

    public static final String ARG_ID = "FEID";
    static final String URL_PREFIX = "https://github.com/jjoe64/GraphView-Demos/blob/master/app/src/main/java/com/jjoe64/graphview_demos/examples/";

    public final @LayoutRes
    int contentView;
    public final Class<? extends BaseExample> exampleClass;
    public final String url;

    FullscreenExample(@LayoutRes int contentView, Class<? extends BaseExample> exampleClass) {
        this.contentView = contentView;
        this.exampleClass = exampleClass;
        this.url = URL_PREFIX + exampleClass.getSimpleName() + ".java";
    }
}
