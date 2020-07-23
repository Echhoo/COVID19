package com.test.url;


import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//从HDFS读取文件    ====IOStream
public class TestHDFSByURL {

    static {
        //开启hdfs协议的支持,需要 hadoop提供
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }
    private static String hdfsURL="hdfs://master:9000/user/lcss/input/core-site.xml";

    //将hdfsurl指定资源下载
    private static void copyToLocal(String hdfsURL) throws Exception {
        //shili化url
        URL url = new URL(hdfsURL);
        //获取url链接
        URLConnection urlConnection = url.openConnection();
        //基于链接获取输入流
        InputStream is =urlConnection.getInputStream();
        // 输出到本地
        IOUtils.copyBytes(is,System.out,4096,false);
        //BufferedInputStream bis=new BufferedInputStream(is);
        //InputStreamReader isr=new InputStreamReader(is);
        //BufferedReader br=new BufferedReader(isr);
        //String buffer=null;
        //while((buffer=br.readLine())!=null){
        //    System.out.println(buffer);
        //}
        //br.close();isr.close();is.close();
    }

    public static void main(String[] args) throws Exception{
        copyToLocal(hdfsURL);
    }
}























