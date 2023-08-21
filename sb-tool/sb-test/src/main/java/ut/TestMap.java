package ut;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TestMap {
    @Test
    public void testGet() {
        HashMap<String, Set<String>> hashMap = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.add("zhangSan");
        hashMap.put("china", set);
        // after JDK1.8
        hashMap.computeIfAbsent("china", key -> new HashSet<>()).add("liSi");
        System.out.println(hashMap.toString());


    }

    // if existed not put
    @Test
    public void testPutIfAbsent() {

        HashMap<Integer, String> sites = new HashMap<>();

        // 往 HashMap 添加一些元素
        sites.put(1, "Google");
        sites.put(2, "Runoob");
        sites.put(3, "Taobao");
        System.out.println("sites HashMap: " + sites);

        // HashMap 不存在该key
        sites.putIfAbsent(4, "Weibo");
        System.out.println("Updated Languages: " + sites);
        // HashMap 中存在 Key
        sites.putIfAbsent(2, "Wiki");
        System.out.println("Updated Languages: " + sites);
    }

}
