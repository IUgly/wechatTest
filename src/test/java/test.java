import com.alibaba.fastjson.JSONArray;
import net.sf.json.JSONObject;
import com.google.gson.Gson;
import org.redrock.dao.StartDao;
import org.redrock.utils.WechatUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
       String url = "https://www.sojson.com/open/api/weather/json.shtml?city=%E9%87%8D%E5%BA%86";
        com.alibaba.fastjson.JSONObject json = WechatUtil.getInfo(url);
        System.out.println(json);
    }
}
