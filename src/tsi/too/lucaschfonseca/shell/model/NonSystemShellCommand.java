package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.api.Session;

public abstract class NonSystemShellCommand extends Command {

    public NonSystemShellCommand(String name, Session executionContext) {
        super(name, executionContext);
    }
}