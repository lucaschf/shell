package tsi.too.lucaschfonseca.shell.api;

import tsi.too.lucaschfonseca.shell.model.Command;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Session {
    private String currentDirectory;
    private String path;
    private String identifier;

    private ArrayList<Command> commands = new ArrayList<>();

    private LocalDateTime creationTime;
    private Map<StringBuilder, StringBuilder> environmentVariables;

    /*
     * Gets all the operating system environment variables and the value of the PATH
     * environment variable to configure, respectively, the path and
     * environmentVariables. * /
     */
    public Session() {
        identifier = "";
        creationTime = LocalDateTime.now();
        populateEnvironmentVariables();
    }

    public Session(String identifier) {
        this();
        this.identifier = identifier;
    }

    private void populateEnvironmentVariables() {
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

    public String getIdentifier() {
        return identifier;
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

    public void addCommandLog(Command command){
        commands.add(command);
    }

    public List<Command> getCommands(){
        return Collections.unmodifiableList(commands);
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public long getUptime() {
        return creationTime.until(LocalDateTime.now(), ChronoUnit.MINUTES);
    }

//	Isso nao faz sentido pra mim. Uma vez que uma superclasse ou interface nao deve conhecer suas
//	subclasses/implementações. E nao e possivel fazer a instanciacao direta de uma classe abstrata.
//	public static Session create();
}