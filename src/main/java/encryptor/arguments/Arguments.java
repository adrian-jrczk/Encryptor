package encryptor.arguments;

import com.beust.jcommander.Parameter;

public class Arguments {

    @Parameter(names = {"--mode", "-m"}, validateWith = ModeValidator.class)
    public String mode = "enc";

    @Parameter(names = {"--algorithm", "-a"}, validateWith = AlgorithmValidator.class)
    public String algorithm = "shift";

    @Parameter(names = {"--password", "-p"}, required = true)
    public String password;

    @Parameter(names = {"--input-file", "-i"}, required = true)
    public String inputFileName;

    @Parameter(names = {"--output-file", "-o"})
    public String outputFileName;
}
