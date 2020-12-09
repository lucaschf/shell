package tsi.too.lucaschfonseca.shell;

import tsi.too.lucaschfonseca.shell.ui.Shell;

public class Launcher {
	public static void main(String[] args) {
		Shell.open(args.length == 2 ? args[1] : null);
	}
}