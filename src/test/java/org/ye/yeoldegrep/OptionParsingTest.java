package org.ye.yeoldegrep;

import org.junit.Assert;
import org.junit.Test;
import org.ye.yeoldegrep.parser.ArgumentParser;
import org.ye.yeoldegrep.utils.Option;

public class OptionParsingTest {
    @Test
    public void optionParsingJustOptions(){
        //Test just arguments
        String[] args1 = new String[]{"-i","-l"};
        String[] args2 = new String[]{"-i","-w"};
        String[] args3 = new String[]{"-il"};
        String[] args4 = new String[]{"-iw"};
        Assert.assertArrayEquals(new Option[]{Option.IgnoreCase, Option.FilesWithMatches}, ArgumentParser.getOpts(args1));
        Assert.assertArrayEquals(null, ArgumentParser.getOpts(args2));
        Assert.assertArrayEquals(new Option[]{Option.IgnoreCase, Option.FilesWithMatches}, ArgumentParser.getOpts(args3));
        Assert.assertArrayEquals(null, ArgumentParser.getOpts(args4));
    }
    @Test
    public void optionParsingMixedArguments(){
        //Test some variety
        String[] args1 = new String[]{"-i","-l","query"};
        String[] args2 = new String[]{"-i","query","-l"};
        String[] args3 = new String[]{"query","-il"};
        String[] args4 = new String[]{"query","-iw"};
        Assert.assertArrayEquals(new Option[]{Option.IgnoreCase, Option.FilesWithMatches}, ArgumentParser.getOpts(args1));
        Assert.assertArrayEquals(new Option[]{Option.IgnoreCase, Option.FilesWithMatches}, ArgumentParser.getOpts(args2));
        Assert.assertArrayEquals(new Option[]{Option.IgnoreCase, Option.FilesWithMatches}, ArgumentParser.getOpts(args3));
        Assert.assertArrayEquals(null, ArgumentParser.getOpts(args4));
    }
    @Test
    public void optionParsingLongAliases(){
        //Test long aliases
        String[] args1 = new String[]{"--ignore-case","-l"};
        String[] args2 = new String[]{"--files-with-matches"};
        String[] args3 = new String[]{"--files-with-matches","--ignore-case"};
        String[] args4 = new String[]{"--ignorecase"};
        Assert.assertArrayEquals(new Option[]{Option.IgnoreCase, Option.FilesWithMatches}, ArgumentParser.getOpts(args1));
        Assert.assertArrayEquals(new Option[]{Option.FilesWithMatches}, ArgumentParser.getOpts(args2));
        Assert.assertArrayEquals(new Option[]{Option.FilesWithMatches, Option.IgnoreCase}, ArgumentParser.getOpts(args3));
        Assert.assertArrayEquals(null, ArgumentParser.getOpts(args4));
    }
    @Test
    public void optionParsingDuplicates(){
        //Test duplicates
        String[] args1 = new String[]{"-il","-l"};
        String[] args2 = new String[]{"--files-with-matches","--files-with-matches"};
        Assert.assertArrayEquals(new Option[]{Option.IgnoreCase, Option.FilesWithMatches}, ArgumentParser.getOpts(args1));
        Assert.assertArrayEquals(new Option[]{Option.FilesWithMatches}, ArgumentParser.getOpts(args2));
    }
    @Test
    public void optionParsingEmpty(){
        //Test Empty
        String[] args1 = new String[]{};
        String[] args2 = new String[]{"query","/file"};
        Assert.assertArrayEquals(new Option[]{}, ArgumentParser.getOpts(args1));
        Assert.assertArrayEquals(new Option[]{}, ArgumentParser.getOpts(args2));
    }
}
