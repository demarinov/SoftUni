package com.dido.exercise.logger;

public class FileAppender implements Appender{

    private Layout layout;
    private File file;
    private ReportLevel reportLevel;
    private int messagesAppended;

    public FileAppender(Layout layout) {
        setLayout(layout);
        setReportLevel(ReportLevel.INFO);
        messagesAppended = 0;
    }

    public void setReportLevel(ReportLevel reportLevel) {
        this.reportLevel = reportLevel;
    }

    public Layout getLayout() {
        return layout;
    }

    private void setLayout(Layout layout) {
        this.layout = layout;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public void append(String date, ReportLevel logLevel, String message) {
        String formattedOutput = layout.format(date, logLevel.getValue(), message);

        if (logLevel.ordinal() >= reportLevel.ordinal()) {
            file.write(formattedOutput);
            messagesAppended++;
        }
    }

    @Override
    public String toString() {
        return String.format("Appender type: %s, Layout type: %s, " +
                "Report level: %s, Messages appended: %d, File size: %d"
                ,getClass().getSimpleName(),getLayout().getClass().getSimpleName()
                ,reportLevel.getValue(),messagesAppended,((LogFile)getFile()).getSize());
    }
}
