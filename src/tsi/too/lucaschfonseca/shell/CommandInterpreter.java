package tsi.too.lucaschfonseca.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

import tsi.too.lucaschfonseca.shell.impl.ExternalCommand;
import tsi.too.lucaschfonseca.shell.model.Command;

public class CommandInterpreter {
//    private final HashSet<String> runsOutsideSystemShell = new HashSet<>();

    private static CommandInterpreter instance;

    public static CommandInterpreter getInstance() {
        synchronized (CommandInterpreter.class){
            if(instance == null)
                instance = new CommandInterpreter();

            return  instance;
        }
    }

//    private CommandInterpreter() {
//        runsOutsideSystemShell.add(Command.EXIT.getName());
//        runsOutsideSystemShell.add(Command.NEW_WINDOW.getName());
//        runsOutsideSystemShell.add(Command.CLEAR.getName());
//    }

    public String interpret(Command command) {
//        if (!runsOutsideSystemShell(command)) {
//            try {
//                return retrieveResults(new ExternalCommand().execute(command.getName() + " " + Arrays.toString(command.getArgs())));
//            } catch (IOException e) {
//                return e.getMessage();
//            }
//        }
//
        return "";
    }

//    private boolean runsOutsideSystemShell(Command command) {
//        return runsOutsideSystemShell.contains(command.getName().toLowerCase());
//    }

    private String retrieveResults(Process process) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            return "failed to execute";
        }
    }
}
