package me.lindanpeng.qunawan.core.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {
    /**
     * Map转String工具
     * @param map
     * @param separator 分隔符
     * @param kvSplice  键值拼接符
     * @return
     */
    public static String mapToString(Map<?, ?> map, String separator, String kvSplice) {
        List<String> result = new ArrayList<>();
        map.entrySet().parallelStream().reduce(result, (first, second) -> {
            first.add(second.getKey() + kvSplice + second.getValue());
            return first;
        }, (first, second) -> {
            if (first == second) {
                return first;
            }
            first.addAll(second);
            return first;
        });

        return StringUtils.join(result, separator);
    }
    public static String mapToString(Map<?,?> map){
        return mapToString(map,",",":");
    }

    public static void main(String[] args) {
        HashMap map=new HashMap();
        map.put("asfs",12);
        map.put(1,10);
        System.out.println(mapToString(map,",",":"));
    }
}
