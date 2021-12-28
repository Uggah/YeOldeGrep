package org.ye.yeoldegrep;

import org.junit.Assert;
import org.junit.Test;
import org.ye.yeoldegrep.parser.ArgumentParser;

public class QueryParsingTest {
    @Test
    public void queryParsingTest(){
        //Test some argument-variations
        String[] args1 = new String[]{"-il","query","/file"};
        String[] args2 = new String[]{"query","/file"};
        String[] args3 = new String[]{};
        String[] args4 = new String[]{"/file"};
        String[] args5 = new String[]{"-il"};
        Assert.assertEquals("query", ArgumentParser.getQuery(args1));
        Assert.assertEquals("query", ArgumentParser.getQuery(args2));
        Assert.assertEquals(null, ArgumentParser.getQuery(args3));
        Assert.assertEquals("/file", ArgumentParser.getQuery(args4));
        Assert.assertEquals(null, ArgumentParser.getQuery(args5));
    }
}
