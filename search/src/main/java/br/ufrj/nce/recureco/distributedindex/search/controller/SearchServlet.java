package br.ufrj.nce.recureco.distributedindex.search.controller;

import br.ufrj.nce.recureco.distributedindex.search.service.SearchService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/26/13
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchServlet extends javax.servlet.http.HttpServlet {


    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Date begin = new Date();

        String query = request.getParameter("q");

        if(query != null && query.trim().length() > 0){

            //it there is message execute search, and get list result
            List<String> documents = new SearchService().getDocuments(query);

            //set list results to be used in result page
            request.setAttribute("result",documents);

            request.setAttribute("query",query);
        }else{

            //if query is not set, set error message to be used in result page
            request.setAttribute("errorMsg","Query not informed.");
        }

        //calculate query execution time
        request.setAttribute("time",(new Date()).getTime() - begin.getTime());

        //redirect to result page
        getServletContext().getRequestDispatcher("/").forward(request, response);

    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
         doGet(request,response);
    }


}