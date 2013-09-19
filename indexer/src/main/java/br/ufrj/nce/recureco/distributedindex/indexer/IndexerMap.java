/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.ufrj.nce.recureco.distributedindex.indexer;

import br.ufrj.nce.recureco.distributedindex.common.clean.line.LineTokenizer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/21/13
 * Time: 2:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class IndexerMap extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {

    private LineTokenizer lineTokenizer;

    public IndexerMap() {
        this.lineTokenizer = new LineTokenizer();
    }

    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {

        FileSplit fileSplit = (FileSplit)reporter.getInputSplit();
        String filename = fileSplit.getPath().getName();

        List<String> tokenizedLine = lineTokenizer.tokenize(value.toString());

        for(String auxWord: tokenizedLine){
            output.collect(new Text(auxWord), new Text(filename));
        }
    }
}
