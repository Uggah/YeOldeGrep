package org.ye.yeoldegrep;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ye.yeoldegrep.utils.Argument;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SearchTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream origOut = System.out;
    private final PrintStream origErr = System.err;

    /**
     * This method sets up the streams used for the tests.
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void caseInsensitiveMatchSearchTest(){
        // Create variables to test the search with.
        Path[] paths = new Path[]{Paths.get("src/test/resources/TestFile1"), Paths.get("src/test/resources/TestFile2")};
        Path[] faultyPaths = new Path[]{Paths.get("src/test/resources/NonExistentFile1"), Paths.get("\"src/test/resources/NonExistentFile2")};
        Argument[] args = new Argument[]{Argument.IgnoreCase, Argument.FilesWithMatches};

        // Test search with actual files
        Search.search("tEst", args, paths);
        Assert.assertEquals("TestFile1\n", outContent.toString());

        // Test search with non-existent files
        Search.search("Test", args, faultyPaths);
        Assert.assertEquals("""
                YeOldeGrep: NonExistentFile1: File not found
                YeOldeGrep: NonExistentFile2: File not found
                """, errContent.toString());
    }

    @Test
    public void standardSearchTest(){
        // Create variables to test the search with.
        Path[] paths = new Path[]{Paths.get("src/test/resources/TestFile1"), Paths.get("src/test/resources/TestFile2")};
        Path[] faultyPaths = new Path[]{Paths.get("src/test/resources/NonExistentFile1"), Paths.get("\"src/test/resources/NonExistentFile2")};
        Argument[] args = new Argument[]{};

        // Test search with actual files
        Search.search("Test", args, paths);
        Assert.assertEquals("TestFile1:Dies ist ein Test \n", outContent.toString());

        // Test search with non-existent files
        Search.search("Test", args, faultyPaths);
        Assert.assertEquals("""
                YeOldeGrep: NonExistentFile1: File not found
                YeOldeGrep: NonExistentFile2: File not found
                """, errContent.toString());
    }

}
