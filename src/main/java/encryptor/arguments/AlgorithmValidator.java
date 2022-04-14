package encryptor.arguments;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class AlgorithmValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!value.toLowerCase().matches("shift|aes|blowfish")) {
            throw new ParameterException("Incorrect algorithm name. Available algorithms: shift, aes, blowfish");
        }
    }
}
