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

    public List<String> getDocuments(String query){

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

        List<String> documentsIntersection = new ArrayList<String>();

        boolean firstTime = true;

        for (String docs: results){

            List<String> auxList = new ArrayList<String>();
            CollectionUtils.addAll(auxList,docs.split(","));

            if(firstTime){
                documentsIntersection = ListUtils.union(documentsIntersection,auxList);
                firstTime = false;
            } else {
                documentsIntersection = ListUtils.intersection(documentsIntersection,auxList);
            }
        }

        return documentsIntersection;
    }

}
