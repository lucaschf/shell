package tsi.too.lucaschfonseca.shell.model;

import java.io.IOException;

public class InternalCommand extends Command {
    public InternalCommand(String name, String... args) {
        super(name, args);
    }

    public InternalCommand(String name) {
        super(name);
    }

    @Override
    public String execute() throws IOException {
        return null;
    }
}
