package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.ui.Shell;

import java.io.IOException;

public class ExitSessionCommand extends NonSystemShellCommand {

    public ExitSessionCommand(Shell shell) {
        super("exit");
        setArg(shell);
    }

    @Override
    public String execute() throws IOException {
        ((Shell) getArg()).close();
        return "";
    }
}
