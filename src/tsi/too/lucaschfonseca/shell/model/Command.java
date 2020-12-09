package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.api.ICommand;
import tsi.too.lucaschfonseca.shell.api.Session;

import java.util.Objects;

public abstract class Command implements ICommand {
    private String name;
    private Object arg;
    private Session executionContext;

    public Command(String name, Session executionContext) {
        super();
        Objects.requireNonNull(executionContext);

        this.name = name;
        this.arg = null;
        this.executionContext = executionContext;
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

    public Command setExecutionContext(Session executionContext) {
        this.executionContext = executionContext;
        return this;
    }

    public Session getExecutionContext() {
        return executionContext;
    }

    @Override
    public String toString() {
        return "%s> %s %s".formatted(executionContext, name, arg);
    }
}