package com.hkesports.matchticker.test.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;

import com.hkesports.matchticker.common.utils.RandomUtils;

public class ApiTester {

	private static final String url = "http://127.0.0.1:8080/enhanced-match-ticker-api-controller/rest/emt/api/v1/supportcontestant";

	public static void main(String[] args) {
		
		mutliTest();
//		singleTest();
	}

	protected static void singleTest() {
		try {
			String body = getConn()
			.data("matchID", "141015")
			.data("facebookID", RandomUtils.getRandomCode(2))
			.data("contestantID", RandomUtils.random("55683", "55688"))
			.execute().body();
			System.out.println(body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected static void mutliTest() {
		ExecutorService executor = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 100; i++) {
			executor.execute(new Work());
		}
		executor.shutdown();
	}
	
	public static class Work implements Runnable {
		@Override
		public void run() {
			System.out.println("thread id:" + Thread.currentThread().getId() + " start!!");
			for (int i = 0; i < 50; i++) {
				try {
					getConn()
					.data("matchID", "141015")
					.data("facebookID", RandomUtils.getRandomCode(2))
					.data("contestantID", RandomUtils.random("55683", "55688"))
					.execute().body();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("thread id:" + Thread.currentThread().getId() + " done!!");
		}
	}
	
	private static Connection getConn() {
		return Jsoup.connect(url).ignoreContentType(true).userAgent("chrome").timeout(5000).method(Method.POST);
	}
}
