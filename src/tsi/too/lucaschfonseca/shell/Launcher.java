package tsi.too.lucaschfonseca.shell;

import tsi.too.lucaschfonseca.shell.ui.MainWindow;

public class Launcher {
	public static void main(String[] args) {
		MainWindow.open(args.length == 2 ? args[1] : null);
	}
}