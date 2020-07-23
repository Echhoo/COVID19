package com.test.fs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.kerby.config.Conf;

import java.net.URI;
import java.net.URISyntaxException;

public class HDFSUtils {
    static {
        //设置宿主机环境变量
        System.setProperty("HADOOP_USER_NAME", "lcss");
        //0-2 获取运行环境的 OS信息， 需要设置hadoop——home主目录
        String osInfo = System.getProperty("os.name");
        if (osInfo.toLowerCase().indexOf("windows") != -1)
            System.setProperty("hadoop.home.dir", "d:/dev/hadoop");

    }

    //获取 FileSystem
    public static FileSystem getHDFSFileSystem(String hdfsRootURIStr) throws Exception {
        //String hdfsRootURIStr = "hdfs://master:9000";
        URI uri = new URI(hdfsRootURIStr);
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(uri, conf);
        return fs;
    }

    //获取系统信息,输出
    public static void getHDFSFsStatus(String hdfsRootURIStr) throws Exception {
        FileSystem fs = getHDFSFileSystem(hdfsRootURIStr);
        FsStatus fsStatus = fs.getStatus();
        long used = fsStatus.getUsed();
        long remaining = fsStatus.getRemaining();
        long capacity = fsStatus.getCapacity();
        System.out.println(used + "__" + remaining + "__" + capacity);
        fs.close();
    }

    //下载
    public static void downloadtoLocal(String hdfsRootURIStr, String dlsrc, String dldst) throws Exception {
        FileSystem fs = getHDFSFileSystem(hdfsRootURIStr);
        Path dlSrc = new Path(dlsrc);
        Path dlDst = new Path(dldst);
        fs.copyToLocalFile(dlSrc, dlDst);
        fs.close();
        System.out.println("finished");
    }

    //上传
    public static void uploadtoLocal(String hdfsRootURIStr, String ulsrc, String uldst) throws Exception {
        FileSystem fs = getHDFSFileSystem(hdfsRootURIStr);
        Path dlSrc = new Path(ulsrc);
        Path dlDst = new Path(uldst);
        fs.copyFromLocalFile(dlSrc, dlDst);
        fs.close();
        System.out.println("ok");
    }

    //复制
    public static void copyHDFSFile(String hdfsRootURIStr, String cpsrc, String cpdst) throws Exception {
        FileSystem fs = getHDFSFileSystem(hdfsRootURIStr);
        Path Src = new Path(cpsrc);
        Path Dst = new Path(cpdst);
        //Path coreSiteXMLPath = new Path(coreSiteXMLURLStr);
        //Path Dst = new Path("/abc/test.xml");
        FSDataInputStream is = fs.open(Src);
        FSDataOutputStream os = fs.create(Dst);
        IOUtils.copyBytes(is, os, 4096, false);
        IOUtils.closeStream(is);
        IOUtils.closeStream(os);
        //fs.re
    }

    //移动or重命名
    public static void renameMVHDFSFile(String hdfsRootURIStr, String src, String dst) throws Exception {
        FileSystem fs = getHDFSFileSystem(hdfsRootURIStr);
        Path Src = new Path(src);
        Path Dst = new Path(dst);
        boolean result = fs.rename(Src, Dst);
        fs.close();
        System.out.println(result);
    }

    //完成ls功能
    public static void lsHDFS(String hdfsRootURIStr, String src) throws Exception {
        FileSystem fs = getHDFSFileSystem(hdfsRootURIStr);
        Path Src = new Path(src);
        FileStatus[] fileStatuses = fs.listStatus(Src);
        for (FileStatus file : fileStatuses) {
            String df = file.isDirectory() ? "文件夹" : "文件  ";
            String permission = file.getPermission().toString();
            short replication = file.getReplication();
            long length = file.getLen();
            long time = file.getModificationTime();
            String own = file.getOwner();
            String group = file.getGroup();
            String path = file.getPath().toString();
            System.out.println(df + "\t" + permission + "\t" + replication + "\t" + own + "\t" + group + "\t" + length + "\t" + time + "\t" + path);
        }
    }

    //完成删除功能
    public static void deleteHDFSFile(String hdfsRootURIStr, String src) throws Exception {
        FileSystem fs = getHDFSFileSystem(hdfsRootURIStr);
        Path Src = new Path(src);
        boolean result = fs.delete(Src, true);
        fs.close();
        System.out.println(result+"文件删除成功");
    }

    //检测文件是否存在
    public static boolean checkifexits(String hdfsRootURIStr, String src) throws Exception {
        //String hdfsRootURIStr = "hdfs://master:9000";
        FileSystem fs = getHDFSFileSystem(hdfsRootURIStr);
        Path Src = new Path("/output/wc");
        boolean result = fs.exists(Src);
        fs.close();
        System.out.println("文件存在");
        return result;
    }
}
