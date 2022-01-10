package org.ye.yeoldegrep;

import org.ye.yeoldegrep.handler.HelpHandler;
import org.ye.yeoldegrep.handler.SearchHandler;
import org.ye.yeoldegrep.parser.ArgumentParser;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public static void main(String[] args) throws IOException {
        List<String> allArgs = new ArrayList<String>();
        for(String in:args) {
            allArgs.add(in);
        }
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String curLine = null;
        while( (curLine = input.readLine()) != null ) {
            allArgs.add(curLine);
        }
        args = allArgs.toArray(new String[0]);

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
