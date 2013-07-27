package br.ufrj.nce.recureco.distributedindex.common.clean.word;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/26/13
 * Time: 8:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class PorterStemmerTest {

    /** Test program for demonstrating the Stemmer.  It reads text from a
     * a list of files, stems each word, and writes the result to standard
     * output. Note that the word stemmed is expected to be in lower case:
     * forcing lower case must be done outside the Stemmer class.
     * Usage: Stemmer file-name file-name ...
     */
    @Test
    public void stemmerTest()
    {
        PorterStemmer porterStemmer = new PorterStemmer();

        String word = "ponies";
        porterStemmer.add(word.toCharArray(), word.length());
        porterStemmer.stem();
        Assert.assertEquals("poni",porterStemmer.toString());

        word = "distributed";
        porterStemmer.add(word.toCharArray(), word.length());
        porterStemmer.stem();
        Assert.assertEquals("distribut",porterStemmer.toString());

        word = "cats";
        porterStemmer.add(word.toCharArray(), word.length());
        porterStemmer.stem();
        Assert.assertEquals("cat",porterStemmer.toString());

        word = "matting";
        porterStemmer.add(word.toCharArray(), word.length());
        porterStemmer.stem();
        Assert.assertEquals("mat",porterStemmer.toString());

        word = "mating";
        porterStemmer.add(word.toCharArray(), word.length());
        porterStemmer.stem();
        Assert.assertEquals("mate",porterStemmer.toString());

        word = "meeting";
        porterStemmer.add(word.toCharArray(), word.length());
        porterStemmer.stem();
        Assert.assertEquals("meet",porterStemmer.toString());

        word = "milling";
        porterStemmer.add(word.toCharArray(), word.length());
        porterStemmer.stem();
        Assert.assertEquals("mill",porterStemmer.toString());

        word = "messing";
        porterStemmer.add(word.toCharArray(), word.length());
        porterStemmer.stem();
        Assert.assertEquals("mess",porterStemmer.toString());

    }

}
