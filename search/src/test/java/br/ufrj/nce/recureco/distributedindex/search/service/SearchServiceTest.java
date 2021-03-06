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
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/27/13
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchServiceTest {

    private SearchService searchService;

    @Mock
    private SearchDAO searchDAOMock;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        searchService = new SearchService(new LineTokenizer(),searchDAOMock);
    }


    @Test
    public void testDocumentsUnformatedAndStopWordsAndQuerySuccessTest() throws Exception {

        List<String> andWords = new ArrayList<String>();
        andWords.add("distribut");
        andWords.add("heart");

        List<String> result = new ArrayList<String>();
        result.add("pg100.txt,pg10005.txt,pg10009.txt,pg1000.txt,pg10018.txt,pg10006.txt,pg10001.txt,pg10010.txt,pg10017.txt,pg10013.txt,pg10008.txt,pg10.txt,pg10012.txt,pg10004.txt,pg10014.txt,pg10003.txt,pg10016.txt,pg10007.txt,pg10020.txt,pg10002.txt,pg1001.txt,pg1002.txt,pg10019.txt,pg10015.txt");
        result.add("pg100.txt,pg10004.txt,pg10.txt,pg10012.txt,pg10017.txt,pg10006.txt,pg10013.txt,pg10001.txt,pg10011.txt,pg10010.txt,pg10018.txt,pg10008.txt,pg10009.txt,pg10005.txt,pg1001.txt,pg1002.txt,pg10020.txt,pg10014.txt,pg10007.txt,pg10016.txt,pg10019.txt,pg10002.txt,pg10003.txt,pg10015.txt");

        when(searchDAOMock.getDocuments(andWords)).thenReturn(result);

        List<String> finalResult = searchService.getDocuments("disTRIbutED HEARt and",false);

        Assert.assertEquals(23,finalResult.size());


        List<String> expectedResult = new ArrayList<String>();

        expectedResult.add("pg10019.txt");
        expectedResult.add("pg10015.txt");
        expectedResult.add("pg1001.txt");
        expectedResult.add("pg1002.txt");
        expectedResult.add("pg10002.txt");
        expectedResult.add("pg10020.txt");
        expectedResult.add("pg10007.txt");
        expectedResult.add("pg10016.txt");
        expectedResult.add("pg100.txt");
        expectedResult.add("pg10.txt");
        expectedResult.add("pg10008.txt");
        expectedResult.add("pg10013.txt");
        expectedResult.add("pg10005.txt");
        expectedResult.add("pg10009.txt");
        expectedResult.add("pg10018.txt");
        expectedResult.add("pg10006.txt");
        expectedResult.add("pg10001.txt");
        expectedResult.add("pg10010.txt");
        expectedResult.add("pg10017.txt");
        expectedResult.add("pg10012.txt");
        expectedResult.add("pg10004.txt");
        expectedResult.add("pg10014.txt");
        expectedResult.add("pg10003.txt");

        for(String word: expectedResult){
            Assert.assertTrue(finalResult.contains(word));
        }

        for(String word: finalResult){
            Assert.assertTrue(expectedResult.contains(word));
        }

    }


    @Test
    public void testDocumentsUnformatedOrStopWordsAndQuerySuccessTest() throws Exception {

        List<String> andWords = new ArrayList<String>();
        andWords.add("love");
        andWords.add("heart");

        List<String> result = new ArrayList<String>();
        result.add("pg100.txt,pg10005.txt,pg10009.txt,pg1000.txt,pg10018.txt,pg10006.txt,pg10001.txt,pg10010.txt,pg10017.txt,pg10013.txt,pg10008.txt,pg10.txt,pg10012.txt,pg10004.txt,pg10014.txt,pg10003.txt,pg10016.txt,pg10007.txt,pg10020.txt,pg10002.txt,pg1001.txt,pg1002.txt,pg10019.txt,pg10015.txt");
        result.add("pg100.txt,pg10004.txt,pg10.txt,pg10012.txt,pg10017.txt,pg10006.txt,pg10013.txt,pg10001.txt,pg10011.txt,pg10010.txt,pg10018.txt,pg10008.txt,pg10009.txt,pg10005.txt,pg1001.txt,pg1002.txt,pg10020.txt,pg10014.txt,pg10007.txt,pg10016.txt,pg10019.txt,pg10002.txt,pg10003.txt,pg10015.txt");

        when(searchDAOMock.getDocuments(andWords)).thenReturn(result);

        List<String> finalResult = searchService.getDocuments("a lOVEd hEARt and",true);

        Assert.assertEquals(25,finalResult.size());


        List<String> expectedResult = new ArrayList<String>();

        expectedResult.add("pg100.txt");
        expectedResult.add("pg10005.txt");
        expectedResult.add("pg10004.txt");
        expectedResult.add("pg10009.txt");
        expectedResult.add("pg10.txt");
        expectedResult.add("pg1000.txt");
        expectedResult.add("pg10012.txt");
        expectedResult.add("pg10018.txt");
        expectedResult.add("pg10017.txt");
        expectedResult.add("pg10006.txt");
        expectedResult.add("pg10001.txt");
        expectedResult.add("pg10013.txt");
        expectedResult.add("pg10010.txt");
        expectedResult.add("pg10011.txt");
        expectedResult.add("pg10008.txt");
        expectedResult.add("pg10014.txt");
        expectedResult.add("pg1001.txt");
        expectedResult.add("pg10003.txt");
        expectedResult.add("pg1002.txt");
        expectedResult.add("pg10016.txt");
        expectedResult.add("pg10020.txt");
        expectedResult.add("pg10007.txt");
        expectedResult.add("pg10002.txt");
        expectedResult.add("pg10019.txt");
        expectedResult.add("pg10003.txt");
        expectedResult.add("pg10015.txt");

        for(String word: expectedResult){
            Assert.assertTrue(finalResult.contains(word));
        }

        for(String word: finalResult){
            Assert.assertTrue(expectedResult.contains(word));
        }

    }





    @Test
    public void testDocumentsSuccessAndQueryTest() throws Exception {

        List<String> andWords = new ArrayList<String>();
        andWords.add("love");
        andWords.add("heart");

        List<String> result = new ArrayList<String>();
        result.add("pg100.txt,pg10005.txt,pg10009.txt,pg1000.txt,pg10018.txt,pg10006.txt,pg10001.txt,pg10010.txt,pg10017.txt,pg10013.txt,pg10008.txt,pg10.txt,pg10012.txt,pg10004.txt,pg10014.txt,pg10003.txt,pg10016.txt,pg10007.txt,pg10020.txt,pg10002.txt,pg1001.txt,pg1002.txt,pg10019.txt,pg10015.txt");
        result.add("pg100.txt,pg10004.txt,pg10.txt,pg10012.txt,pg10017.txt,pg10006.txt,pg10013.txt,pg10001.txt,pg10011.txt,pg10010.txt,pg10018.txt,pg10008.txt,pg10009.txt,pg10005.txt,pg1001.txt,pg1002.txt,pg10020.txt,pg10014.txt,pg10007.txt,pg10016.txt,pg10019.txt,pg10002.txt,pg10003.txt,pg10015.txt");

        when(searchDAOMock.getDocuments(andWords)).thenReturn(result);

        List<String> finalResult = searchService.getDocuments("love heart",false);

        Assert.assertEquals(23,finalResult.size());


        List<String> expectedResult = new ArrayList<String>();

        expectedResult.add("pg10019.txt");
        expectedResult.add("pg10015.txt");
        expectedResult.add("pg1001.txt");
        expectedResult.add("pg1002.txt");
        expectedResult.add("pg10002.txt");
        expectedResult.add("pg10020.txt");
        expectedResult.add("pg10007.txt");
        expectedResult.add("pg10016.txt");
        expectedResult.add("pg100.txt");
        expectedResult.add("pg10.txt");
        expectedResult.add("pg10008.txt");
        expectedResult.add("pg10013.txt");
        expectedResult.add("pg10005.txt");
        expectedResult.add("pg10009.txt");
        expectedResult.add("pg10018.txt");
        expectedResult.add("pg10006.txt");
        expectedResult.add("pg10001.txt");
        expectedResult.add("pg10010.txt");
        expectedResult.add("pg10017.txt");
        expectedResult.add("pg10012.txt");
        expectedResult.add("pg10004.txt");
        expectedResult.add("pg10014.txt");
        expectedResult.add("pg10003.txt");

        for(String word: expectedResult){
            Assert.assertTrue(finalResult.contains(word));
        }

        for(String word: finalResult){
            Assert.assertTrue(expectedResult.contains(word));
        }

    }


    @Test
    public void testDocumentsWithStopWordsAndQuerySuccessTest() throws Exception {

        List<String> andWords = new ArrayList<String>();
        andWords.add("love");
        andWords.add("heart");

        List<String> result = new ArrayList<String>();
        result.add("pg100.txt,pg10005.txt,pg10009.txt,pg1000.txt,pg10018.txt,pg10006.txt,pg10001.txt,pg10010.txt,pg10017.txt,pg10013.txt,pg10008.txt,pg10.txt,pg10012.txt,pg10004.txt,pg10014.txt,pg10003.txt,pg10016.txt,pg10007.txt,pg10020.txt,pg10002.txt,pg1001.txt,pg1002.txt,pg10019.txt,pg10015.txt");
        result.add("pg100.txt,pg10004.txt,pg10.txt,pg10012.txt,pg10017.txt,pg10006.txt,pg10013.txt,pg10001.txt,pg10011.txt,pg10010.txt,pg10018.txt,pg10008.txt,pg10009.txt,pg10005.txt,pg1001.txt,pg1002.txt,pg10020.txt,pg10014.txt,pg10007.txt,pg10016.txt,pg10019.txt,pg10002.txt,pg10003.txt,pg10015.txt");

        when(searchDAOMock.getDocuments(andWords)).thenReturn(result);

        List<String> finalResult = searchService.getDocuments("a love heart and",false);

        Assert.assertEquals(23,finalResult.size());


        List<String> expectedResult = new ArrayList<String>();

        expectedResult.add("pg10019.txt");
        expectedResult.add("pg10015.txt");
        expectedResult.add("pg1001.txt");
        expectedResult.add("pg1002.txt");
        expectedResult.add("pg10002.txt");
        expectedResult.add("pg10020.txt");
        expectedResult.add("pg10007.txt");
        expectedResult.add("pg10016.txt");
        expectedResult.add("pg100.txt");
        expectedResult.add("pg10.txt");
        expectedResult.add("pg10008.txt");
        expectedResult.add("pg10013.txt");
        expectedResult.add("pg10005.txt");
        expectedResult.add("pg10009.txt");
        expectedResult.add("pg10018.txt");
        expectedResult.add("pg10006.txt");
        expectedResult.add("pg10001.txt");
        expectedResult.add("pg10010.txt");
        expectedResult.add("pg10017.txt");
        expectedResult.add("pg10012.txt");
        expectedResult.add("pg10004.txt");
        expectedResult.add("pg10014.txt");
        expectedResult.add("pg10003.txt");

        for(String word: expectedResult){
            Assert.assertTrue(finalResult.contains(word));
        }

        for(String word: finalResult){
            Assert.assertTrue(expectedResult.contains(word));
        }

    }

    @Test
    public void testDocumentsSuccessOrQueryTest() throws Exception {

        List<String> andWords = new ArrayList<String>();
        andWords.add("love");
        andWords.add("heart");

        List<String> result = new ArrayList<String>();
        result.add("pg100.txt,pg10005.txt,pg10009.txt,pg1000.txt,pg10018.txt,pg10006.txt,pg10001.txt,pg10010.txt,pg10017.txt,pg10013.txt,pg10008.txt,pg10.txt,pg10012.txt,pg10004.txt,pg10014.txt,pg10003.txt,pg10016.txt,pg10007.txt,pg10020.txt,pg10002.txt,pg1001.txt,pg1002.txt,pg10019.txt,pg10015.txt");
        result.add("pg100.txt,pg10004.txt,pg10.txt,pg10012.txt,pg10017.txt,pg10006.txt,pg10013.txt,pg10001.txt,pg10011.txt,pg10010.txt,pg10018.txt,pg10008.txt,pg10009.txt,pg10005.txt,pg1001.txt,pg1002.txt,pg10020.txt,pg10014.txt,pg10007.txt,pg10016.txt,pg10019.txt,pg10002.txt,pg10003.txt,pg10015.txt");

        when(searchDAOMock.getDocuments(andWords)).thenReturn(result);

        List<String> finalResult = searchService.getDocuments("love heart",true);

        Assert.assertEquals(25,finalResult.size());


        List<String> expectedResult = new ArrayList<String>();

        expectedResult.add("pg100.txt");
        expectedResult.add("pg10005.txt");
        expectedResult.add("pg10004.txt");
        expectedResult.add("pg10009.txt");
        expectedResult.add("pg10.txt");
        expectedResult.add("pg1000.txt");
        expectedResult.add("pg10012.txt");
        expectedResult.add("pg10018.txt");
        expectedResult.add("pg10017.txt");
        expectedResult.add("pg10006.txt");
        expectedResult.add("pg10001.txt");
        expectedResult.add("pg10013.txt");
        expectedResult.add("pg10010.txt");
        expectedResult.add("pg10011.txt");
        expectedResult.add("pg10008.txt");
        expectedResult.add("pg10014.txt");
        expectedResult.add("pg1001.txt");
        expectedResult.add("pg10003.txt");
        expectedResult.add("pg1002.txt");
        expectedResult.add("pg10016.txt");
        expectedResult.add("pg10020.txt");
        expectedResult.add("pg10007.txt");
        expectedResult.add("pg10002.txt");
        expectedResult.add("pg10019.txt");
        expectedResult.add("pg10003.txt");
        expectedResult.add("pg10015.txt");

        for(String word: expectedResult){
            Assert.assertTrue(finalResult.contains(word));
        }

        for(String word: finalResult){
            Assert.assertTrue(expectedResult.contains(word));
        }

    }


    @Test
    public void testDocumentsWithStopWordsOrQuerySuccessTest() throws Exception {

        List<String> andWords = new ArrayList<String>();
        andWords.add("love");
        andWords.add("heart");

        List<String> result = new ArrayList<String>();
        result.add("pg100.txt,pg10005.txt,pg10009.txt,pg1000.txt,pg10018.txt,pg10006.txt,pg10001.txt,pg10010.txt,pg10017.txt,pg10013.txt,pg10008.txt,pg10.txt,pg10012.txt,pg10004.txt,pg10014.txt,pg10003.txt,pg10016.txt,pg10007.txt,pg10020.txt,pg10002.txt,pg1001.txt,pg1002.txt,pg10019.txt,pg10015.txt");
        result.add("pg100.txt,pg10004.txt,pg10.txt,pg10012.txt,pg10017.txt,pg10006.txt,pg10013.txt,pg10001.txt,pg10011.txt,pg10010.txt,pg10018.txt,pg10008.txt,pg10009.txt,pg10005.txt,pg1001.txt,pg1002.txt,pg10020.txt,pg10014.txt,pg10007.txt,pg10016.txt,pg10019.txt,pg10002.txt,pg10003.txt,pg10015.txt");

        when(searchDAOMock.getDocuments(andWords)).thenReturn(result);

        List<String> finalResult = searchService.getDocuments("a love heart and",true);

        Assert.assertEquals(25,finalResult.size());


        List<String> expectedResult = new ArrayList<String>();

        expectedResult.add("pg100.txt");
        expectedResult.add("pg10005.txt");
        expectedResult.add("pg10004.txt");
        expectedResult.add("pg10009.txt");
        expectedResult.add("pg10.txt");
        expectedResult.add("pg1000.txt");
        expectedResult.add("pg10012.txt");
        expectedResult.add("pg10018.txt");
        expectedResult.add("pg10017.txt");
        expectedResult.add("pg10006.txt");
        expectedResult.add("pg10001.txt");
        expectedResult.add("pg10013.txt");
        expectedResult.add("pg10010.txt");
        expectedResult.add("pg10011.txt");
        expectedResult.add("pg10008.txt");
        expectedResult.add("pg10014.txt");
        expectedResult.add("pg1001.txt");
        expectedResult.add("pg10003.txt");
        expectedResult.add("pg1002.txt");
        expectedResult.add("pg10016.txt");
        expectedResult.add("pg10020.txt");
        expectedResult.add("pg10007.txt");
        expectedResult.add("pg10002.txt");
        expectedResult.add("pg10019.txt");
        expectedResult.add("pg10003.txt");
        expectedResult.add("pg10015.txt");

        for(String word: expectedResult){
            Assert.assertTrue(finalResult.contains(word));
        }

        for(String word: finalResult){
            Assert.assertTrue(expectedResult.contains(word));
        }

    }



    @Test
    public void testDocumentsEmptyWordsAndQuerySuccessTest() throws Exception {

        List<String> finalResult = searchService.getDocuments("",false);

        Assert.assertEquals(0,finalResult.size());

    }


    @Test
    public void testDocumentsEmptyWordsOrQuerySuccessTest() throws Exception {

        List<String> finalResult = searchService.getDocuments("",false);

        Assert.assertEquals(0,finalResult.size());

    }

    @Test
    public void documentsNullWordsAndQuerySuccessTest() throws Exception {

        List<String> finalResult = searchService.getDocuments(null,false);

        Assert.assertEquals(0,finalResult.size());

    }

    @Test
    public void documentsNullWordsOrQuerySuccessTest() throws Exception {

        List<String> finalResult = searchService.getDocuments(null,true);

        Assert.assertEquals(0,finalResult.size());

    }


    @Test
    public void documentsOnlyStopWordsAndQuerySuccessTest() throws Exception {

        List<String> finalResult = searchService.getDocuments("and an on",false);

        Assert.assertEquals(0,finalResult.size());

    }


    @Test
    public void documentsOnlyStopWordsOrQuerySuccessTest() throws Exception {

        List<String> finalResult = searchService.getDocuments("and an on",true);

        Assert.assertEquals(0,finalResult.size());

    }




}
