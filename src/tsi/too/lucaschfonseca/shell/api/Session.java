package tsi.too.lucaschfonseca.shell.api;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Session {

    private String currentDirectory;
    private String path;
    private String identifier;

    private LocalDateTime creationTime;
    private LocalDateTime closeTime;

    private final Map<StringBuilder, StringBuilder> environmentVariables;

    /*
     * Gets all the operating system environment variables and the value of the PATH
     * environment variable to configure, respectively, the path and
     * environmentVariables. * /
     */
    public Session() {
        creationTime = LocalDateTime.now();

        var env = System.getenv();
        setPath(env.get("PATH"));

        environmentVariables = env.entrySet().stream()
                .collect(Collectors.toMap(e -> new StringBuilder(e.getKey()), e -> new StringBuilder(e.getValue())));
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(String currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<StringBuilder, StringBuilder> getEnvironmentVariables() {
        return environmentVariables;
    }

    public String getEnvironmentVariable(String name) {
        if (environmentVariables.containsKey(new StringBuilder(name)))
            return environmentVariables.get(new StringBuilder(name)).toString();

        return null;
    }

    public void updateEnvironmentVariable(String name, String value) {
        environmentVariables.put(new StringBuilder(name), new StringBuilder(value));
    }

    public void closeSession(){

    }

//	Isso nao faz sentido pra mim. Uma vez que uma superclasse ou interface nao deve conhecer suas
//	subclasses/implementações.
//	public static Session create();
}