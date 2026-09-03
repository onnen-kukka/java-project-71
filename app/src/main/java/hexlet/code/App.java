package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff [-hV]",
        description = "Compares two configuration files and shows a difference.")


public class App implements Callable<String> {
    @Option(names = { "-f", "--format"},
            paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String format;

    @Parameters(paramLabel = "filepath1",
            description = "path to first file")
    private String filepath1;
    @Parameters(paramLabel = "filepath2",
            description = "path to second file")
    private String filepath2;

    @Override
    public String call() throws Exception {
        String result = Differ.generate(filepath1, filepath2);
        System.out.println(result);
        return  result;
    }

    public static void main(String[] args) {

        App app = new App();
        int exitCode = new CommandLine(app).execute(args);
        System.exit(exitCode);
    }
}
