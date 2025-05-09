package AIE;

// AIE - Ahmedhanov Islam Encryption

public final class Encryption {
	
	private Encryption() {}
	
	
	
	private static final StringBuilder sb = new StringBuilder();
	
	
	public static void main(String[] args) {
		int[][] key = Generator.generateKeys();
		
		System.out.println(encrypt("Hello",key));
		System.out.println(decrypt(encrypt("Hello",key),key));
		
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
	
	
	
	private static final String deepEncode(final String text, final int key) {
		sb.setLength(0);
		sb.append(text);
		
		Generator.random.setSeed(key);
		
		for(int i = 0; i < Constants.MAX_DEEP/2+Generator.random.nextInt(Constants.MAX_DEEP/2); i++) {
			Generator.random.setSeed(key+i);
			sb.append(encode(sb.toString(),Generator.generateKey()));
			sb.delete(0, text.length());
		}
		
		return sb.toString();
	}
	
	private static final String deepDecode(final String text, final int key) {
		sb.setLength(0);
		sb.append(text);
		
		Generator.random.setSeed(key);

		for(int i = 0; i < Constants.MAX_DEEP/2+Generator.random.nextInt(Constants.MAX_DEEP/2); i++) {
			Generator.random.setSeed(key+i);

			sb.append(decode(sb.toString(),Generator.generateKey()));
			sb.delete(0, text.length());
		}
		
		return sb.toString();
	}
	
	private static final String encode(final String text, final int key) {
		sb.setLength(0);
		
		for(int i = 0; i < text.length(); i++) {
			Generator.random.setSeed(key+i+text.length());
			if(Generator.generateKey() > 0) {
			sb.append((char) (text.charAt(i)+Generator.generateKey()));
			} else {
				sb.append((char) (text.charAt(i)-Generator.generateKey()));
			}
		}
		
		return sb.toString();
	}
	
	private static final String decode(final String text, final int key) {
		sb.setLength(0);
		
		for(int i = 0; i < text.length(); i++) {
			Generator.random.setSeed(key+i+text.length());
			if(Generator.generateKey() > 0) {
				sb.append((char) (text.charAt(i)-Generator.generateKey()));
				} else {
					sb.append((char) (text.charAt(i)+Generator.generateKey()));
				}
		}
		
		return sb.toString();
	}
	
}