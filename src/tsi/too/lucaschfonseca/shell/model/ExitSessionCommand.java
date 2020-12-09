package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.ui.MainWindow;

import java.io.IOException;

public class ExitSessionCommand extends NonSystemShellCommand {

    public ExitSessionCommand(MainWindow mainWindow) {
        super("exit");
        setArg(mainWindow);
    }

    @Override
    public String execute() throws IOException {
        ((MainWindow) getArg()).close();
        return "";
    }

    @Override
    public String toString() {
        return getName();
    }
}
