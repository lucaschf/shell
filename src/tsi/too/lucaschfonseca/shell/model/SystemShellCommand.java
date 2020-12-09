package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.api.Session;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.stream.Collectors;

public abstract class SystemShellCommand extends Command {
    public SystemShellCommand(String name, Session executionContext) {
        super(name, executionContext);
    }

    @Override
    public String execute() throws IOException {
        var p = new ProcessBuilder(
                "cmd",
                "/c",
                MessageFormat.format("{0} {1}", getName(), getArg())
        );

        var directory = new File(getExecutionContext().getCurrentDirectory());
        p.directory(directory);

        return retrieveResults(p.start());
    }

    protected String retrieveResults(Process process) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8))) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            } catch (IOException ex) {
                return "failed to execute command";
            }
        }
    }
}
