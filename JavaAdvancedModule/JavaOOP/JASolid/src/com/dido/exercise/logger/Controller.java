package com.dido.exercise.logger;

import java.util.Scanner;

public class Controller {

    private static Scanner sc = new Scanner(System.in);
    private Logger logger;

    public static void main(String[] args) {
        Logger logger = new MessageLogger();
        Controller controller = new Controller(logger);

        controller.readAppenders();
        controller.readMessages();

    }

    public Controller(Logger logger) {
        setLogger(logger);
    }

    private void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void readAppenders() {

        // •	"<appender type> <layout type> <REPORT LEVEL>"
        //•	"<appender type> <layout type>"
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String[] appenderData = sc.nextLine().split("\\s");

            String appenderType = appenderData[0];
            String layoutType = appenderData[1];

            Layout layout = null;
            switch(layoutType) {

                case "SimpleLayout":
                    layout = new SimpleLayout();
                    break;
                case "XmlLayout":
                    layout = new XmlLayout();
                    break;
            }

            ReportLevel reportLevel = null;
            if (appenderData.length == 3) {
                String levelName = appenderData[2];
                reportLevel = ReportLevel.getLevelByName(levelName);
            }

            Appender appender = null;
            switch(appenderType) {

                case "ConsoleAppender":
                    appender = new ConsoleAppender(layout);
                    if (reportLevel != null) {
                        ((ConsoleAppender) appender).setReportLevel(reportLevel);
                    }
                    break;
                case "FileAppender":
                    File file = new LogFile();
                    appender = new FileAppender(layout);
                    ((FileAppender)appender).setFile(file);
                    if (reportLevel != null) {
                        ((FileAppender) appender).setReportLevel(reportLevel);
                    }
                    break;
            }

            ((MessageLogger) logger).addAppender(appender);
        }
    }

    public void readMessages() {
        // •	"<REPORT LEVEL>|<time>|<message>"
        String input = sc.nextLine();

        while(!"END".equals(input)) {

            String[] messageData = input.split("\\|");
            logMessage(messageData);
            input = sc.nextLine();
        }

        printAppenderData();
    }

    public void printAppenderData() {

        System.out.println("Logger info");
        for (Appender appender : ((MessageLogger) logger).getLogAppenderList()) {
            System.out.println(appender.toString());
        }
    }

    public void logMessage(String[] messageData) {

        String reportLevel = messageData[0];
        String date = messageData[1];
        String message = messageData[2];

        switch(reportLevel.toUpperCase()) {

            case "INFO":
                logger.logInfo(date,message);
                break;
            case "WARNING":
                logger.logWarning(date, message);
                break;
            case "ERROR":
                logger.logError(date, message);
                break;
            case "CRITICAL":
                logger.logCritical(date, message);
                break;
            case "FATAL":
                logger.logFatal(date, message);
                break;
        }
    }
}
