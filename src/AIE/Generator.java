package AIE;

import java.security.SecureRandom;
import java.util.Random;

public final class Generator {
	static final SecureRandom secureRandom = new SecureRandom();
	static final Random random = new Random();
	
	private Generator() {}
	
	public static final int[][] generateKeys() {
		final int[][] keys = new int[1 + (int) secureRandom.nextInt(Constants.MAX_KEYS)][1 + (int) secureRandom.nextInt(Constants.MAX_DEEP_FOR_INNER_ARRAYS_OF_KEYS)];
		
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
	
}