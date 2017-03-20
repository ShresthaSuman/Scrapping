/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.scrap;

import com.leapfrog.scrap.entity.DAO.LinkDAO;
import com.leapfrog.scrap.entity.DAOImpl.LinkDAOImpl;
import com.leapfrog.scrap.entity.Link;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.IIOException;

/**
 *
 * @author Mr. Shrestha Suman
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       String site="http://leapfrog.academy/";
        LinkDAO ldao=new LinkDAOImpl();
       try{
           URL url=new URL(site);
           HttpURLConnection conn=(HttpURLConnection) url.openConnection();
           BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
           String line="";
           StringBuilder contents =new StringBuilder();
           while((line=reader.readLine())!=null){
              contents.append(line).append("\n");
           }
           String regEx="<a href=\"(.*?)\" class=\"track\"(.*?)data-property=\"(.*?)\">\\n(.*?)\\n(.*?)<h5>(.*?)</h5>\\n(.*?)</a>";
           Pattern pattern=Pattern.compile(regEx);
           Matcher matcher=pattern.matcher(contents.toString());
                   while(matcher.find()){
                       Link link =new Link();
                       link.setLinks(matcher.group(1));
                       link.setTitle(matcher.group(6));
                       int id=ldao.insert(link);
                       System.out.println(id);
                   }
           
       }catch(IOException ioe){
           System.out.println(ioe.getMessage());
       }
       
    }
    
}
