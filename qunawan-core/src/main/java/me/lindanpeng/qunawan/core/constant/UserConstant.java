package me.lindanpeng.qunawan.core.constant;

import java.util.HashMap;
import java.util.Map;

public class UserConstant {
    public static final String DEFAULT_AVATAR="http://www.ilvping.com/public/themes/weibo/images/user_pic_middle.gif";
    public static final String DEFAULT_NICKNAME="新用户";

    public static final int GENDEAR_MAIE=0;
    public static final int GENDEAR_FEMAIE=1;
    public static final int GENDEAR_UNKNOWN=2;

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
