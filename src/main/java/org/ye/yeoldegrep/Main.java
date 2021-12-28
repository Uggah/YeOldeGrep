package org.ye.yeoldegrep;

import org.ye.yeoldegrep.handler.HelpHandler;
import org.ye.yeoldegrep.handler.SearchHandler;
import org.ye.yeoldegrep.parser.ArgumentParser;

import java.util.Arrays;

/**
 * Main class which acts as interface between the other classes
 */
public class Main {
    /**
     * <p>Calls the search method with the parsed input arguments</p>
     * <p>In case of invalid user input, how-to instructions as well as a exception message will be printed</p>
     *
     * @param args User input
     */
    public static void main(String[] args) {
        try {
            if(Arrays.stream(args).anyMatch("--help"::equals)) {
                HelpHandler.sendHelp();
            } else {
                SearchHandler.search(ArgumentParser.getQuery(args), ArgumentParser.getOpts(args), ArgumentParser.getFilePaths(args));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            HelpHandler.printInstructions();
            System.exit(1);
        }
    }
}
