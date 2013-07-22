package br.ufrj.nce.recureco.distributedindex.cleanner;

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
