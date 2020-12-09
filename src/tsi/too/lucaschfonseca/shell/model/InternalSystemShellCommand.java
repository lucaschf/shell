package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.api.Session;

public class InternalSystemShellCommand extends SystemShellCommand {

    public InternalSystemShellCommand(String name, Session executionContext) {
        super(name, executionContext);
    }
}
