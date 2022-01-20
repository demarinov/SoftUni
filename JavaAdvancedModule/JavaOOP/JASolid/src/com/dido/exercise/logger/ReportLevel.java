package com.dido.exercise.logger;

public enum ReportLevel {

    // Info > Warning > Error > Critical > Fatal.
    INFO("INFO"),WARNING("WARNING"),
    ERROR("ERROR"),CRITICAL("CRITICAL"),
    FATAL("FATAL");

    private String value;

    ReportLevel(String value) {
        setValue(value);
    }

    public static ReportLevel getLevelByName(String name) {
        for (ReportLevel level : ReportLevel.values()) {

            if (name.equals(level.getValue())) {
                return level;
            }
        }

        return null;
    }

    public String getValue() {
        return value;
    }

    private void setValue(String value) {
        this.value = value;
    }
}
