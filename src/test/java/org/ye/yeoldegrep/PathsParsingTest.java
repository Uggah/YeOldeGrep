package org.ye.yeoldegrep;

import org.junit.Assert;
import org.junit.Test;
import org.ye.yeoldegrep.parser.ArgumentParser;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsParsingTest {
    @Test
    public void pathSingleParsingTest(){
        //Test some Argument-Variations with only one Path
        String[] args1 = new String[]{"-il","query","./file"};
        String[] args2 = new String[]{"query","/someFile"};
        String[] args3 = new String[]{};
        String[] args4 = new String[]{"/file"};
        String[] args5 = new String[]{"-il"};
        Assert.assertArrayEquals(new Path[]{Paths.get("file")}, ArgumentParser.getFilePaths(args1));
        Assert.assertArrayEquals(new Path[]{Paths.get("/someFile")}, ArgumentParser.getFilePaths(args2));
        Assert.assertArrayEquals(null, ArgumentParser.getFilePaths(args3));
        Assert.assertArrayEquals(null, ArgumentParser.getFilePaths(args4));
        Assert.assertArrayEquals(null, ArgumentParser.getFilePaths(args5));
    }

    @Test
    public void pathMultipleParsingTest(){
        //Test some Argument-Variations with multiple Paths
        String[] args1 = new String[]{"-il","query","/file","more/someOtherFile","/eeeh/evenMoreFiles"};
        String[] args2 = new String[]{"query","file","someOtherFile"};
        String[] args3 = new String[]{};
        String[] args4 = new String[]{"--ignore-case","/filebutactuallyquery","someStrangeStuff"};
        String[] args5 = new String[]{"-il"};
        Assert.assertArrayEquals(new Path[]{Paths.get("/file"),Paths.get("more/someOtherFile"),Paths.get("/eeeh/evenMoreFiles")}, ArgumentParser.getFilePaths(args1));
        Assert.assertArrayEquals(new Path[]{Paths.get("file"),Paths.get("someOtherFile")}, ArgumentParser.getFilePaths(args2));
        Assert.assertArrayEquals(null, ArgumentParser.getFilePaths(args3));
        Assert.assertArrayEquals(new Path[]{Paths.get("someStrangeStuff")}, ArgumentParser.getFilePaths(args4));
        Assert.assertArrayEquals(null, ArgumentParser.getFilePaths(args5));
    }
}
