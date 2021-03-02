package cache;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String, Float> h = new HashMap<>();
        Float a = Float.valueOf(12.6f);
        Float b = Float.valueOf(1.6f);
        System.out.println(a == b);
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        h.putIfAbsent("1234", a);
        h.putIfAbsent("1234", b);
        System.out.println(h.get("1234"));
        h.values();
    }
}
