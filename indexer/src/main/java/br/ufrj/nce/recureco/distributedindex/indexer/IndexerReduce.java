package br.ufrj.nce.recureco.distributedindex.indexer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/21/13
 * Time: 2:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class IndexerReduce extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {

        StringBuilder sb = new StringBuilder();

        boolean firstTime = true;

        while (values.hasNext()) {

            String newDocument = values.next().toString();

            if(newDocument != null && newDocument.trim().length() > 0 && !sb.toString().contains(newDocument)){

                if(!firstTime){
                    sb.append(",");
                }

                sb.append(newDocument);

                firstTime = false;
            }
        }
        output.collect(key, new Text(sb.toString()));
    }
}