package org.redrock.utils;

import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import javax.enterprise.inject.Model;
import java.util.List;

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

}
