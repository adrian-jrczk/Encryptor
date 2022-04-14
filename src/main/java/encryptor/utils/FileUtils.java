package encryptor.utils;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static byte[] loadBytes(String fileName) throws FileOperationException {
        try {
            return Files.readAllBytes(Path.of(fileName));
        } catch (Exception exception) {
            throw new FileOperationException("Could not load data from file: " + fileName);
        }
    }

    public static void saveString(String fileName, String data) throws FileOperationException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(data);
        } catch (Exception exception) {
            throw new FileOperationException("Could not save data to file = " + fileName);
        }
    }

    public static void saveBytes(String fileName, byte[] data) throws FileOperationException {
        try (FileOutputStream writer = new FileOutputStream(fileName)) {
            writer.write(data);
        } catch (Exception exception) {
            throw new FileOperationException("Could not save data to file = " + fileName);
        }
    }
}
