package application;

	import javax.crypto.Cipher;
	import javax.crypto.KeyGenerator;
	import javax.crypto.SecretKey;
	import javax.crypto.spec.GCMParameterSpec;
	import javax.crypto.spec.SecretKeySpec;
	import java.util.Base64;



	public class EncryptingClass {
	    private SecretKey key;
	    private int KEY_SIZE = 128;
	    private int T_LEN = 128;
	    private byte[] IV;

	    public void init() throws Exception {
	        KeyGenerator generator = KeyGenerator.getInstance("AES");
	        generator.init(KEY_SIZE);
	        key = generator.generateKey();
	    }

	    public void initFromStrings(String secretKey, String IV){
	        key = new SecretKeySpec(decode(secretKey),"AES");
	        this.IV = decode(IV);
	    }

	 
	    public String encrypt(String message) throws Exception {
	        byte[] messageInBytes = message.getBytes();
	        Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
	        GCMParameterSpec spec = new GCMParameterSpec(T_LEN,IV);
	        encryptionCipher.init(Cipher.ENCRYPT_MODE, key,spec);
	        byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
	        return encode(encryptedBytes);
	    }

	    

	    private String encode(byte[] data) {
	        return Base64.getEncoder().encodeToString(data);
	    }

	    private byte[] decode(String data) {
	        return Base64.getDecoder().decode(data);
	    }

	   

	    
	}

