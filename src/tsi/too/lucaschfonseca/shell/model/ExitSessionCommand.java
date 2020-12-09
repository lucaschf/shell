package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.api.Session;
import tsi.too.lucaschfonseca.shell.ui.MainWindow;

import java.io.IOException;

public class ExitSessionCommand extends NonSystemShellCommand {

    public ExitSessionCommand(MainWindow mainWindow, Session session) {
        super("exit", session);
        setArg(mainWindow);
    }

    @Override
    public String execute() {
        ((MainWindow) getArg()).close();
        return "";
    }

    @Override
    public String toString() {
        return "%s> %s".formatted(getExecutionContext(), getName());
    }
}
