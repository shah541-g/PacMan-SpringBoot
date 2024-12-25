package utils.loggingUtilities;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.logging.*;

public class LoggerUtility {
    private static final Map<String, Logger> loggers = new ConcurrentHashMap<>();
    private static final String LOG_FOLDER = "logs";

    static {
        ensureLogDirectoryExists();
    }

    /**
     * Get or create a logger instance.
     */
    public static Logger getLogger(String name, String logFileName) {
        return loggers.computeIfAbsent(name, loggerName -> createLogger(loggerName, logFileName));
    }

    private static Logger createLogger(String name, String logFileName) {
        ensureLogDirectoryExists(); // Ensure directory exists

        Logger logger = Logger.getLogger(name);
        try {
            String logFilePath = new File(LOG_FOLDER, logFileName).getPath();
            System.out.println("Creating log file: " + logFilePath);

            FileHandler fileHandler = new FileHandler(logFilePath, true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.setUseParentHandlers(false);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("Failed to configure logger for " + name + ": " + e.getMessage());
        }
        return logger;
    }

    private static void ensureLogDirectoryExists() {
        File logDir = new File(LOG_FOLDER);
        if (!logDir.exists()) {
            boolean created = logDir.mkdir();
            if (created) {
                System.out.println("Log directory created: " + logDir.getAbsolutePath());
            } else {
                System.err.println("Failed to create log directory: " + LOG_FOLDER);
            }
        }
    }

    public static String[] getExistingLoggerFiles() {
        File logDir = new File(LOG_FOLDER);
        if (!logDir.exists()) {
            return new String[0];
        }
        return logDir.list((dir, name) -> name.endsWith(".log"));
    }
}
