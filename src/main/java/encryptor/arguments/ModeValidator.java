package encryptor.arguments;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ModeValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!value.toLowerCase().matches("enc|encryption|dec|decryption")) {
            throw new ParameterException("Incorrect mode chosen. Available modes: encryption(enc), decryption(dec)");
        }
    }
}
