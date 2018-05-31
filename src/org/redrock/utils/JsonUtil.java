package org.redrock.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import javax.enterprise.inject.Model;
import java.util.*;

/**
 * @author ugly
 */
public class JsonUtil {

    public static JSONArray BeanToJson(Object bean, String[] strings){
        JsonConfig jsonConfig = new JsonConfig();
        // bean 转换json格式（屏蔽不用的对象属性）
        jsonConfig.setExcludes(strings);
        JSONArray jsonArray = JSONArray.fromObject(bean, jsonConfig);
        return jsonArray;
    }


    public static JSONObject BeanToJsonEmpty(List<Model> bean){
        //bean转Json,(过滤为空值的属性)
        JsonConfig jsonConfig = new JsonConfig();
        PropertyFilter filter = new PropertyFilter() {
            @Override
            public boolean apply(Object object, String fieldName, Object fieldValue) {
                if (fieldValue instanceof List){
                    List<Object> list = (List<Object>) fieldValue;
                    if (list.size() == 0){
                        return true;
                    }
                }
                return null == fieldValue || "".equals(fieldValue);
            }
        };
        jsonConfig.setJsonPropertyFilter(filter);
        JSONArray jsonArray = JSONArray.fromObject(bean, jsonConfig);
        JSONObject json = new JSONObject();
        json.put("data",jsonArray);
        return json;
    }


    public static <T> T fromJson(String jsonString, Class<T> type) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        return (T) JSONObject.toJavaObject(jsonObject, type);
    }

    /**
     * 将JSONArray对象转换成list集合
     *
     * @param jsonArr
     * @return
     */
    public static List<Object> jsonToList(JSONArray jsonArr) {
        List<Object> list = new ArrayList<Object>();
        for (Object obj : jsonArr) {
            if (obj instanceof JSONArray) {
                list.add(jsonToList((JSONArray) obj));
            } else if (obj instanceof JSONObject) {
                list.add(jsonToMap((JSONObject) obj));
            } else {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 将json字符串转换成map对象
     *
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(String json) {
        JSONObject obj = JSONObject.parseObject(json);
        return jsonToMap(obj);
    }

    /**
     * 将JSONObject转换成map对象
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> jsonToMap(JSONObject obj) {
        Set<?> set = obj.keySet();
        Map<String, Object> map = new HashMap<String, Object>(set.size());
        for (Object key : obj.keySet()) {
            Object value = obj.get(key);
            if (value instanceof JSONArray) {
                map.put(key.toString(), jsonToList((JSONArray) value));
            } else if (value instanceof JSONObject) {
                map.put(key.toString(), jsonToMap((JSONObject) value));
            } else {
                map.put(key.toString(), obj.get(key));
            }

        }
        return map;
    }

    /**
     * 对象转换为json字符串
     * @param obj
     * @return
     */
    public static String logObjToString(Object obj){
        return JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }
}

