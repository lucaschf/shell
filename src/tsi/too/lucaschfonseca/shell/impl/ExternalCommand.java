package tsi.too.lucaschfonseca.shell.impl;

import java.io.IOException;

import tsi.too.lucaschfonseca.shell.api.ICommand;

public class ExternalCommand implements ICommand {

	@Override
	public String execute() throws IOException {
		var p = new ProcessBuilder("cmd", "/c", "command");
		p.start();
		return "";
	}
}
