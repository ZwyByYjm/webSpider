package utils;


import com.alibaba.fastjson.JSONObject;
import com.webSpider.pojo.LastFMUserInfo;

import java.lang.reflect.Field;

/**
 * 将request转换成需要的Object对象
 *
 * @author 印佳明
 * @create 2018-04-17 15:37
 */
public class JSON2ObjUtil {
    JSON2ObjUtil() {

    }

    /**
     * @Author 印佳明
     * @Date 2018/4/17 16:13
     * 通过反射将request中的值付给对应的对象
     */
    public static <T> T json2Object(JSONObject jsonObject, Class<T> clz) {
        //1、获取字段
        //1.1 获取Field的数组,私有字段也能获取
        try {
            T t = clz.newInstance();
            Field[] fields = clz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
//                System.out.println(field.getType().getSimpleName());
                if (field.getType().getSimpleName().equalsIgnoreCase("Integer"))
                    field.set(t, jsonObject.get(field.getName()) == null ? null : Integer.valueOf(jsonObject.get(field.getName()).toString().trim()));
                if (field.getType().getSimpleName().equalsIgnoreCase("String")) {
                    if (field.getName().equalsIgnoreCase("registered")) {
                        field.set(t, JSONObject.parseObject(jsonObject.get(field.getName()).toString()).get("unixtime").toString());
                    } else {
                        field.set(t, jsonObject.get(field.getName()) == null ? "" : jsonObject.get(field.getName()).toString());
                    }

                }
            }
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class clazz = Class.forName("com.webSpider.pojo.LastFMUserInfo");
        //1、获取字段
        //1.1 获取Field的数组,私有字段也能获取
        LastFMUserInfo t = (LastFMUserInfo) clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.getType().getSimpleName());
            if (field.getType().getSimpleName().equalsIgnoreCase("Integer"))
                field.set(t, 1);
            if (field.getType().getSimpleName().equalsIgnoreCase("String"))
                field.set(t, "asd");
        }
        System.out.println(t);

    }
}
