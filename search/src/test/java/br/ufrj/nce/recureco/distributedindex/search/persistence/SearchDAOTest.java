package br.ufrj.nce.recureco.distributedindex.search.persistence;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/27/13
 * Time: 12:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class SearchDAOTest {

    @Test
    public void searchDAOTest(){
        SearchDAO searchDAO = new SearchDAO("jdbc:mysql://192.168.56.121:3306/distributedindextest?user=root&password=123456");

        List<String> wordAndList = new ArrayList<String>();
        wordAndList.add("love");
        wordAndList.add("heart");

        List<String> listResult = searchDAO.getDocuments(wordAndList);

        Assert.assertEquals(2,listResult.size());
    }

}
