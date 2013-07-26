package br.ufrj.nce.recureco.distributedindex.common.stopword;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/21/13
 * Time: 3:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class StopWordVerifier {

    private String[] stopWords;

    public StopWordVerifier() {

        this.stopWords = new String[]{"a","an","and","are","as","at","be","by","for","from",
                                      "has","he","in","is","it","its","of","on","that","the",
                                      "to","was","were","will","with"};
    }

    public boolean isStopWord(String word){
        return (Arrays.binarySearch(this.stopWords,word) >= 0);
    }
}
