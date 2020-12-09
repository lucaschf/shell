package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.api.CommandsTable;
import tsi.too.lucaschfonseca.shell.impl.ExternalCommandsTable;
import tsi.too.lucaschfonseca.shell.impl.InternalCommandsTable;

public class CommandFactory {
    private static final CommandsTable internalTable = new InternalCommandsTable();
    private static final CommandsTable externalCommands = new ExternalCommandsTable();


    private CommandFactory() {
        // prevents instantiation
    }

    public static Command create(String name) throws IllegalArgumentException{
        if(name == null)
            throw new IllegalArgumentException("Null command not accepted");

        if(internalTable.getCommandsList().contains(name.toLowerCase()))
            return new InternalCommand(name);

        if(externalCommands.getCommandsList().contains(name.toLowerCase()))
            return new ExternalCommand(name);

        throw new IllegalArgumentException(String.format("O comando '%s' não é reconhecido como um comando interno ou externo", name));
    }
}