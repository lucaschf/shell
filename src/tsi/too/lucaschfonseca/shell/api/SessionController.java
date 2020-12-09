package tsi.too.lucaschfonseca.shell.api;

import tsi.too.lucaschfonseca.shell.impl.ShellSession;
import tsi.too.lucaschfonseca.shell.model.ExternalSystemShellCommand;

import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SessionController {
    private static ArrayList<Session> activeSessions = new ArrayList<>();

    private static SessionController instance;

    private SessionController() {
        // prevent instantiation
    }

    public static SessionController getInstance() {
        synchronized (SessionController.class) {
            if (instance == null)
                instance = new SessionController();

            return instance;
        }
    }

    public Session createSession(String identifier) {
        if (identifier == null || identifier.isBlank())
            identifier = "Shell %d".formatted(activeSessions.size() + 1);

        var s = new ShellSession(identifier);
        activeSessions.add(s);
        return s;
    }

    public void closeSession(Session session) {
        generateLog(session);
        activeSessions.remove(session);
        session = null;
    }

    private void generateLog(Session session) {
        try (FileWriter writer = new FileWriter(generateFileName(session))) {
            String s = "%s\n\n%s".formatted(generateFileIdentifier(session), generateCommandsLog(session));
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateFileIdentifier(Session session) {
        return MessageFormat.format("{0}\nArquivo: {1}\nData: {2}\nHora: {3}\nTempo: {4} minutos",
                generateHeaderLog("Identificação do arquivo"),
                generateFileName(session),
                session.getCreationTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                session.getCreationTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                session.getUptime()
        );
    }

    private String generateCommandsLog(Session session) {
        var counter = new Object() {
            int external = 0;
            int internal = 0;
        };

        StringBuilder sb = new StringBuilder(
                generateHeaderLog(String.format("Comandos executados no %s", session.getIdentifier())));

        session.getCommands().forEach(c -> {
            sb.append(c.toString());
            if (c instanceof ExternalSystemShellCommand)
                counter.external++;
            else
                counter.internal++;
        });

        sb.append("\n")
                .append(generateHeaderLog("Número de comandos executados"))
                .append("\nComandos internos: %d".formatted(counter.internal))
                .append("\nComandos externos: %d".formatted(counter.external))
        ;

        return sb.toString();
    }

    private String generateFileName(Session session) {
        return session.getIdentifier().concat(".log");
    }

    private String generateHeaderLog(String header) {
        var s = "-".repeat(10);

        return String.format("%s %s %s\n", s, header, s);
    }
}
