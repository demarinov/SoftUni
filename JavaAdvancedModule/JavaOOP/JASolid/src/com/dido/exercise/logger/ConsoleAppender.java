package com.dido.exercise.logger;

public class ConsoleAppender implements Appender{

    private Layout layout;
    private ReportLevel reportLevel;
    private int messagesAppended;

    public ConsoleAppender(Layout layout) {

        setLayout(layout);
        setReportLevel(ReportLevel.INFO);
        messagesAppended =0;
    }

    public Layout getLayout() {
        return layout;
    }

    private void setLayout(Layout layout) {
        this.layout = layout;
    }

    public ReportLevel getReportLevel() {
        return reportLevel;
    }

    public void setReportLevel(ReportLevel reportLevel) {
        this.reportLevel = reportLevel;
    }

    @Override
    public void append(String date, ReportLevel logLevel ,String message) {

        if (logLevel.ordinal() >= reportLevel.ordinal()) {
            String formattedOutput = layout.format(date, logLevel.getValue(), message);
            System.out.println(formattedOutput);
            messagesAppended++;
        }
    }

    @Override
    public String toString() {
        return String.format("Appender type: %s, Layout type: %s, " +
                        "Report level: %s, Messages appended: %d"
                ,getClass().getSimpleName(),getLayout().getClass().getSimpleName()
                ,reportLevel.getValue(),messagesAppended);
    }
}
