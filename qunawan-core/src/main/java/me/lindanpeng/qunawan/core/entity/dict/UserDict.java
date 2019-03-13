package me.lindanpeng.qunawan.core.entity.dict;

import java.util.HashMap;
import java.util.Map;

public class UserDict {
    public static final Map<Integer,String> GENDER_MAP=new HashMap<>();
    public static final Map<Integer,String> IDENTITY_MAP=new HashMap<>();
    static {
        GENDER_MAP.put(0,"男");
        GENDER_MAP.put(1,"女");
        GENDER_MAP.put(2,"未知");

        IDENTITY_MAP.put(0,"在职人员");
        IDENTITY_MAP.put(1,"学生");
        IDENTITY_MAP.put(2,"其他");
    }
}
