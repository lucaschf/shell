package tsi.too.lucaschfonseca.shell.api;

public class NoSuchCommandException extends Exception{
    public NoSuchCommandException() {
    }

    public NoSuchCommandException(String message) {
        super(message);
    }

    public NoSuchCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}