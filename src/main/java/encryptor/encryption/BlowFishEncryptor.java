package encryptor.encryption;

import encryptor.encryption.exceptions.DecryptionFailedException;
import encryptor.encryption.exceptions.EncryptionFailedException;
import encryptor.encryption.exceptions.PasswordParsingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class BlowFishEncryptor extends Encryptor {

    private SecretKey secretKey;

    @Override
    public byte[] encrypt(byte[] data, String password) throws EncryptionFailedException {
        try {
            generateKeyFromPassword(password);
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
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
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (PasswordParsingException exception) {
            throw new DecryptionFailedException(exception.getMessage());
        } catch (Exception exception) {
            throw new DecryptionFailedException("Could not decrypt data");
        }
    }

    @Override
    protected void generateKeyFromPassword(String password) throws PasswordParsingException {
        try {
            this.secretKey = new SecretKeySpec(password.getBytes(), "Blowfish");
        } catch (Exception exception) {
            throw new PasswordParsingException("Error occurred while trying to parse password");
        }
    }
}
