package com.dido.exercise.logger;

public interface Layout {

    public String format(String date, String level, String message);
}
