package encryptor.encryption;

import encryptor.encryption.exceptions.DecryptionFailedException;
import encryptor.encryption.exceptions.EncryptionFailedException;
import encryptor.encryption.exceptions.PasswordParsingException;

public abstract class Encryptor {

    abstract protected void generateKeyFromPassword(String password) throws PasswordParsingException;

    abstract public byte[] encrypt(byte[] data, String password) throws EncryptionFailedException;

    abstract public byte[] decrypt(byte[] data, String password) throws DecryptionFailedException;
}
