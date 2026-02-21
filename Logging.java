import java.time.LocalDateTime;
import java.util.Map;

/**
 * Logger LLD
 * --------------
 * 
 * Functional Requirements

Support log levels:
DEBUG
INFO
WARN
ERROR
FATAL

Should allow:
logger.info("User created successfully");
logger.error("Payment failed", exception);

Ability to:
Change log level at runtime.
Disable lower level logs.
Format logs (timestamp, thread name, class name, etc.)
Support multiple output destinations (appenders):
Console
File

Database (optional)
Remote log server (optional)

Each class should get its own logger instance:
Logger logger = LoggerFactory.getLogger(PaymentService.class);

Non-Functional Requirements
Must be thread-safe.
High performance (minimal latency).
Should not block main business threads.
Should support asynchronous logging.
Extensible (Open/Closed principle).

Entity --
Logger - Used by client to Log
LoggerFactory - Used to give a log class instance (Each class will a singleton class)
    - take client class name as argument
LogLevel

LogHandler
ErrorLoggerHandler
DebugLoggerHandler
InfoLoggerHandler

LogViewer
ConsolerLogViewer
FileLogViewer

If the log level = debug or info or error, it will be only be logged into console
If the log level = error, it will be logged to file

Priotiry List --
Debug -> Info -> Error
If user has enabled debug logging in the Log View then, info and error logs will also come

classes --
Client - main()
Logger = LoggerFactory.getLogger("classname")
- LogLevel
- LogHandler
* log(message, Log level)

LoggerFactory
- Map<name, Logger>
* getLogger

LogHandler
- LogViewer
* log(message, LogLevel)  -> calls the handler based on the log level

ErrorLogHandler
* log(message) -> "Error : " + message
SAME FOR ALL OTHER HANDLERS

LogViewer
* append(message, log level)
 */

class Client {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("Client");
        logger.log("THis is info.", LogLevel.INFO);
        logger.log("This is error", LogLevel.ERROR);
        logger.log("This is error.", LogLevel.ERROR);
    }
}
enum LogLevel {
    DEBUG(1), INFO(2), ERROR(3);
    private final int value;
    private LogLevel(int value) {
        this.value = value;
    }
}

class LoggerFactory {
    private static Map<String, Logger> loggerMap;
    public static Logger getLogger(String className) {
        if(loggerMap.containsKey(className))
            return loggerMap.get(className);
        else {
            Logger logger = new Logger();
            loggerMap.put(className, logger);
            return logger;
        }
    }
}

class Logger {
    LogLevel logLevel;
    LogHandler logHandler;
    public void log(String message, LogLevel logLevel) {
        logHandler.log(message, logLevel);
    }
}

abstract class LogHandler {
    LogViewer logViewer;
    public abstract void log(String message, LogLevel logLevel);
}

class ErrorLogHandler extends LogHandler {
    public void log(String message, LogLevel logLevel) {
        message += LocalDateTime.now() + " : ERROR : " + message;
        logViewer = LogViewerFactory.getLogViewer("ERROR");
        logViewer.append(message);
    }
}

/** Similarily do to all Log handlers */

class LogViewerFactory {
    static Map<String, LogViewer> logViewerMap;
    public LogViewer() {
        logViewerMap = new HashMao<>() {{
            put("ERROR", new ErrorLogViewer());
        }};
    }
    public static LogViewer getLogViewer(String logViewerKey) {
        return logViewerMap.get(logViewerKey);
    }
}

abstract class LogViewer {
    public abstract void apply(String message);
}

class ErrorLogViewer extends LogViewer {
    List<String> messages;
    public void append(String message) {
        messages.add(message);
    }
}

/** Similarily do to all Log Viewer */

/*
Concurrency -- 
We can use CopyOnWriteArrayList and ConcurrentHashMap
Atomtic Integer

ReenterrantReadWrite lock -- .readLock().lock()

using Messaging Queue  - BlockingQueue
exmaple below

*/
