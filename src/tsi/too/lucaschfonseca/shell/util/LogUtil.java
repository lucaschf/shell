package tsi.too.lucaschfonseca.shell.util;

import tsi.too.lucaschfonseca.shell.api.Session;
import tsi.too.lucaschfonseca.shell.model.ExternalSystemShellCommand;

import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;

public class LogUtil {
    private LogUtil() {
        // prevent instantiation
    }

    public static void generateLog(Session session) {
        try (FileWriter writer = new FileWriter(generateFileName(session))) {
            String s = "%s\n\n%s".formatted(generateFileIdentifier(session), generateCommandsLog(session));
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateFileIdentifier(Session session) {
        var uptime = session.getUptimeInSeconds();
        String time;

        if (uptime > 60) {
            uptime = uptime / 60;
            time = "Minutos";
        } else
            time = "Segundos";

        return MessageFormat.format("{0}\nArquivo: {1}\nData: {2}\nHora: {3}\nTempo: {4} {5}",
                generateHeaderLog("Identificação do arquivo"),
                generateFileName(session),
                session.getCreationTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                session.getCreationTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                uptime,
                time
        );
    }

    private static String generateCommandsLog(Session session) {
        var counter = new Object() {
            int external = 0;
            int internal = 0;
        };

        StringBuilder sb = new StringBuilder(
                generateHeaderLog(String.format("Comandos executados no %s", session.getIdentifier())));

        session.getCommands().forEach(c -> {
            sb.append("\n").append(c.toString());
            if (c instanceof ExternalSystemShellCommand)
                counter.external++;
            else
                counter.internal++;
        });

        sb.append("\n\n")
                .append(generateHeaderLog("Número de comandos executados"))
                .append("\nComandos internos: %d".formatted(counter.internal))
                .append("\nComandos externos: %d".formatted(counter.external))
        ;

        return sb.toString();
    }

    private static String generateFileName(Session session) {
        return session.getIdentifier().concat(".log");
    }

    private static String generateHeaderLog(String header) {
        var s = "-".repeat(10);

        return String.format("%s %s %s\n", s, header, s);
    }
}
