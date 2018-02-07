package com.lanou;

import java.util.HashMap;
import java.util.List;

import redis.clients.jedis.Jedis;

public class Demo {
	public static void main(String[] args) {
		//Jedis j = new Jedis("127.0.0.1",6379);
		//j.auth("123456");
//		j.select(0);
//		j.set("test", "123456");
		map();
		
	}
	public static void map() {
		Jedis j = new Jedis("127.0.0.1",6379);
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "lilei");
		map.put("age", "19");
		map.put("study", "math");
		j.select(2);
		j.hmset("xiaoming", map);
		List<String> list = j.hmget("xiaoming", "name","age","study");
		System.out.println(list);
	}
}
