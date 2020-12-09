package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.api.Session;

public class ExternalSystemShellCommand extends SystemShellCommand {

    public ExternalSystemShellCommand(String name, Session executionContext) {
        super(name, executionContext);
    }
}
