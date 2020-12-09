package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.api.Session;
import tsi.too.lucaschfonseca.shell.ui.MainWindow;

public class NewWindowCommand extends NonSystemShellCommand {

    public NewWindowCommand(Object arg, Session session) {
        super("shell", session);
        setArg(arg);
    }

    @Override
    public String execute() {
        MainWindow.open(getArg() == null ? null : getArg().toString());
        return "";
    }
}