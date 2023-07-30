package Helpers;

import java.util.LinkedHashMap;
import java.util.Map;

public class LimitedSizeMap<K, V> extends LinkedHashMap<K, V> {
    private static final int MAX_ENTRIES = 1000;

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }
}

