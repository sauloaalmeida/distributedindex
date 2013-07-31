package br.ufrj.nce.recureco.distributedindex.search.controller;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/26/13
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentViewerServlet extends javax.servlet.http.HttpServlet {

    private static final int BYTES_DOWNLOAD = 1024;
    private static final String DIR_DOWNLOAD = "/input/";
    private static final String DIR_HADOOP_CONF = "/hadoop/usr/hadoop-1.1.2/conf/";

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String doc = request.getParameter("doc");

        if(doc != null && doc.trim().length() > 0){

            try{

                String filePath = DIR_DOWNLOAD + doc;

                Configuration conf = new Configuration();

                conf.addResource(new Path(DIR_HADOOP_CONF + "core-site.xml"));
                conf.addResource(new Path(DIR_HADOOP_CONF + "hdfs-site.xml"));
                conf.addResource(new Path(DIR_HADOOP_CONF + "mapred-site.xml"));

                FileSystem fileSystem = FileSystem.get(conf);

                Path path = new Path(filePath);
                if (!fileSystem.exists(path)) {
                    response.getWriter().print("File not found.");
                    return;
                }

                FSDataInputStream in = fileSystem.open(path);

                response.setContentType("text/plain");

                int read=0;
                byte[] bytes = new byte[BYTES_DOWNLOAD];
                OutputStream os = response.getOutputStream();

                while((read = in.read(bytes))!= -1){
                    os.write(bytes, 0, read);
                }
                os.flush();
                os.close();
            }catch(FileNotFoundException e){
                response.getWriter().print("File not found.");
            }


        }else{
            //print invalid document
            response.getWriter().print("File not informed.");
        }

    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
         doGet(request,response);
    }


}