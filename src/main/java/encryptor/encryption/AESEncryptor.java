package encryptor.encryption;

import encryptor.encryption.exceptions.DecryptionFailedException;
import encryptor.encryption.exceptions.EncryptionFailedException;
import encryptor.encryption.exceptions.PasswordParsingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;

public class AESEncryptor extends Encryptor {

    private final int KEY_SIZE = 128;
    private final String salt = "856270598237534643";
    private final int T_LEN = 128;
    private SecretKeySpec secretKey;
    private GCMParameterSpec IV = new GCMParameterSpec(T_LEN, new byte[]{-50, 73, -89, -90, 123, 21, -61, -73, -108, 120, -28, 45, -125, -59, -30, 59});

    @Override
    public byte[] encrypt(byte[] data, String password) throws EncryptionFailedException {
        try {
            generateKeyFromPassword(password);
            Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
            encryptionCipher.init(Cipher.ENCRYPT_MODE, secretKey, IV);
            return encryptionCipher.doFinal(data);
        } catch (PasswordParsingException exception) {
            throw new EncryptionFailedException(exception.getMessage());
        } catch (Exception exception) {
            throw new EncryptionFailedException("Could not encrypt data");
        }
    }

    @Override
    public byte[] decrypt(byte[] data, String password) throws DecryptionFailedException {
        try {
            generateKeyFromPassword(password);
            Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
            decryptionCipher.init(Cipher.DECRYPT_MODE, secretKey, IV);
            return decryptionCipher.doFinal(data);
        } catch (PasswordParsingException exception) {
            throw new DecryptionFailedException(exception.getMessage());
        } catch (Exception exception) {
            throw new DecryptionFailedException("Could not decrypt data");
        }
    }

    @Override
    protected void generateKeyFromPassword(String password) throws PasswordParsingException {
        try {
            KeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 10000, KEY_SIZE);
            SecretKey pbeKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(pbeKeySpec);
            this.secretKey = new SecretKeySpec(pbeKey.getEncoded(), "AES");
        } catch (Exception exception) {
            throw new PasswordParsingException("Error occurred while trying to parse password");
        }
    }
}
