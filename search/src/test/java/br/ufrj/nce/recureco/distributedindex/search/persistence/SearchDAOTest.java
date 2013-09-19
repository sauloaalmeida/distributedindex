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
    public void searchDAOSuccessTest(){
        SearchDAO searchDAO = new SearchDAO("jdbc:mysql://192.168.56.121:3306/distributedindextest?user=root&password=123456");

        List<String> wordAndList = new ArrayList<String>();
        wordAndList.add("love");
        wordAndList.add("heart");

        List<String> listResult = searchDAO.getDocuments(wordAndList);

        Assert.assertEquals(2,listResult.size());
    }

    @Test
    public void searchDAOEmptySuccessTest(){
        SearchDAO searchDAO = new SearchDAO("jdbc:mysql://192.168.56.121:3306/distributedindextest?user=root&password=123456");

        List<String> wordAndList = new ArrayList<String>();


        List<String> listResult = searchDAO.getDocuments(wordAndList);

        Assert.assertEquals(0,listResult.size());
    }


    @Test
    public void searchDAONullSuccessTest(){
        SearchDAO searchDAO = new SearchDAO("jdbc:mysql://192.168.56.121:3306/distributedindextest?user=root&password=123456");

        List<String> wordAndList = null;

        List<String> listResult = searchDAO.getDocuments(wordAndList);

        Assert.assertEquals(0,listResult.size());
    }

}
