package encryptor.core;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import encryptor.arguments.Arguments;
import encryptor.encryption.*;
import encryptor.encryption.exceptions.DecryptionFailedException;
import encryptor.encryption.exceptions.EncryptionFailedException;
import encryptor.utils.FileOperationException;
import encryptor.utils.FileUtils;

public class Engine {

    private Encryptor encryptor;

    public void run(String[] args) {
        try {
            Arguments arguments = parseArguments(args);
            encryptor = setEncryptor(arguments);
            byte[] data = loadData(arguments.inputFileName);
            if (arguments.mode.matches("enc|encryption")) {
                encryptData(data, arguments);
            } else if (arguments.mode.matches("dec|decryption")) {
                decryptData(data, arguments);
            }
            System.out.printf("File %s %s successfully", arguments.inputFileName, arguments.mode.equals("enc") ? "encrypted" : "decrypted");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void encryptData(byte[] data, Arguments arguments) throws EncryptionFailedException, FileOperationException {
        byte[] results = encryptor.encrypt(data, arguments.password);
        String fileName = arguments.outputFileName == null ? arguments.inputFileName : arguments.outputFileName;
        FileUtils.saveBytes(fileName, results);
    }

    private Encryptor setEncryptor(Arguments arguments) throws EncryptorInitializationException {
        return switch (arguments.algorithm) {
            case "shift" -> new ShiftEncryptor();
            case "aes" -> new AESEncryptor();
            case "blowfish" -> new BlowFishEncryptor();
            default -> throw new EncryptorInitializationException("Could not initialize encryptor");
        };
    }

    private void decryptData(byte[] data, Arguments arguments) throws DecryptionFailedException, FileOperationException {
        byte[] results = encryptor.decrypt(data, arguments.password);
        String fileName = arguments.outputFileName == null ? arguments.inputFileName : arguments.outputFileName;
        FileUtils.saveString(fileName, new String(results));
    }

    private byte[] loadData(String inputFileName) throws FileOperationException {
        return FileUtils.loadBytes(inputFileName);
    }

    private Arguments parseArguments(String[] args) throws ParameterException {
        Arguments arguments = new Arguments();
        JCommander.newBuilder().addObject(arguments).build().parse(args);
        return arguments;
    }
}
