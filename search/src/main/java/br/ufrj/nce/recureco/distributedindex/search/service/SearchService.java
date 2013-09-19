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
package br.ufrj.nce.recureco.distributedindex.search.service;

import br.ufrj.nce.recureco.distributedindex.common.clean.line.LineTokenizer;
import br.ufrj.nce.recureco.distributedindex.search.persistence.SearchDAO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/26/13
 * Time: 11:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchService {

    private LineTokenizer lineTokenizer;
    private SearchDAO searchDAO;

    public SearchService() {
        this(new LineTokenizer(),new SearchDAO());
    }

    SearchService(LineTokenizer lineTokenizer, SearchDAO searchDAO) {
        this.lineTokenizer = lineTokenizer;
        this.searchDAO = searchDAO;
    }

    public List<String> getDocuments(String query,boolean booleanOr){

        List<String> andWords = lineTokenizer.tokenize(query);

        //if query words list is null, return empty results
        if(andWords==null){
            return new ArrayList<String>();
        }
        //if query words list is  empty, return empty results
        if (andWords.size() == 0){
            return new ArrayList<String>();
        }

        //if still here, there is something to search
        List<String> results = searchDAO.getDocuments(andWords);

        List<String> documentsResults = new ArrayList<String>();

        boolean firstTime = true;

        for (String docs: results){

            List<String> auxList = new ArrayList<String>();
            CollectionUtils.addAll(auxList,docs.split(","));

            if(booleanOr){
                documentsResults = ListUtils.sum(documentsResults,auxList);
            }else{
                if(firstTime){
                    documentsResults = ListUtils.union(documentsResults,auxList);
                    firstTime = false;
                } else {
                    documentsResults = ListUtils.intersection(documentsResults,auxList);
                }
            }
        }

        return documentsResults;
    }

}
