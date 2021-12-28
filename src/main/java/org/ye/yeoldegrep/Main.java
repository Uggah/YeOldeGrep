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
            HelpHandler.printInstructions();
            e.printStackTrace();
            System.exit(1);
        }
    }
}
