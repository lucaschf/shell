package tsi.too.lucaschfonseca.shell.impl;

import tsi.too.lucaschfonseca.shell.api.CommandNames;
import tsi.too.lucaschfonseca.shell.api.CommandsTable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class InternalCommandsTable implements CommandsTable {

    @Override
    public Set<String> getCommands() {
        return new HashSet<>(
                Arrays.asList(
                        CommandNames.CLEAR_SCREEN,
                        CommandNames.DATE,
                        CommandNames.TIME,
                        CommandNames.DIR,
                        CommandNames.TYPE,
                        CommandNames.CD,
                        CommandNames.MD,
                        CommandNames.COPY,
                        CommandNames.RD,
                        CommandNames.DEL,
                        CommandNames.VOL,
                        CommandNames.VER,
                        CommandNames.PATH,
                        CommandNames.SET
                )
        );
    }
}