package com.hkesports.matchticker.common.utils;

import java.util.Random;

public class RandomUtils {
	public static final Random RANDOM = new Random();
	private static final char pem_array[] = { 
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 
			};

	public static String getRandomCode(int size) {
		StringBuilder str = new StringBuilder(size);
		for (int i = 0; i < size; i++) {
			str.append(pem_array[RANDOM.nextInt(pem_array.length)]);
		}
		return str.toString();
	}
	
	public static String getRandomCode2(int size) {
		StringBuilder str = new StringBuilder(size);
		for (int i = 0; i < size; i++) {
			int flag = RANDOM.nextInt(3);
			if (flag==0) {
				str.append((char) (RANDOM.nextInt(10) + 48));
			} else if (flag==1){
				str.append((char) (RANDOM.nextInt(26) + 65));
			} else {
				str.append((char) (RANDOM.nextInt(26) + 97));
			}
		}
		return str.toString();
	}

	public static String breakApart(String str) {
		StringBuilder sb = new StringBuilder(str);
		int count = sb.length() - 1;// (sb.length()/3)*2;//
		for (int i = count, index = 0; i >= 0; i--, index = RANDOM.nextInt(sb.length())) {
			char c = sb.charAt(index);
			sb.setCharAt(index, sb.charAt(i));
			sb.setCharAt(i, c);
		}
		return sb.toString();
	}

	public static String random(String... values) {
		if(values==null || values.length == 0) {
			return null;
		}
		return values[RANDOM.nextInt(values.length)];
	}
	
	public static void main(String[] args) {
		System.out.println(getRandomCode2(10));
	}
}
