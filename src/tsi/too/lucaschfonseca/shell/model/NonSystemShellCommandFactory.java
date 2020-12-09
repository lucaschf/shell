package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.ui.Shell;

public class NonSystemShellCommandFactory {
    private NonSystemShellCommandFactory() {
        // prevents instantiation
    }

    public static Command create(String name, Shell args) throws IllegalArgumentException {
        if (name.equalsIgnoreCase("exit")) {
            return new ExitSessionCommand().setArg(args);
        }

        if (name.equalsIgnoreCase("shell"))
            return new NewWindowCommand().setArg(args);

        throw new IllegalArgumentException(String.format("O comando '%s' não é reconhecido como um comando interno ou externo", name));
    }
}