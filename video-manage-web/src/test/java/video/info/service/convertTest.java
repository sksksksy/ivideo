package video.info.service;

import java.util.ArrayList;
import java.util.List;

public class convertTest {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("hello");
        a.forEach(s -> {
            System.out.println(s);
        });
    }
}
