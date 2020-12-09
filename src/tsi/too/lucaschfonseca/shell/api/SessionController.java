package tsi.too.lucaschfonseca.shell.api;

import tsi.too.lucaschfonseca.shell.impl.ShellSession;
import tsi.too.lucaschfonseca.shell.util.LogUtil;

import java.util.ArrayList;

public class SessionController {
    private static final ArrayList<Session> activeSessions = new ArrayList<>();

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
        LogUtil.generateLog(session);
        activeSessions.remove(session);
    }
}