package org.ye.yeoldegrep.parser;

import org.ye.yeoldegrep.utils.Argument;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArgumentParser {

    /**
     * Parses the raw arguments given by a user into an Argument-Array.
     *
     * Returns an empty list if no Argument was given.
     * Returns null if an invalid Argument was given.
     *
     * @param rawArgs The users arguments
     * @return An Array of Argument-Objects or null
     */
    public static Argument[] getArgs(String[] rawArgs) {
        List<Argument> args = new ArrayList<Argument>();

        for(String rawArg : rawArgs) {
            if(rawArg.startsWith("-") && rawArg.length() > 1) {
                if(rawArg.startsWith("--") && rawArg.length() > 2) {
                    //LongAlias
                    args.add(Argument.getByLongAlias(rawArg.substring(2)));
                } else {
                    //ShortAlias - Maybe multiple
                    for (int i = 1; i < rawArg.length(); i++){
                         args.add(Argument.getByShortAlias(rawArg.charAt(i)));
                    }
                }
            }
        }

        // Clear duplicates
        List<Argument> finalArgs = args.stream()
                .distinct()
                .collect(Collectors.toList());

        if(finalArgs.size() >= 0 && !(finalArgs.contains(null))) {
            // Return list if no null in it
            return (Argument[]) finalArgs.toArray();
        }
        // Return null if an argument couldn't be parsed
        return null;
    }

    /**
     * Returns the query-String given by the users arguments.
     * This is expected to be the first non-option argument.
     *
     * Returns null if there is no query given.
     *
     * @param rawArgs The users arguments
     * @return The query-String or null
     */
    public static String getQuery(String[] rawArgs) {
        //Find first non-option-argument as that is supposed to be query
        for(String rawArg : rawArgs) {
            if(!rawArg.startsWith("-")) {
                continue;
            }
            return rawArg;
        }

        return null;
    }

    /**
     * Returns an Array with all File-Paths that ought to be searched
     * Returns null if an invalid path was given.
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
            //Option or first non-Option-Argument (thus query) -> Continue
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
                    paths.add(Paths.get(System.getProperty("user.dir"),rawArg));
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
            return (Path[]) finalPaths.toArray();
        }
        // Return null if there was no Path/an invalid one
        return null;
    }
}
