package com.dido.exercise.logger;


import java.util.ArrayList;
import java.util.List;

public class MessageLogger implements Logger{

    private List<Appender> logAppenderList;

    public MessageLogger(Appender logAppender) {
        logAppenderList = new ArrayList<>();
        setLogAppender(logAppender);
    }

    public MessageLogger(Appender ... logAppenders) {
        logAppenderList = new ArrayList<>();
        for (Appender logAppender : logAppenders) {
            setLogAppender(logAppender);
        }
    }

    public List<Appender> getLogAppenderList() {
        return logAppenderList;
    }

    private void setLogAppender(Appender logAppender) {
        logAppenderList.add(logAppender);
    }

    public void addAppender(Appender logAppender) {
        logAppenderList.add(logAppender);
    }

    @Override
    public void logError(String date, String message) {
        for (Appender logAppender : logAppenderList) {
            logAppender.append(date, ReportLevel.ERROR, message);
        }
    }

    @Override
    public void logInfo(String date, String message) {
        for (Appender logAppender : logAppenderList) {
            logAppender.append(date, ReportLevel.INFO, message);
        }
    }

    @Override
    public void logWarning(String date, String message) {
        for (Appender logAppender : logAppenderList) {
            logAppender.append(date, ReportLevel.WARNING, message);
        }
    }

    @Override
    public void logCritical(String date, String message) {
        for (Appender logAppender : logAppenderList) {
            logAppender.append(date, ReportLevel.CRITICAL, message);
        }
    }

    @Override
    public void logFatal(String date, String message) {
        for (Appender logAppender : logAppenderList) {
            logAppender.append(date, ReportLevel.FATAL, message);
        }
    }
}
