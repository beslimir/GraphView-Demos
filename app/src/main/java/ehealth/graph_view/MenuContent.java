package ehealth.graph_view;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
public class MenuContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<MenuItem> ITEMS = new ArrayList<MenuItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, MenuItem> ITEM_MAP = new HashMap<String, MenuItem>();

    static {
        addItem(new MenuItem("1", "Realtime graf", RealtimeFragment.class));
    }

    private static void addItem(MenuItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A item representing a piece of content.
     */
    public static class MenuItem {
        public final String id;
        public final String content;
        public final Class<? extends Fragment> fragmentClass;

        public MenuItem(String id,
                        String content,
                        Class<? extends Fragment> fragmentClass) {
            this.id = id;
            this.content = content;
            this.fragmentClass = fragmentClass;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
