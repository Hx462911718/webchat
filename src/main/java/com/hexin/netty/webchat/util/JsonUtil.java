/**
 * 
 */
package com.hexin.netty.webchat.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author hexin
 * 
 * 2019年6月20日 下午4:11:41
 */
public class JsonUtil {
	
	private JsonUtil(){};
	
	private static SerializeConfig serializeConfig = new SerializeConfig();
	private static SerializerFeature[] serializerFeatures = new SerializerFeature[4];
	static{
		serializeConfig.put(java.util.Date.class, new SimpleDateFormatSerializer(TimeUtil.FORMAT_DAY));
		serializeConfig.put(java.sql.Date.class, new SimpleDateFormatSerializer(TimeUtil.FORMAT_DAY));
		serializeConfig.put(java.sql.Timestamp.class, new SimpleDateFormatSerializer(TimeUtil.FORMAT_SECOND));
		serializeConfig.put(java.sql.Time.class, new SimpleDateFormatSerializer(TimeUtil.FORMAT_TIME));
		
		serializerFeatures[0] = SerializerFeature.WriteNullStringAsEmpty;
		serializerFeatures[1] = SerializerFeature.WriteMapNullValue;
		serializerFeatures[2] = SerializerFeature.WriteNullListAsEmpty;
		serializerFeatures[3] = SerializerFeature.DisableCircularReferenceDetect;
	}
	
	/**
	 * 转成JSON对象
	 * @param json
	 * @return
	 */
	public static JSONObject toJsonObject(String json)  {
		return JSON.parseObject(json);
	}
	
	/**
	 * 转成JSON数组
	 * @param json
	 * @return
	 */
	public static JSONArray toJsonArray(String json) {
		return JSON.parseArray(json);
	}
	
	/**
	 * 将Object转成JSON字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return JSON.toJSONString(obj,serializeConfig,serializerFeatures);
	}
	
	/**
	 * 转成Bean
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T>T convertToBean(String json,Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}
	
	/**
	 * 转成列表
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T>List<T> convertToList(String json,Class<T> clazz) {
		return JSON.parseArray(json, clazz);
	}
	
	/**
	 * 
	 * 转成Map<String,Object>>
	 * @param json
	 * @return
	 */
	public static Map<String,Object> convertToMap(String json) {
		return JSONObject.parseObject(json, new TypeReference<Map<String,Object>>(){});
	}
	/**
	 * 
	 * 转成List<Map<String,Object>>
	 * @param json
	 * @return
	 */
	public static List<Map<String,Object>> convertToListMap(String json) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		JSONArray array = JsonUtil.toJsonArray(json);
		Iterator<Object> itor=array.iterator();
		while(itor.hasNext()){
			JSONObject jsonObject = (JSONObject)itor.next();
			list.add(JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<Map<String,Object>>(){}));
		}
		return list;
	}
}
