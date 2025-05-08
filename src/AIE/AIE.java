package AIE;

import java.security.SecureRandom;
import java.util.Random;

// AIE - Ahmedhanov Islam Encryption

public final class AIE {
	
	private AIE() {}
	
	private static final int MAX_DEEP = 200,
		 	 				 MAX_KEYS = 1000;
	
	private static final StringBuilder sb = new StringBuilder();
	private static final Random random = new Random();
	private static final SecureRandom secureRandom = new SecureRandom();
	
	public static void main(String[] args) {
		int[] testKeys = generateKeys();
		String testText = "Hello World";
		System.out.println(decrypt(encrypt(testText,testKeys),testKeys));
	}
	
	public static final String decrypt(String text, final int[] keys) {
		
		for(int i = 0; i < keys.length; i++) {
			text = deepDecode(text,keys[i]);
		}
		
		return text;
	}
	
	public static final String encrypt(String text, final int[] keys) {
		
		for(int i = 0; i < keys.length; i++) {
			text = deepEncode(text,keys[i]);
		}
		
		return text;
	}
	
	public static final int[] generateKeys() {
		final int[] keys = new int[1 + (int) secureRandom.nextInt(MAX_KEYS)];
		
		for(int i = 0; i < keys.length; i++) {
			keys[i] = Integer.MIN_VALUE + secureRandom.nextInt(Integer.MAX_VALUE)*2;
		}
		
		return keys;
	}
	
	public static final int generateKey() {
		return Integer.MIN_VALUE + random.nextInt(Integer.MAX_VALUE)*2;
	}
	
	private static final String deepEncode(String text, final int key) {
		random.setSeed(key);
		
		for(int i = 0; i < random.nextInt(MAX_DEEP); i++) {
			random.setSeed(key+i);
			
			text = encode(text,generateKey());
			
		}
		
		return text;
	}
	
	private static final String deepDecode(String text, final int key) {
		random.setSeed(key);
		
		for(int i = 0; i < random.nextInt(MAX_DEEP); i++) {
			random.setSeed(key+i);
			text = decode(text,generateKey());
		}
		
		return text;
	}
	
	private static final String encode(final String text, final int key) {
		sb.setLength(0);
		
		for(int i = 0; i < text.length(); i++) {
			random.setSeed(key+i+text.length());
			sb.append((char) (text.charAt(i)+generateKey()));
		}
		
		return sb.toString();
	}
	
	private static final String decode(final String text, final int key) {
		sb.setLength(0);
		
		for(int i = 0; i < text.length(); i++) {
			random.setSeed(key+i+text.length());
			sb.append((char) (text.charAt(i)-generateKey()));
		}
		
		return sb.toString();
	}
	
}