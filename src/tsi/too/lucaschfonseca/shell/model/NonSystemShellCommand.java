package tsi.too.lucaschfonseca.shell.model;

public abstract class NonSystemShellCommand extends Command {

    private NonSystemShellCommand(String name, Object arg) {
        super(name);
        setArg(arg);
    }

    public NonSystemShellCommand(String name) {
        super(name);
    }
}