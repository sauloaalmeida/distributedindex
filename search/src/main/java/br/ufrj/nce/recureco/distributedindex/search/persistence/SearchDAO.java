package br.ufrj.nce.recureco.distributedindex.search.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/26/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchDAO {

    private String jdbcUrlConnection;

    SearchDAO(String jdbcUrlConnection) {
        this.jdbcUrlConnection = jdbcUrlConnection;
    }

    public SearchDAO() {
        this.jdbcUrlConnection = "jdbc:mysql://192.168.56.121:3306/distributedindex?user=root&password=123456";
    }

    public List<String> getDocuments(List<String> andWords){

        //if query words list is null, return empty results
        if(andWords==null){
            return new ArrayList<String>();
        }
        //if query words list is  empty, return empty results
        if (andWords.size() == 0){
            return new ArrayList<String>();
        }

        //if still here, there is something to search
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");


            conn = DriverManager.getConnection(jdbcUrlConnection);

            preparedStatement = createPreparedStatement(andWords, conn);

            resultSet = preparedStatement.executeQuery();

            return prepareListDocumentsResult(resultSet);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Error while trying to close resultset",e);
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Error while trying to close prepared statement",e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Error while trying to close connection",e);
                }
            }
        }
    }

    private List<String> prepareListDocumentsResult(ResultSet resultSet) {

        List<String> listReturn = new ArrayList<String>();

        try {
            while (resultSet.next()){
                listReturn.add(resultSet.getString("documents"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while trying reading resultset",e);
        }

        return listReturn;
    }

    private PreparedStatement createPreparedStatement(List<String> andWords, Connection conn) throws SQLException {

        //mount prepared Statement dynamically
        boolean firstExec = true;
        StringBuilder questionMarks = new StringBuilder();
        for (int i=0;i<andWords.size();i++){

            if(!firstExec){
                questionMarks.append(",");
            }

            questionMarks.append("?");

            firstExec = false;
        }

        String query = String.format("select * from search_index where term in (%s)", questionMarks.toString());

        PreparedStatement preparedStatement = conn.prepareStatement(query);

        int j = 1;
        for(String word :andWords){
            preparedStatement.setString(j++,word);
        }

        return preparedStatement;
    }

}
