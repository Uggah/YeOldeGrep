package org.ye.yeoldegrep;

import org.ye.yeoldegrep.parser.ArgumentParser;

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
            Search.search(ArgumentParser.getQuery(args), ArgumentParser.getOpts(args), ArgumentParser.getFilePaths(args));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
