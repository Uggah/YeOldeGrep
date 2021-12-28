package org.ye.yeoldegrep;

import org.ye.yeoldegrep.utils.Option;

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
     * @param opts Array of options
     * @param paths Array of file paths
     */

    public static void search (String query, Option[] opts, Path[] paths) {

        File[] files= new File[paths.length];

        for (int f = 0; f < files.length; f++){
            files[f] = paths[f].toFile();
        }

        List<Option> optList = Arrays.asList(opts); //TODO: Change this to use the given array instead of a list...

        if (optList.contains(Option.IgnoreCase) && optList.contains(Option.FilesWithMatches)){
            caseInsensitiveMatchSearch(query, files);
        } else if(optList.contains(Option.IgnoreCase)){
            caseInsensitiveSearch(query, files);
        } else if (optList.contains(Option.FilesWithMatches)){
            matchSearch(query, files);
        } else {
            standardSearch(query, files);
        }
    }

    /**
     * This method searches for the given query in the given files while staying case sensitive.
     *
     * @param query Search query
     * @param files Files to be searched
     */

    private static void standardSearch (String query, File[] files) {
        for(File f : files){

            try {
                Scanner fileScanner = new Scanner(f);
                String fileContents = "";

                while(fileScanner.hasNext()){
                    fileContents = fileContents.concat(fileScanner.next() + " ");
                }

                String[] fileContentsArray = fileContents.split(" ");
                String[] fileContentsLines = fileContents.split("\n");

                for(String s : fileContentsArray){
                    if(s.equals(query)){
                        String line = "";
                        for(String fileLine : fileContentsLines){
                            if(fileLine.contains(s)){
                                line = fileLine;
                            }
                        }
                        System.out.println(f.getName() + ":" + line);
                    }
                }
            } catch (FileNotFoundException e){
                System.err.println("YeOldeGrep: " + f.getName() + ": File not found");
            }

        }
    }

    /**
     * This method searches<em>case insensitively</em> for the given query in the given files.
     *
     * @param query Search query
     * @param files Files to be searched
     */
    private static void caseInsensitiveSearch (String query, File[] files){

        for(File f : files){

            try {
                Scanner fileScanner = new Scanner(f);

                while(fileScanner.hasNextLine()){
                    String line = fileScanner.nextLine();
                    String lowerCaseLine = line.toLowerCase();
                    if(lowerCaseLine.contains(query.toLowerCase())){
                        System.out.println(f.getName() + ":" + line);
                    }
                }
            } catch (FileNotFoundException e){
                System.err.println("YeOldeGrep: " + f.getName() + ": File not found");
            }
        }
    }

    /**
     * A method which scans all files and prints those files containing the query.
     *
     * @param query Search query
     * @param files Files to be searched
     */
    private static void matchSearch(String query, File[] files){

        for(File f : files){

            try {
                Scanner fileScanner = new Scanner(f);

                while(fileScanner.hasNextLine()){
                    if(fileScanner.nextLine().contains(query)){
                        System.out.println(f.getName());
                    }
                }
            } catch (FileNotFoundException e){
                System.err.println("YeOldeGrep: " + f.getName() + ": File not found");
            }
        }
    }

    /**
     * A method which scans all files and prints those files containing the query case insensitively.
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
                    fileContents = fileContents.concat(fileScanner.next() + " ");
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
