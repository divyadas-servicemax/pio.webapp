package com.ge.util;

import java.io.IOException;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

	public static String convertObjectToString(Object object){
		ObjectMapper mapper = new ObjectMapper();

		String jsonString = "";
		//Object to JSON String
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T convertJsonToObject(String jsonString,T t ){
		ObjectMapper mapper = new ObjectMapper();
		
		//JSON from String to Object
		T object = null;
		try {
			object = (T)mapper.readValue(jsonString, t.getClass());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return object;
	}
	
	public static org.json.JSONObject getJsonObject(String jsonString) {
		String validJson = "";
		org.json.JSONObject resultJson = null;
		if (StringUtils.isNotEmpty(jsonString)) {

			if ((jsonString != null) && (StringUtils.isNotEmpty(jsonString))) {
				resultJson = new org.json.JSONObject(validJson);
			}

		}
		return resultJson;
	}
	
	public static void main(String[] args) {
		System.out.println("test");
		
		String test = "{\"name\":\"jim\",\"age\":22}";
		
		Student s = new Student();
		s.setName("tom");
		s.setAge(23);
		String jsonString = convertObjectToString(s);
		System.out.println(jsonString);
		
		Student fromJson = convertJsonToObject(jsonString, new Student());
		
		System.out.println(fromJson.getAge());
		
	}
	
}

class Student{
	String name;
	int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	

}
