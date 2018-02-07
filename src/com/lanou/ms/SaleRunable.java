package com.lanou.ms;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class SaleRunable implements Runnable {
	String productKey="iphone8";
	Jedis  jedis=new Jedis("localhost");
	String userName;
	public SaleRunable(String userName) {
		this.userName=userName;
	}
	
	
	@Override
	public void run() {
		jedis.auth("123456");
		jedis.watch(productKey);
		String value = jedis.get(productKey);
		Integer num = Integer.valueOf(value);
		if(num<=100&&num>=1) {
			Transaction T = jedis.multi();
			T.incrBy(productKey, -1);
			List<Object> list = T.exec();
			if(list==null||list.size()==0) {
				System.out.println(userName+"商品抢购失败");
			}else {
				for (Object o : list) {
					System.out.println(userName+"("+o.toString()+")商品抢购成功，当前抢购成功的人数是:"+(1-(num-100)));
				}
			}
			
		}else {
			System.out.println("商品被抢完了！");
		}
		jedis.close();
	}

}
