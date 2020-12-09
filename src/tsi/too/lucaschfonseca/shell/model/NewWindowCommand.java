package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.ui.MainWindow;

public class NewWindowCommand extends NonSystemShellCommand {

    public NewWindowCommand() {
        super("shell");
    }

    @Override
    public String execute() {
        MainWindow.open(getArg() == null ? null : getArg().toString());
        return "";
    }
}