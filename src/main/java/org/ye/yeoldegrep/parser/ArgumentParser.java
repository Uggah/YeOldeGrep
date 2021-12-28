package org.ye.yeoldegrep.parser;

import org.ye.yeoldegrep.exceptions.InvalidOptionException;
import org.ye.yeoldegrep.exceptions.MissingFilePathException;
import org.ye.yeoldegrep.exceptions.MissingQueryException;
import org.ye.yeoldegrep.handler.YeHandler;
import org.ye.yeoldegrep.utils.Option;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contains methods to parse the arguments given by the user into usable formats for searching
 */

public class ArgumentParser {

    /**
     * <p>Parses the raw arguments given by a user into an Option-Array.</p>
     *
     * Returns an empty list if no Option was given.
     *
     * @param rawArgs The users arguments
     * @return An Array of Option-Objects
     * @throws InvalidOptionException if a given option was invalid
     */
    public static Option[] getOpts(String[] rawArgs) throws InvalidOptionException {
        List<Option> args = new ArrayList<Option>();

        for(String rawArg : rawArgs) {
            if(rawArg.startsWith("-") && rawArg.length() > 1) {
                if(rawArg.startsWith("--") && rawArg.length() > 2) {
                    //LongAlias
                    args.add(Option.getByLongAlias(rawArg.substring(2)));
                } else {
                    //ShortAlias - Maybe multiple
                    for (int i = 1; i < rawArg.length(); i++){
                         args.add(Option.getByShortAlias(rawArg.charAt(i)));
                    }
                }
            }
        }

        // Clear duplicates
        List<Option> finalArgs = args.stream()
                .distinct()
                .collect(Collectors.toList());

        if(finalArgs.size() >= 0 && !(finalArgs.contains(null))) {
            // Return list if no null in it
            return finalArgs.toArray(new Option[0]);
        }
        // Throw exception if invalid option was given
        throw new InvalidOptionException();
    }

    /**
     * <p>Returns the query-String given by the users arguments.<br>
     * This is expected to be the first non-option argument.</p>
     *
     * @param rawArgs The users arguments
     * @return The query-String or null
     * @throws MissingQueryException if there was no query given
     */
    public static String getQuery(String[] rawArgs) throws MissingQueryException {
        //Find first non-option-argument as that is supposed to be query
        for(String rawArg : rawArgs) {
            if(rawArg.startsWith("-")) {
                //ye
                if(rawArg.equals("--ye")) {
                    YeHandler.ye();
                }
                continue;
            }
            return rawArg;
        }

        //Throw exception if there is no query
        throw new MissingQueryException();
    }

    /**
     * <p>Returns an Array with all File-Paths that ought to be searched</p>
     *
     * @param rawArgs The users arguments
     * @return An Array of Path-Objects or null
     * @throws InvalidPathException if the given path was invalid
     * @throws MissingFilePathException if there was no file/path given
     */
    public static Path[] getFilePaths(String[] rawArgs) throws InvalidPathException, MissingFilePathException {
        List<Path> paths = new ArrayList<Path>();

        //Find all non-option-arguments after the first one
        boolean firstOne = true;
        for(String rawArg : rawArgs) {
            //Option or first non-option-argument (thus query) -> Continue
            if(rawArg.startsWith("-")) {
                continue;
            } else if (firstOne) {
                firstOne = false;
                continue;
            }


            if(rawArg.startsWith("/") || rawArg.substring(1).startsWith(":\\")) {
                // Absolute path
                paths.add(Paths.get(rawArg));
            } else {
                // Relative path given -> Convert to absolute path
                if(rawArg.startsWith("./")) {
                    rawArg = rawArg.substring(2);
                }
                paths.add(Paths.get(rawArg));
            }
        }

        // Clear duplicates
        List<Path> finalPaths = paths.stream()
                .distinct()
                .collect(Collectors.toList());

        if(finalPaths.size() > 0) {
            // Return list if there is something in it
            return finalPaths.toArray(new Path[0]);
        }
        // Return null if there was no Path/an invalid one
        throw new MissingFilePathException();
    }
}
