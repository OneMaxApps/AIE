package AIE;

import java.security.SecureRandom;
import java.util.Random;

import static AIE.Constants.*;

public final class Generator {
	static final SecureRandom secureRandom = new SecureRandom();
	static final Random random = new Random();
	
	private Generator() {}
	
	public static final int[][] generate2DKey() {
		final int[][] keys = new int[MIN_KEYS + (int) secureRandom.nextInt(MAX_KEYS-MIN_KEYS+1)][MIN_SUB_KEYS + (int) secureRandom.nextInt(MAX_SUB_KEYS-MIN_SUB_KEYS+1)];
		
		for(int i = 0; i < keys.length; i++) {
			for(int j = 0; j < keys[i].length; j++) {
			keys[i][j] = secureRandom.nextInt();
			}
		}
		
		return keys;
	}
	
	public static final int generateKey() {
		return random.nextInt() > Integer.MAX_VALUE/2 ? random.nextInt() : -random.nextInt();
	}
	
}