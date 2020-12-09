package tsi.too.lucaschfonseca.shell;

import tsi.too.lucaschfonseca.shell.api.CommandNames;
import tsi.too.lucaschfonseca.shell.api.CommandsTable;
import tsi.too.lucaschfonseca.shell.api.NoSuchCommandException;
import tsi.too.lucaschfonseca.shell.impl.NonSystemShellCommandsTable;
import tsi.too.lucaschfonseca.shell.model.*;
import tsi.too.lucaschfonseca.shell.ui.Shell;

import java.io.IOException;

public class CommandInterpreter {

    private final Shell context;
    private static final CommandsTable nonSystemShellCommands = new NonSystemShellCommandsTable();

    public CommandInterpreter(Shell context) {
        this.context = context;
    }

    public String interpretAndExecute(String userInput) {
        if (userInput.isBlank()) // nothing to do
            return "";

        try {
            return recoverCommandFromUserInput(userInput).execute();
        } catch (IOException e) {
            return "failed to execute";
        } catch (NoSuchCommandException e) {
            return e.getMessage();
        }
    }

    private Command recoverCommandFromUserInput(String userInput) throws IllegalArgumentException, NoSuchCommandException {
        String commandName;
        String args = "";

        if (userInput == null || !userInput.contains(" ")) {
            commandName = userInput;
        } else {
            commandName = userInput.substring(0, userInput.indexOf(" ")).trim();
            args = userInput.substring(commandName.length()).trim();
        }

        if (nonSystemShellCommands.getCommands().contains(commandName))
            return new NonSystemShellCommandFactory().create(commandName, args);
        else
            return CommandFactory.create(commandName).setArg(args);
    }

    private class NonSystemShellCommandFactory {
        private NonSystemShellCommandFactory() {
            // prevents instantiation
        }

        public Command create(String name, Object arg) throws IllegalArgumentException, NoSuchCommandException {
            if (name.equalsIgnoreCase(CommandNames.EXIT)) {
                return new ExitSessionCommand(context);
            }

            if (name.equalsIgnoreCase(CommandNames.SHELL))
                return new NewWindowCommand().setArg(arg);

            throw new NoSuchCommandException(String.format("O comando '%s' não é reconhecido como um comando interno ou externo", name));
        }
    }
}
