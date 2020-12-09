package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.ui.Shell;

public class NewWindowCommand extends NonSystemShellCommand {

    public NewWindowCommand() {
        super("shell");
    }

    @Override
    public String execute() {
        Shell.open(getArg() == null ? null : getArg().toString());
        return "";
    }
}