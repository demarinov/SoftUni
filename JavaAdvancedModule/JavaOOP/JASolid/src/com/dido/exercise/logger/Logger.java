package com.dido.exercise.logger;

public interface Logger {

    public void logError(String date, String message);
    public void logInfo(String date, String message);
    public void logWarning(String date, String message);
    public void logCritical(String date, String message);
    public void logFatal(String date, String message);
}
