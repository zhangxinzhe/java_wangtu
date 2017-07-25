package net.zdsoft.common.utils;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author fangb
 * @date 2010-1-18 上午04:54:01
 */
public class JsonUtil {

    /**
     * 将java对象转换成json格式的字符串
     * <p>
     * 该方法会将日期类型的数据转换为日期对应的数值
     * 
     * @param src
     * @return
     */
    public static String toJson(Object src) {
        return buildGson().toJson(src);
    }

    /**
     * 将map<String,String>类型的JSON转为MAP对象
     * 
     * @param json
     * @return
     */
    public static Map<String, String> toMap(String json) {
        return buildGson().fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
    }

    // public static void main(String[] args) {
    // String json = "{\"c_updiniversion\":\"VP103_20140310\", \"message\":\"OK\"})";
    // Map<String,String> test = JsonUtil.toMap(json);
    // for(String key : test.keySet()){
    // System.out.println(key+"=" + test.get(key));
    // }
    // }

    public static Gson buildGson() {
        return new GsonBuilder().registerTypeAdapter(java.util.Date.class, new DateSerializer()).create();
    }

    /**
     * 日期的解析类 将日期解析成数值类型，方便通用的传输
     * 
     * @author fangb
     * 
     */
    static class DateSerializer implements JsonSerializer<Date> {

        @Override
        public JsonElement serialize(Date arg0, Type arg1, JsonSerializationContext arg2) {
            return new JsonPrimitive(arg0.getTime());
        }
    }
}
