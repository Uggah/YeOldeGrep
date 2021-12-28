package org.ye.yeoldegrep.handler;

/**
 * Class with Methods to handle all sort of helping things for users
 */
public class HelpHandler {

    /**
     * Method that sends the --help string (a man-page-excerpt)
     */
    public static void sendHelp() {
        System.out.println("""
                """); //TODO Add actual content
    }

    public static void printInstructions(){
        System.out.println("Usage: java -jar YeOldeGrep-0.1.jar [OPTION] QUERY [FILE]\n" +
                "Try 'java -jar YeOldeGrep-0.1.jar --help' for more information.");
    }
}
