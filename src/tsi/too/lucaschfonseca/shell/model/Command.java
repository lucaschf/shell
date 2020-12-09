package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.api.ICommand;

public abstract class Command implements ICommand {
    private String name;
    private Object arg;

    public Command(String name) {
        super();
        this.name = name;
        this.arg = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getArg() {
        return arg;
    }

    public Command setArg(Object arg) {
        this.arg = arg;
        return this;
    }

    @Override
    public String toString() {
        return "%s %s".formatted(name, arg);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != getClass())
            return false;

        return ((Command) obj).getName().equalsIgnoreCase(name);
    }
}