package tsi.too.lucaschfonseca.shell.impl;

import tsi.too.lucaschfonseca.shell.api.CommandNames;
import tsi.too.lucaschfonseca.shell.api.CommandsTable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExternalCommandsTable implements CommandsTable {

    @Override
    public Set<String> getCommands() {
        return new HashSet<>(
                Arrays.asList(
                        CommandNames.TREE,
                        CommandNames.ATTRIB,
                        CommandNames.HELP
                )
        );
    }
}