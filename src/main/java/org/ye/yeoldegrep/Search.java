package org.ye.yeoldegrep;

import org.ye.yeoldegrep.utils.Argument;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Search {

    /**
     * <p>This method hands over the search request to the responsible method
     * after converting the data types to a more usuable format.</p>
     *
     * @param query Search query
     * @param args Array of arguments
     * @param paths Array of file paths
     */

    public static void standardSearch (String query, Argument[] args, Path[] paths) {

        File[] files= new File[paths.length];

        for (int f = 0; f < files.length; f++){
            files[f] = paths[f].toFile();
        }

        List<Argument> argList = Arrays.asList(args); //TODO: Change this to use the given array instead of a list...

        if (argList.contains(Argument.IgnoreCase) && argList.contains(Argument.FilesWithMatches)){
            caseInsensitiveMatchSearch(query, files);
        } else if(argList.contains(Argument.IgnoreCase)){
            caseInsensitiveSearch(query, files);
        } else if (argList.contains(Argument.FilesWithMatches)){
            matchSearch(query, files);
        }
    }



    private static void caseInsensitiveSearch (String query, File[] files){

        //TODO: Implementation & Documentation

    }

    private static void matchSearch(String query, File[] files){

        //TODO: Implementation & Documentation

    }

    /**
     * A method which scans all files and prints those files containing the query or a derivative thereof to console.
     *
     * @param query Search query
     * @param files Files to be searched
     */

    private static void caseInsensitiveMatchSearch(String query, File[] files) {

        for(File f : files){

            try {
                Scanner fileScanner = new Scanner(f);
                String fileContents = "";

                while(fileScanner.hasNext()){
                    fileContents = fileContents.concat(fileScanner.next());
                }

                String[] fileContentsArray = fileContents.split(" ");

                for(String s : fileContentsArray){
                    if(s.equalsIgnoreCase(query)){
                        System.out.println(f.getName());
                    }
                }
            } catch (FileNotFoundException e){
                System.err.println("YeOldeGrep: " + f.getName() + ": File not found");
            }

        }

    }

}
