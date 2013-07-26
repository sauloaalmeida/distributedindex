package br.ufrj.nce.recureco.distributedindex.indexer;

import br.ufrj.nce.recureco.distributedindex.common.stopword.StopWordVerifier;
import br.ufrj.nce.recureco.distributedindex.common.wordcleaner.WordCleanner;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/21/13
 * Time: 2:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class IndexerMap extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {

    private final static IntWritable one = new IntWritable(1);
    private StopWordVerifier stopWordVerifier;
    private WordCleanner wordCleanner;
    private Text word;

    public IndexerMap() {
        this.stopWordVerifier = new StopWordVerifier();
        this.wordCleanner = new WordCleanner();
        this.word = new Text();
    }

    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {

        String line = value.toString();
        StringTokenizer tokenizer = new StringTokenizer(line);

        while (tokenizer.hasMoreTokens()) {

            String auxWord = tokenizer.nextToken();

            //convert to lower case, trim and remove all non alpha characters
            auxWord = wordCleanner.cleanWord(auxWord);

            //TODO:stemming

            //TODO:lemmitization

            FileSplit fileSplit = (FileSplit)reporter.getInputSplit();
            String filename = fileSplit.getPath().getName();

            //removing stop words
            if(auxWord != null && auxWord.trim().length() > 0 && !stopWordVerifier.isStopWord(auxWord)){
                word.set(auxWord);
                output.collect(word, new Text(filename));
            }

        }
    }
}
