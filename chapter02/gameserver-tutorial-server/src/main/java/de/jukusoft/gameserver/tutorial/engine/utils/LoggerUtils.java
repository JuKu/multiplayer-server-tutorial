package de.jukusoft.gameserver.tutorial.engine.utils;

import io.netty.handler.logging.LogLevel;

/**
 * Created by Justin on 26.10.2016.
 */
public class LoggerUtils {

    public static LogLevel convertToNettyLogLevel (org.apache.log4j.lf5.LogLevel logLevel) {
        switch (logLevel.getLabel()) {
            case "FATAL":
                return LogLevel.ERROR;
            case "ERROR":
                return LogLevel.ERROR;
            case "WARN":
                return LogLevel.WARN;
            case "WARNING":
                return LogLevel.DEBUG;
            case "INFO":
                return LogLevel.INFO;
            case "DEBUG":
                return LogLevel.DEBUG;
            case "CONFIG":
                return LogLevel.DEBUG;
        }

        throw new IllegalArgumentException("unsupported log level: " + logLevel.getLabel());
    }

}
