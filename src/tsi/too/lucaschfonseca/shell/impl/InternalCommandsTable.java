package tsi.too.lucaschfonseca.shell.impl;

import tsi.too.lucaschfonseca.shell.api.CommandsTable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class InternalCommandsTable implements CommandsTable {
    @Override
    public Set<String> getCommandsList() {
        return new HashSet<>(Arrays.asList("cls", "date", "time", "dir", "type", "cd", "md", "copy", "rd", "del", "vol", "ver", "path", "set"));
    }
}