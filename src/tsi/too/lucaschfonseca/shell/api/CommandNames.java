package tsi.too.lucaschfonseca.shell.api;

public interface CommandNames {
    // Non system shell commands
    String EXIT = "exit";
    String SHELL = "shell";

    // Internal commands
    String CLEAR_SCREEN = "cls";
    String DATE = "date";
    String TIME = "time";
    String DIR = "dir";
    String TYPE = "type";
    String CD = "cd";
    String MD = "md";
    String COPY = "copy";
    String RD = "rd";
    String DEL = "del";
    String VOL = "vol";
    String VER = "ver";
    String PATH = "path";
    String SET = "set";

    // External commands
    String TREE = "tree";
    String ATTRIB = "attrib";
    String HELP = "help";
}
