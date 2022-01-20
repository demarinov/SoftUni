package com.dido.exercise.logger;

public interface Appender {

    public void append(String date, ReportLevel logLevel ,String message);
}
