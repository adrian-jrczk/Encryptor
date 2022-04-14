package encryptor.encryption;

import encryptor.encryption.exceptions.DecryptionFailedException;
import encryptor.encryption.exceptions.EncryptionFailedException;
import encryptor.encryption.exceptions.PasswordParsingException;

public class ShiftEncryptor extends Encryptor {

    private final int KEY_CAP = 127;
    private int SHIFT_DIVIDER_CAP = 4;
    private int key;
    private byte[] VALIDATION_BYTES = new byte[]{40, 11, 50, 125, 2, 50, 33, 90, 2, 1};

    @Override
    public byte[] encrypt(byte[] data, String password) throws EncryptionFailedException {
        try {
            generateKeyFromPassword(password);
            byte[] encrypted = new byte[data.length + VALIDATION_BYTES.length];
            int shiftDivider = 1;
            for (int i = 0; i < data.length; i++) {
                if (shiftDivider > SHIFT_DIVIDER_CAP) {
                    shiftDivider = 1;
                }
                encrypted[i] = (byte) (data[i] + (key / shiftDivider));
                shiftDivider++;
            }
            addTrailingValidationBytes(encrypted);
            return encrypted;
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
            if (!hasCorrectValidationBytes(data)) {
                throw new Exception();
            }
            byte[] decrypted = new byte[data.length - 10];
            int shiftDivider = 1;
            for (int i = 0; i < decrypted.length; i++) {
                if (shiftDivider > SHIFT_DIVIDER_CAP) {
                    shiftDivider = 1;
                }
                decrypted[i] = (byte) (data[i] - (key / shiftDivider));
                shiftDivider++;
            }
            return decrypted;
        } catch (PasswordParsingException exception) {
            throw new DecryptionFailedException(exception.getMessage());
        } catch (Exception exception) {
            throw new DecryptionFailedException("Could not decrypt data");
        }
    }

    @Override
    public void generateKeyFromPassword(String password) throws PasswordParsingException {
        try {
            key = Integer.parseInt(password) % KEY_CAP;
        } catch (Exception exception) {
            throw new PasswordParsingException("Error occurred while trying to parse password");
        }
    }

    private void addTrailingValidationBytes(byte[] bytes) {
        for (int i = 0; i < 10; i++) {
            bytes[bytes.length - 10 + i] = (byte) (VALIDATION_BYTES[i] + key);
        }
    }

    private boolean hasCorrectValidationBytes(byte[] bytes) {
        for (int i = 0; i < 10; i++) {
            byte decrypted = (byte) (bytes[bytes.length - 10 + i] - key);
            if (decrypted != VALIDATION_BYTES[i]) {
                return false;
            }
        }
        return true;
    }
}
