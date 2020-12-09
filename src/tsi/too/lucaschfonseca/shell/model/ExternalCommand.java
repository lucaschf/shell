package tsi.too.lucaschfonseca.shell.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ExternalCommand extends Command {
    public ExternalCommand(String name, String... args) {
        super(name, args);
    }

    public ExternalCommand(String name) {
        super(name);
    }

    @Override
    public String execute() throws IOException {
        var p = new ProcessBuilder("cmd", "/c", getName() + Arrays.toString(getArgs()));

        return retrieveResults(p.start());
    }

    private String retrieveResults(Process process) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            return "failed to execute";
        }
    }
}
