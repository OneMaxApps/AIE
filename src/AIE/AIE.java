package AIE;

import java.security.SecureRandom;
import java.util.Random;

// AIE - Ahmedhanov Islam Encryption

public final class AIE {
	
	private AIE() {}
	
	private static final int MAX_DEEP = 200,
							 MAX_DEEP_FOR_INNER_ARRAYS_OF_KEYS = 10,
		 	 				 MAX_KEYS = 1000;
	
	private static final StringBuilder sb = new StringBuilder();
	private static final Random random = new Random();
	private static final SecureRandom secureRandom = new SecureRandom();
	
	public static void main(String[] args) {
		int[][] testKeys = generateKeys();
		String testText = "Some plain text for testing";
		
		long start = System.currentTimeMillis();
		
		System.out.println(decrypt(encrypt(testText,testKeys),testKeys));
		
		long end = System.currentTimeMillis()-start;
		
		System.out.println(end);
	}
	
	public static final String decrypt(String text, final int[][] keys) {
		
		for(int i = 0; i < keys.length; i++) {
			for(int j = 0; j < keys[i].length; j++) {
				text = deepDecode(text,keys[i][j]);
			}
		}
		
		return text;
	}
	
	public static final String encrypt(String text, final int[][] keys) {
		
		for(int i = 0; i < keys.length; i++) {
			for(int j = 0; j < keys[i].length; j++) {
				text = deepEncode(text,keys[i][j]);
			}
		}
		
		return text;
	}
	
	public static final int[][] generateKeys() {
		final int[][] keys = new int[1 + (int) secureRandom.nextInt(MAX_KEYS)][1 + (int) secureRandom.nextInt(MAX_DEEP_FOR_INNER_ARRAYS_OF_KEYS)];
		
		for(int i = 0; i < keys.length; i++) {
			for(int j = 0; j < keys[i].length; j++) {
			keys[i][j] = secureRandom.nextInt(Integer.MAX_VALUE);
			}
		}
		
		return keys;
	}
	
	public static final int generateKey() {
		return random.nextInt(Integer.MAX_VALUE);
	}
	
	private static final String deepEncode(final String text, final int key) {
		sb.setLength(0);
		sb.append(text);
		
		random.setSeed(key);
		
		for(int i = 0; i < random.nextInt(MAX_DEEP); i++) {
			random.setSeed(key+i);
			sb.append(encode(sb.toString(),generateKey()));
			sb.delete(0, text.length());
		}
		
		return sb.toString();
	}
	
	private static final String deepDecode(final String text, final int key) {
		sb.setLength(0);
		sb.append(text);
		
		random.setSeed(key);

		for(int i = 0; i < random.nextInt(MAX_DEEP); i++) {
			random.setSeed(key+i);

			sb.append(decode(sb.toString(),generateKey()));
			sb.delete(0, text.length());
		}
		
		return sb.toString();
	}
	
	private static final String encode(final String text, final int key) {
		sb.setLength(0);
		
		for(int i = 0; i < text.length(); i++) {
			random.setSeed(key+i+text.length());
			if(generateKey() > 0) {
			sb.append((char) (text.charAt(i)+generateKey()));
			} else {
				sb.append((char) (text.charAt(i)-generateKey()));
			}
		}
		
		return sb.toString();
	}
	
	private static final String decode(final String text, final int key) {
		sb.setLength(0);
		
		for(int i = 0; i < text.length(); i++) {
			random.setSeed(key+i+text.length());
			if(generateKey() > 0) {
				sb.append((char) (text.charAt(i)-generateKey()));
				} else {
					sb.append((char) (text.charAt(i)+generateKey()));
				}
		}
		
		return sb.toString();
	}
	
}