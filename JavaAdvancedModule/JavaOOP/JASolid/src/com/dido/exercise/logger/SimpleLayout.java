package com.dido.exercise.logger;

public class SimpleLayout implements Layout{

    @Override
    public String format(String date, String level, String message) {
        return String.format("%s - %s - %s",date, level, message);
    }
}
