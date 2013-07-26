package br.ufrj.nce.recureco.distributedindex.common.clean.line;

import br.ufrj.nce.recureco.distributedindex.common.clean.stopword.StopWordVerifier;
import br.ufrj.nce.recureco.distributedindex.common.clean.word.WordCleanner;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/26/13
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class LineTokenizer {

    private StopWordVerifier stopWordVerifier;
    private WordCleanner wordCleanner;

    public LineTokenizer() {
        this.stopWordVerifier = new StopWordVerifier();
        this.wordCleanner = new WordCleanner();
    }

    public List<String> tokenize(String line){

        StringTokenizer tokenizer = new StringTokenizer(line);

        List<String> wordList = new ArrayList<String>();

        while (tokenizer.hasMoreTokens()) {

            String auxWord = tokenizer.nextToken();

            //convert to lower case, trim and remove all non alpha characters
            auxWord = wordCleanner.cleanWord(auxWord);

            //TODO:stemming

            //TODO:lemmitization

            //removing stop words
            if(auxWord != null && auxWord.trim().length() > 0 && !stopWordVerifier.isStopWord(auxWord)){
                wordList.add(auxWord);
            }

        }

        return wordList;
    }

}
