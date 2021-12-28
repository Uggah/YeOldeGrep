package org.ye.yeoldegrep.handler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class with Methods to handle all sort of helping things for users
 */
public class HelpHandler {

    /**
     * Method that sends the --help-string (a man-page-excerpt)
     */
    public static void sendHelp() {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("manpage");
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
        }
    }

    public static void printInstructions(){
        System.out.println("Usage: java -jar YeOldeGrep-0.1.jar [OPTION] QUERY [FILE]\n" +
                "Try 'java -jar YeOldeGrep-0.1.jar --help' for more information.");
    }
}
