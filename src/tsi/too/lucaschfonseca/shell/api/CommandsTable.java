package tsi.too.lucaschfonseca.shell.api;

import java.util.Set;

@FunctionalInterface
public interface CommandsTable {

    Set<String> getCommands();
}