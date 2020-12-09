package tsi.too.lucaschfonseca.shell.api;

import java.io.IOException;

@FunctionalInterface
public interface ICommand {
	String execute() throws IOException;
}