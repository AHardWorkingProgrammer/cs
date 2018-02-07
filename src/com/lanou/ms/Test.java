package com.lanou.ms;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import redis.clients.jedis.Jedis;

public class Test {
	public static void main(String[] args) {
		Jedis jedis=new Jedis("localhost");
		jedis.auth("123456");
		jedis.set("iphone8", "100");
		jedis.close();
		ExecutorService exexutor = Executors.newFixedThreadPool(20);
		for (int i = 0; i < 1000; i++) {
			exexutor.execute(new SaleRunable("user"+i));
		}
		exexutor.shutdown();
	}
}
