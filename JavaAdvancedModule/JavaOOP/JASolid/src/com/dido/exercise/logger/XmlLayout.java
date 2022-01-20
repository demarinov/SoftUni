package com.dido.exercise.logger;

public class XmlLayout implements Layout {
    @Override
    public String format(String date, String level, String message) {
        // implement xml format
        return String.format("<log>%n" +
                "    <date>%s</date>%n" +
                "    <level>%s</level>%n" +
                "    <message>%s</message>%n" +
                "</log>",date,level,message);
    }
}
