package com.dido.exercise.logger;


import java.util.Scanner;

public class LoggerMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            // •	"<appender type> <layout type> <REPORT LEVEL>"
            //•	"<appender type> <layout type>"
            String[] appenderData = sc.nextLine().split("\\s");
        }


        String input = sc.nextLine();

        while("End".equals(input)) {

            // log messages

            input = sc.nextLine();
        }

        Layout simpleLayout = new SimpleLayout();
        Appender consoleAppender = new ConsoleAppender(simpleLayout);

        Layout xmlLayout = new XmlLayout();

        File file = new LogFile();
        Appender fileAppender = new FileAppender(xmlLayout);
        ((FileAppender) fileAppender).setFile(file);

        Logger logger = new MessageLogger(consoleAppender, fileAppender);

//        logger.logError("3/26/2015 2:08:11 PM", "Error parsing JSON.");
//        logger.logInfo("3/26/2015 2:08:11 PM", "User Pesho successfully registered.");
        logger.logError("3/31/2015 5:33:07 PM", "Error parsing request");

        System.out.println(((LogFile)((FileAppender) fileAppender).getFile()).getSize());
    }
}
