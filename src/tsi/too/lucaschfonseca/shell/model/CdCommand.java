package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.api.CommandNames;
import tsi.too.lucaschfonseca.shell.api.Session;

import java.io.File;
import java.io.IOException;

public class CdCommand extends InternalSystemShellCommand {

    public CdCommand(String arg, Session session) {
        super(CommandNames.CD, session);
        setArg(arg);
    }

    @Override
    public String execute() throws IOException {
        if (getArg().toString().isBlank())
            return super.execute();

        var target = getArg().toString();
        if (isDirectory(target)) {
            getExecutionContext().setCurrentDirectory(getArg().toString());
            return "";
        } else {
            var d = getExecutionContext().getCurrentDirectory() + "\\" + target;

            if (isDirectory(d)) {
                getExecutionContext().setCurrentDirectory(d);
                return "";
            }

            return "O sistema não consegue encontrar o caminho especificado.";
        }
    }

    private boolean isDirectory(String s) {
        File file = new File(s);
        return file.isDirectory();
    }
}
