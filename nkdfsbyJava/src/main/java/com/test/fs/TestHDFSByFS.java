package com.test.fs;

import org.apache.commons.math3.analysis.function.StepFunction;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.yarn.webapp.hamlet2.Hamlet;

import java.io.IOException;
import java.net.URI;

public class TestHDFSByFS {
    //zai static code种操作hdfs用户信息
    static {
        //设置宿主机环境变量
        System.setProperty("HADOOP_USER_NAME","lcss");
    }

    public static void main(String[] args) throws IOException {
        //0 指定 HDFS 的根目录
        String hdfsRootURIStr = "hdfs://master:9000";
        //0-1 实例化uri
        URI hdfsRootURI = URI.create(hdfsRootURIStr);
        //0-2 获取运行环境的 OS信息， 需要设置hadoop——home主目录
        String osInfo = System.getProperty("os.name");
        if(osInfo.toLowerCase().indexOf("windows")!=-1)
            System.setProperty("hadoop.home.dir","d:/dev/hadoop");

        //1 获取指定uri的filesystem

        //1.1 获取hadoop环境配置文件
        Configuration conf = new Configuration();
        //1.2 根据hdfs conf配置文件获取
        FileSystem fs=FileSystem.get(hdfsRootURI, conf);
        //
        //
        ////2 演示filrsystem常规操作
        ////2.1获取状态信息
        //FsStatus fsStatus = fs.getStatus();
        //long used = fsStatus.getUsed();
        //long remaining = fsStatus.getRemaining();
        //
        //long capacity = fsStatus.getCapacity();
        //System.out.println(used +"__"+remaining+"__"+capacity);
        //
        //2.2 下载 hdfs 指定文件  //path具体资源
        String coreSiteXMLURLStr = "hdfs://master:9000/user/lcss/input/core-site.xml";
        Path coreSiteXMLPath = new Path(coreSiteXMLURLStr);
        //Path Dst = new Path("/abc/test.xml");
        //FSDataInputStream is = fs.open(coreSiteXMLPath);
        //FSDataOutputStream os = fs.create(Dst);
        //IOUtils.copyBytes(is,os,4096,false);
        //IOUtils.closeStream(is);
        //IOUtils.closeStream(os);

        ////2.3 下载文件到本地，，上传文件到HDFS
        //Path dlSrc = new Path("/user/lcss/input/core-site.xml");
        //Path dlDst = new Path("D:/2.xml");
        //fs.copyToLocalFile(dlSrc,dlDst);
        ////----
        //Path ulSrc = new Path("D:/1.xml");
        //Path ulDst = new Path("/data");
        //fs.copyFromLocalFile(ulSrc,ulDst);

        //2.3.模拟 -ls
        //Path Src = new Path("/");
        //FileStatus[] fileStatuses = fs.listStatus(Src);
        //for(FileStatus file : fileStatuses){
        //    String df = file.isDirectory()?"文件夹":"文件  ";
        //    String permission = file.getPermission().toString();
        //    short replication = file.getReplication();
        //    long length = file.getLen();
        //    long time = file.getModificationTime();
        //    String own =file.getOwner();
        //    String group =file.getGroup();
        //    String path = file.getPath().toString();
        //    System.out.println(df+"\t"+permission+"\t"+replication+"\t"+own+"\t"+group+"\t"+length+"\t"+time+"\t"+path);
        //}

        //2.4 移动 true
        //Path Src = new Path("/abc/test.xml");
        //Path Dst = new Path("/abc/1.xml");
        //boolean result=fs.rename(Src,Dst);
        //fs.close();
        //System.out.println(result);

        //2.5 删除
        //Path Src = new Path("/abc/1.xml");
        //boolean result = fs.delete(Src,true);
        //fs.close();
        //System.out.println(result);

        //2.6检测是否存在
        Path Src = new Path("/output/wc");
        boolean result = fs.exists(Src);
        fs.close();
        System.out.println(result);

    }
}




















