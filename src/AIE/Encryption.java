package AIE;

import static AIE.Constants.*;
import static AIE.Generator.*;

/**
 * AIE - Ahmedhanov Islam Encryption
 * <p>
 * This is the main class for working with text for encryption
 * </p>
 */

public final class Encryption {
	
	private Encryption() {}
	
	private static final StringBuilder sb = new StringBuilder();
	
	public static final String decrypt(final String text, final int[][] keys) {
		sb.setLength(0);
		sb.append(text);
		
		for(int i = 0; i < keys.length; i++) {
			for(int j = 0; j < keys[i].length; j++) {
				sb.append(deepUnshifting(text,keys[i][j]));
				sb.delete(0, text.length());
			}
		}
		
		return sb.toString();
	}
	
	public static final String encrypt(final String text, final int[][] keys) {
		sb.setLength(0);
		sb.append(text);
		
		for(int i = 0; i < keys.length; i++) {
			for(int j = 0; j < keys[i].length; j++) {
				sb.append(deepShifting(text,keys[i][j]));
				sb.delete(0, text.length());
			}
		}
		
		return sb.toString();
	}
	
	private static final String deepShifting(final String text, final int key) {
		sb.setLength(0);
		sb.append(text);
		
		random.setSeed(key);
		
		for(int i = 0; i < MIN_DEEP+random.nextInt(MAX_DEEP-MIN_DEEP+1); i++) {
			random.setSeed(key+i);
			sb.append(shifting(sb.toString(),generateKey()));
			sb.delete(0, text.length());
		}
		
		return sb.toString();
	}
	
	private static final String deepUnshifting(final String text, final int key) {
		sb.setLength(0);
		sb.append(text);
		
		random.setSeed(key);

		for(int i = 0; i < MIN_DEEP+random.nextInt(MAX_DEEP-MIN_DEEP+1); i++) {
			random.setSeed(key+i);

			sb.append(unshifting(sb.toString(),generateKey()));
			sb.delete(0, text.length());
		}
		
		return sb.toString();
	}
	
	private static final String shifting(final String text, final int key) {
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
	
	private static final String unshifting(final String text, final int key) {
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