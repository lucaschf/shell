package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.api.CommandsTable;
import tsi.too.lucaschfonseca.shell.api.NoSuchCommandException;
import tsi.too.lucaschfonseca.shell.api.Session;
import tsi.too.lucaschfonseca.shell.impl.ExternalCommandsTable;
import tsi.too.lucaschfonseca.shell.impl.InternalCommandsTable;

import java.util.Objects;

public class CommandFactory {
    private static final CommandsTable internalTable = new InternalCommandsTable();
    private static final CommandsTable externalCommands = new ExternalCommandsTable();

    private CommandFactory() {
        // prevents instantiation
    }

    public static Command create(Session executionContext, String name, Object args) throws IllegalArgumentException, NoSuchCommandException {
        Objects.requireNonNull(name);
        Objects.requireNonNull(executionContext);

        if (internalTable.getCommands().contains(name.toLowerCase()))
            return new InternalSystemShellCommand(name, executionContext)
                    .setArg(args)
                    ;

        if (externalCommands.getCommands().contains(name.toLowerCase()))
            return new ExternalSystemShellCommand(name, executionContext)
                    .setArg(args)
                    ;

        throw new NoSuchCommandException(
                String.format("O comando '%s' não é reconhecido como um comando interno ou externo", name));
    }
}