package br.ufrj.nce.recureco.distributedindex.common.clean.line;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/27/13
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class LineTokenizerTest {

    private LineTokenizer lineTokenizer;

    @Before
    public void setup(){
        this.lineTokenizer = new LineTokenizer();
    }

    @Test
    public void lineTokenizerSuccessTest(){

        List<String> list = lineTokenizer.tokenize("and or distributed");

        Assert.assertEquals(2,list.size());
        Assert.assertEquals("or",list.get(0));
        Assert.assertEquals("distribut",list.get(1));


    }

    @Test
    public void lineTokenizerOnlyStopWordsSuccessTest(){

        List<String> list = lineTokenizer.tokenize("and on an a");

        Assert.assertEquals(0,list.size());

    }

}