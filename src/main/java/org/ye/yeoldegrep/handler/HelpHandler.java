package org.ye.yeoldegrep.handler;

/**
 * Class with Methods to handle all sort of helping things for users
 */
public class HelpHandler {

    private final static String INSTRUCTION = "Usage: java -jar YeOldeGrep-0.1.jar [OPTIONS] QUERY [FILE...]\n" +
            "Try 'java -jar YeOldeGrep-0.1.jar --help' for more information.";

    /**
     * Method that sends the --help string (a man-page-excerpt)
     */
    public static void sendHelp() {
        System.out.println("""
                """); //TODO Add actual content
    }

    /**
     * <p>Method that prints short usage instructions</p>
     */
    public static void printInstructions(){
        System.out.println(INSTRUCTION);
    }
}
