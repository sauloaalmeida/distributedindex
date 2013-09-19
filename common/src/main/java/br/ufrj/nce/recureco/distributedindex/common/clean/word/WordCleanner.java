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
package br.ufrj.nce.recureco.distributedindex.common.clean.word;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/21/13
 * Time: 3:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class WordCleanner {

    public String cleanWord(String word){

        //if exists something, clean
        if(word != null && word.trim().length() > 0){

            //to lower case, trim, replace non alpha characters
            return word.toLowerCase()
                       .trim()
                       .replaceAll("\\s|[^A-Za-z]","");
        }

        //if still here, return nothing, null
        return null;
    }

}
