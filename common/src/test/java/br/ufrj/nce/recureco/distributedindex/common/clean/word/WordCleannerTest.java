package br.ufrj.nce.recureco.distributedindex.common.clean.word;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/21/13
 * Time: 4:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class WordCleannerTest {

    private WordCleanner wordCleanner;

    @Before
    public void setup(){
        this.wordCleanner = new WordCleanner();
    }

    @Test
    public void cleanWordTrimSuccessTest() {

        Assert.assertEquals("test",wordCleanner.cleanWord("test"));
        Assert.assertEquals("test",wordCleanner.cleanWord("test       "));
        Assert.assertEquals("test",wordCleanner.cleanWord("      test"));
        Assert.assertEquals("test",wordCleanner.cleanWord("      test        "));

    }


    @Test
    public void cleanWordToLowerCaseSuccessTest() {

        Assert.assertEquals("test",wordCleanner.cleanWord("TEST"));
        Assert.assertEquals("test",wordCleanner.cleanWord("tesT"));
        Assert.assertEquals("test",wordCleanner.cleanWord("Test"));
        Assert.assertEquals("test",wordCleanner.cleanWord("tEsT"));
        Assert.assertEquals("test",wordCleanner.cleanWord("TeSt"));
        Assert.assertEquals("test",wordCleanner.cleanWord("test"));
    }


    @Test
    public void cleanWordNonAlphaCharacterSuccessTest() {

        Assert.assertEquals("test",wordCleanner.cleanWord("t*e#s$tˆ"));
        Assert.assertEquals("test",wordCleanner.cleanWord("t!e%s(t)"));
        Assert.assertEquals("test",wordCleanner.cleanWord("\"{t}e|||\\s/t?"));
        Assert.assertEquals("test",wordCleanner.cleanWord("t;e.s,t'"));
        Assert.assertEquals("test",wordCleanner.cleanWord("t=e-st+"));
        Assert.assertEquals("test",wordCleanner.cleanWord("t=e_s_t+"));
        Assert.assertEquals("test",wordCleanner.cleanWord("test"));
    }


    @Test
    public void cleanWordAllSuccessTest() {

        Assert.assertEquals("test",wordCleanner.cleanWord("t* e#s _$Tˆ"));
        Assert.assertEquals("test",wordCleanner.cleanWord("T!E%S(T)"));
        Assert.assertEquals("test",wordCleanner.cleanWord("\"{t } e|||\\S/T?"));
        Assert.assertEquals("test",wordCleanner.cleanWord("T; e.... s , t'"));
        Assert.assertEquals("test",wordCleanner.cleanWord("test"));
    }
}
