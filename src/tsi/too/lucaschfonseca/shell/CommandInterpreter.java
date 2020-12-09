package tsi.too.lucaschfonseca.shell;

import tsi.too.lucaschfonseca.shell.api.CommandNames;
import tsi.too.lucaschfonseca.shell.api.CommandsTable;
import tsi.too.lucaschfonseca.shell.api.NoSuchCommandException;
import tsi.too.lucaschfonseca.shell.api.Session;
import tsi.too.lucaschfonseca.shell.impl.NonSystemShellCommandsTable;
import tsi.too.lucaschfonseca.shell.model.*;
import tsi.too.lucaschfonseca.shell.ui.MainWindow;

import java.io.IOException;
import java.util.Objects;

public class CommandInterpreter {
    private final MainWindow shell;
    private static final CommandsTable nonSystemShellCommands = new NonSystemShellCommandsTable();
    private final Session session;

    public CommandInterpreter(MainWindow shell, Session session) {
        this.session = session;
        Objects.requireNonNull(session);
        this.shell = shell;
    }

    public String interpretAndExecute(String userInput) {
        if (userInput.isBlank()) // nothing to do
            return "";

        try {
            var command = recoverCommandFromUserInput(userInput);
            session.addCommandLog(command);
            return command.execute();
        } catch (IOException e) {
            return "failed to execute";
        } catch (NoSuchCommandException e) {
            return e.getMessage();
        }
    }

    private Command recoverCommandFromUserInput(String userInput) throws IllegalArgumentException, NoSuchCommandException {
        String commandName;
        String arg = "";

        if (userInput == null || !userInput.contains(" ")) {
            commandName = userInput;
        } else {
            commandName = userInput.substring(0, userInput.indexOf(" ")).trim();
            arg = userInput.substring(commandName.length()).stripLeading().stripTrailing();
        }

        return createCommand(commandName, arg);
    }

    private Command createCommand(String commandName, String arg) throws IllegalArgumentException, NoSuchCommandException {
        if (commandName.equalsIgnoreCase(CommandNames.CD))
            return new CdCommand(arg, session);

        if (nonSystemShellCommands.getCommands().contains(commandName))
            return new NonSystemShellCommandFactory().create(commandName, arg);

        return CommandFactory.create(session, commandName, arg);
    }

    private class NonSystemShellCommandFactory {
        private NonSystemShellCommandFactory() {
            // prevents instantiation
        }

        public Command create(String name, Object arg) throws IllegalArgumentException, NoSuchCommandException {
            if (name.equalsIgnoreCase(CommandNames.EXIT)) {
                return new ExitSessionCommand(shell, session);
            }

            if (name.equalsIgnoreCase(CommandNames.SHELL))
                return new NewWindowCommand(arg, session);

            throw new NoSuchCommandException(String.format("O comando '%s' não é reconhecido como um comando interno ou externo", name));
        }
    }
}