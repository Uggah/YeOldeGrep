package org.ye.yeoldegrep.parser;

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
     * Returns an empty list if no Option was given.<br>
     * Returns null if an invalid Option was given.
     *
     * @param rawArgs The users arguments
     * @return An Array of Option-Objects or null
     */
    public static Option[] getOpts(String[] rawArgs) {
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
        // Return null if an argument couldn't be parsed
        return null;
    }

    /**
     * <p>Returns the query-String given by the users arguments.<br>
     * This is expected to be the first non-option argument.</p>
     *
     * Returns null if there is no query given.
     *
     * @param rawArgs The users arguments
     * @return The query-String or null
     */
    public static String getQuery(String[] rawArgs) {
        //Find first non-option-argument as that is supposed to be query
        for(String rawArg : rawArgs) {
            if(rawArg.startsWith("-")) {
                continue;
            }
            return rawArg;
        }
        return null;
    }

    /**
     * <p>Returns an Array with all File-Paths that ought to be searched</p>
     *
     * Returns null if an invalid path was given.<br>
     * Returns null if no path was given.
     *
     * @param rawArgs The users arguments
     * @return An Array of Path-Objects or null
     */
    public static Path[] getFilePaths(String[] rawArgs) {
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

            try {
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
            } catch (InvalidPathException e) {
                // Invalid-Path -> Return null
                return null;
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
        return null;
    }
}
