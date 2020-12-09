package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.api.CommandsTable;
import tsi.too.lucaschfonseca.shell.api.NoSuchCommandException;
import tsi.too.lucaschfonseca.shell.impl.ExternalCommandsTable;
import tsi.too.lucaschfonseca.shell.impl.InternalCommandsTable;

import java.util.Objects;

public class CommandFactory {
    private static final CommandsTable internalTable = new InternalCommandsTable();
    private static final CommandsTable externalCommands = new ExternalCommandsTable();

    private CommandFactory() {
        // prevents instantiation
    }

    public static Command create(String name) throws IllegalArgumentException, NoSuchCommandException {
        Objects.requireNonNull(name);

        if (internalTable.getCommands().contains(name.toLowerCase()))
            return new InternalSystemShellCommand(name);

        if (externalCommands.getCommands().contains(name.toLowerCase()))
            return new ExternalSystemShellCommand(name);

        throw new NoSuchCommandException(
                String.format("O comando '%s' não é reconhecido como um comando interno ou externo", name));
    }
}